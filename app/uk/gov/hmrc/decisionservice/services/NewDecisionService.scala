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

import com.google.inject.Inject
import uk.gov.hmrc.decisionservice.models.{DecisionRequest, _DecisionResponse}
import uk.gov.hmrc.decisionservice.models.enums.SetupEnum

import scala.concurrent.{ExecutionContext, Future}

class NewDecisionService @Inject()(controlDecisionService: ControlDecisionService,
                                   exitDecisionService: ExitDecisionService,
                                   financialRiskDecisionService: FinancialRiskDecisionService,
                                   personalServiceDecisionService: PersonalServiceDecisionService,
                                   partAndParcelDecisionService: PartAndParcelDecisionService,
                                   resultService: ResultService) {

  val version = "1.0.0-beta"

  def calculateResult(request: DecisionRequest)(implicit ec: ExecutionContext): Future[_DecisionResponse] = {

    import uk.gov.hmrc.decisionservice.models.{Score, _DecisionResponse}

    val interview = request.interview
    val setup: Option[SetupEnum.Value] = None

    val _exitDecisionService = exitDecisionService.decide(interview.exit)
    val _personalServiceDecisionService = personalServiceDecisionService.decide(interview.personalService)
    val _controlDecisionService = controlDecisionService.decide(interview.control)
    val _financialRiskDecisionService = financialRiskDecisionService.decide(interview.financialRisk)
    val _partAndParcelDecisionService = partAndParcelDecisionService.decide(interview.partAndParcel)

    for {
      exit <- _exitDecisionService
      personalService <- _personalServiceDecisionService
      control <- _controlDecisionService
      financialRisk <- _financialRiskDecisionService
      partAndParcel <- _partAndParcelDecisionService
      result <- resultService.decide(exit, personalService, control, financialRisk, partAndParcel)

    } yield _DecisionResponse(version, request.correlationID, Score(setup, exit, personalService, control, financialRisk, partAndParcel), result)
  }
}
