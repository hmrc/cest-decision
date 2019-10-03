/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.decisionservice.services

import java.time.LocalDateTime

import com.google.inject.Inject
import play.api.Logger
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.models.enums.{ResultEnum, SetupEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.repository.InterviewRepository
import uk.gov.hmrc.decisionservice.ruleEngines._

import scala.concurrent.{ExecutionContext, Future}

class DecisionService @Inject()(controlRuleEngine: ControlRuleEngine,
                                earlyExitRuleEngine: ExitRuleEngine,
                                financialRiskRuleEngine: FinancialRiskRuleEngine,
                                personalServiceRuleEngine: PersonalServiceRuleEngine,
                                partAndParcelRuleEngine: PartAndParcelRuleEngine,
                                resultRuleEngine: ResultRuleEngine,
                                businessOnOwnAccountRuleEngine: BusinessOnOwnAccountRuleEngine,
                                val repository: InterviewRepository) {

  def calculateResult(request: DecisionRequest)(implicit ec: ExecutionContext): Future[DecisionResponse] = {

    val interview = request.interview
    implicit val version = request.version

    val setup = if(interview.setup.isDefined) Some(SetupEnum.CONTINUE) else None

    for {
      exit <- earlyExitRuleEngine.decide(interview.exit)
      personalService <- personalServiceRuleEngine.decide(interview.personalService)
      control <- controlRuleEngine.decide(interview.control)
      financialRisk <- financialRiskRuleEngine.decide(interview.financialRisk)
      partAndParcel <- partAndParcelRuleEngine.decide(interview.partAndParcel)
      businessOnOwnAccount <- businessOnOwnAccountRuleEngine.decide(interview.businessOnOwnAccount)
      score = Score(setup, exit, personalService, control, financialRisk, partAndParcel, businessOnOwnAccount)
      scoreWithoutBooa = Score(setup, exit, personalService, control, financialRisk, partAndParcel, Some(WeightedAnswerEnum.MEDIUM))
      result <- resultRuleEngine.decide(score)
      resultWithoutBooa <- resultRuleEngine.decide(scoreWithoutBooa)
      response = DecisionResponse(request.version, request.correlationID, score, result, resultWithoutBooa)
      _ <- logResult(request, response)
    } yield response
  }

  private def logResult(request: DecisionRequest, response: DecisionResponse)(implicit ec: ExecutionContext): Future[Boolean] =
    if (response.result != ResultEnum.NOT_MATCHED) {
      repository().save(LogInterview(request, response.result.toString, response.score, LocalDateTime.now)).map {
        case result if result.ok => true
        case result => {
          Logger.error(s"[DecisionService][logResult] Failed to log result, ${result.writeErrors}")
          false
        }
      }.recover {
        case e => {
          Logger.error(s"[DecisionService][logResult] Failed to write to Mongo, ${e.getMessage}")
          false
        }
      }
    } else Future.successful(true)
}
