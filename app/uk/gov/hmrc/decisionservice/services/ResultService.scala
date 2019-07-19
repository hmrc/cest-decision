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

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Score
import uk.gov.hmrc.decisionservice.models.enums.{ExitEnum, ResultEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.util.MatrixOfMatricesRulesSet

import scala.concurrent.Future

class ResultService @Inject()(ruleSet: MatrixOfMatricesRulesSet) {

  def decide(score: Score): Future[ResultEnum.Value] = {

    def checkMatrixOfMatrices: Future[ResultEnum.Value] = {
      val result = ruleSet.checkRules(score, ResultEnum.NOT_MATCHED)

      val response = result match {
        case "INIR35" => ResultEnum.INSIDE_IR35
        case _ => ResultEnum.withName(result)
      }

      Future.successful(ResultEnum.withName(response))
    }

    score match {
      case Score(None, None, None, None, None, None) => Future.successful(ResultEnum.NOT_MATCHED)
      case _ => checkResults(score).fold(checkMatrixOfMatrices)(result => Future.successful(result))
    }
  }

  def checkResults(score: Score): Option[ResultEnum.Value] = {

    val exitInside = Some(ExitEnum.INSIDE_IR35)
    val sectionInside = Some(WeightedAnswerEnum.INSIDE_IR35)
    val sectionOutside = Some(WeightedAnswerEnum.OUTSIDE_IR35)
    val sectionAnswers = Seq(score.personalService, score.control, score.financialRisk, score.partAndParcel)

    if (score.exit == exitInside || sectionAnswers.contains(sectionInside)) {

      Some(ResultEnum.INSIDE_IR35)

    } else if (sectionAnswers.contains(sectionOutside)) {

      Some(ResultEnum.OUTSIDE_IR35)

    } else {

      None
    }
  }
}
