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

import uk.gov.hmrc.decisionservice.config.ruleSets.{MatrixOfMatricesRules, PartAndParcelRules}
import uk.gov.hmrc.decisionservice.models.{PartAndParcel, Score}
import uk.gov.hmrc.decisionservice.models.enums.{ResultEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.util.{MatrixOfMatricesRulesSet, PartAndParcelRulesSet}
import uk.gov.hmrc.play.test.UnitSpec

class ResultServiceSpec extends UnitSpec{

  object TestResultDecisionService extends ResultService(new MatrixOfMatricesRulesSet)

  "ResultService" when {

    "decide is called with all sections populated for a in determination" should {

      val expectedAnswer = ResultEnum.INSIDE_IR35
      val indexedArray = MatrixOfMatricesRules.inside.value.zipWithIndex

      indexedArray.foreach {
        item =>

          val (jsValue, index) = item

          s"return a result for scenario ${index + 1}" in {

            val score = jsValue.as[Score]

            val actualAnswer = TestResultDecisionService.decide(
              score.exit,score.personalService,score.control,score.financialRisk,score.partAndParcel
            )

            await(actualAnswer) shouldBe expectedAnswer

          }
      }
    }

    "decide is called with all sections populated for a unknown determination" should {

      val expectedAnswer = ResultEnum.UNKNOWN
      val indexedArray = MatrixOfMatricesRules.unknown.value.zipWithIndex

      indexedArray.foreach {
        item =>

          val (jsValue, index) = item

          s"return a result for scenario ${index + 1}" in {

            val score = jsValue.as[Score]

            val actualAnswer = TestResultDecisionService.decide(
              score.exit,score.personalService,score.control,score.financialRisk,score.partAndParcel
            )

            await(actualAnswer) shouldBe expectedAnswer

          }
      }
    }

    "decide is called with None for every section" should {

      "return a result" in {

        val expectedAnswer = ResultEnum.NOT_MATCHED
        val actualAnswer = TestResultDecisionService.decide(None, None, None, None,None)

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }

}
