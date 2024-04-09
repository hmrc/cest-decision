/*
 * Copyright 2023 HM Revenue & Customs
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

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import play.api.test.Helpers.{await, defaultAwaitTimeout}
import uk.gov.hmrc.decisionservice.models.PartAndParcel
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PartAndParcelRules

class PartAndParcelRuleEngineSpec extends AnyWordSpecLike with Matchers {

  object TestPartAndParcelRuleEngine extends PartAndParcelRuleEngine

  "PartAndParcelDecisionServiceSpec" when {

    "decide is called with a PartAndParcel section with triggered rules populated" should {

      DecisionServiceVersion.values.foreach { version =>

        s"for rule engine version $version" should {

            s"return the correct expected result" in {

              PartAndParcelRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestPartAndParcelRuleEngine.decide(Some(ruleSet.rules.as[PartAndParcel]))(version)
              val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

              await(actualAnswer) shouldBe Some(expectedAnswer)

            }
          }
        }
      }
    }

    "decide is called with a PartAndParcel section with None for every value" should {

      "return a WeightedAnswer" in {

        val actualAnswer = TestPartAndParcelRuleEngine.decide(Some(PartAndParcel(None, None, None, None)))(DecisionServiceVersion.v1_5_0)

        await(actualAnswer) shouldBe None
      }
    }
  }
}
