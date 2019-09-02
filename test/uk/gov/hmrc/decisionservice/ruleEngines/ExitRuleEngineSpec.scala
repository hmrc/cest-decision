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
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.enums.ExitEnum
import uk.gov.hmrc.decisionservice.ruleSets.EarlyExitRules
import uk.gov.hmrc.play.test.UnitSpec

class ExitRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  lazy val earlyExitRules = app.injector.instanceOf[EarlyExitRules]

  object TestExitRuleEngine extends ExitRuleEngine(earlyExitRules)

  "ExitDecisionService" when {

    "decide is called with an Exit section" should {

      "returns an INSIDE_IR35" in {

        val actualResult = TestExitRuleEngine.decide(Some(Exit(Some(false))))
        val expectedResult = ExitEnum.CONTINUE

        await(actualResult) shouldBe Some(expectedResult)
      }
    }

    "decide is called with a Exit section with triggered rules" should {

      earlyExitRules.ruleSet.zipWithIndex.foreach { item =>

        val (ruleSet, index) = item

        s"return an answer for scenario ${index + 1}" in {

          val actualAnswer = TestExitRuleEngine.decide(Some(ruleSet.rules.as[Exit]))
          val expectedAnswer = ExitEnum.withName(ruleSet.result)

          await(actualAnswer) shouldBe Some(expectedAnswer)
        }
      }
    }
  }
}
