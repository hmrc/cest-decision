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

import uk.gov.hmrc.decisionservice.config.ruleSets.PartAndParcelRules
import uk.gov.hmrc.decisionservice.models.PartAndParcel
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum
import uk.gov.hmrc.decisionservice.util.PartAndParcelRulesSet
import uk.gov.hmrc.play.test.UnitSpec

class PartAndParcelDecisionServiceSpec extends UnitSpec{

  object TestPartAndParcelDecisionService extends PartAndParcelDecisionService(new PartAndParcelRulesSet)

  "PartAndParcelDecisionServiceSpec" when {

    "decide is called with a PartAndParcel section with every value provided" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = WeightedAnswerEnum.MEDIUM
        val actualAnswer = TestPartAndParcelDecisionService.decide(PartAndParcel(
          Some(false),
          Some(false),
          Some(true),
          Some("workForEndClient")
        ))

        await(actualAnswer) shouldBe Some(expectedAnswer)

      }
    }

    "decide is called with a PartAndParcel section with triggered rules populated" should {

      PartAndParcelRules.ruleSet.zipWithIndex.foreach { item =>

          val (ruleSet, index) = item

          s"return an answer for scenario ${index + 1}" in {

            val actualAnswer = TestPartAndParcelDecisionService.decide(ruleSet.rules.as[PartAndParcel])
            val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

            await(actualAnswer) shouldBe Some(expectedAnswer)
          }
      }
    }

    "decide is called with a PartAndParcel section with None for every value" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = None
        val actualAnswer = TestPartAndParcelDecisionService.decide(PartAndParcel(None, None, None, None))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }

}
