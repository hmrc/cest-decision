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

import uk.gov.hmrc.decisionservice.config.ruleSets.EarlyExitRules
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.enums.{ExitEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.util.ExitRulesSet
import uk.gov.hmrc.play.test.UnitSpec

class ExitDecisionServiceSpec extends UnitSpec {

  object TestExitDecisionService extends ExitDecisionService(new ExitRulesSet)

  "ExitDecisionService" when {

    "decide is called with an Exit section" should {

      "returns an INSIDE_IR35" in {

        val actualResult = TestExitDecisionService.decide(Some(Exit(Some(true))))
        val expectedResult = Some(ExitEnum.INSIDE_IR35)

        await(actualResult) shouldBe expectedResult
      }
    }

    "decide is called with a Exit section with in scenarios populated" should {

      val expectedAnswer = Some(ExitEnum.INSIDE_IR35)
      val indexedArray = EarlyExitRules.inside.value.zipWithIndex

      indexedArray.foreach {
        item =>

          val (jsValue, index) = item

          s"return an answer for scenario ${index + 1}" in {

            val actualAnswer = TestExitDecisionService.decide(Some(jsValue.as[Exit]))

            await(actualAnswer) shouldBe expectedAnswer

          }
      }
    }
  }
}
