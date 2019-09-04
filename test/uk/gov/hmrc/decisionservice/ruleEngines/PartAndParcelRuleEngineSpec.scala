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

package uk.gov.hmrc.decisionservice.ruleEngines

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.PartAndParcel
import uk.gov.hmrc.decisionservice.models.enums.{WeightedAnswerEnum, WorkerRepresentsEngagerBusiness}
import uk.gov.hmrc.decisionservice.ruleSets.PartAndParcelRules
import uk.gov.hmrc.play.test.UnitSpec

class PartAndParcelRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  lazy val partAndParcelRules = app.injector.instanceOf[PartAndParcelRules]

  object TestPartAndParcelRuleEngine extends PartAndParcelRuleEngine(partAndParcelRules)

  "PartAndParcelDecisionServiceSpec" when {

    "decide is called with a PartAndParcel section with every value provided" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = WeightedAnswerEnum.MEDIUM
        val actualAnswer = TestPartAndParcelRuleEngine.decide(Some(PartAndParcel(
          Some(false),
          Some(false),
          Some(true),
          Some(WorkerRepresentsEngagerBusiness.workForEndClient)
        )))

        await(actualAnswer) shouldBe Some(expectedAnswer)
      }
    }

    "decide is called with a PartAndParcel section with triggered rules populated" should {

      partAndParcelRules.ruleSet.zipWithIndex.foreach { item =>

          val (ruleSet, index) = item

          s"return an answer for scenario ${index + 1}" in {

            val actualAnswer = TestPartAndParcelRuleEngine.decide(Some(ruleSet.rules.as[PartAndParcel]))
            val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

            await(actualAnswer) shouldBe Some(expectedAnswer)

          }
      }
    }

    "decide is called with a PartAndParcel section with None for every value" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = None
        val actualAnswer = TestPartAndParcelRuleEngine.decide(Some(PartAndParcel(None, None, None, None)))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }

}
