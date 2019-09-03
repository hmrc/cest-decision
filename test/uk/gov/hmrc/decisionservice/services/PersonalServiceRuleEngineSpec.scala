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

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.PersonalService
import uk.gov.hmrc.decisionservice.models.enums.{PossibleSubstituteRejection, WeightedAnswerEnum, WorkerSentActualSubstitute}
import uk.gov.hmrc.decisionservice.ruleEngines.PersonalServiceRuleEngine
import uk.gov.hmrc.decisionservice.ruleSets.PersonalServiceRules
import uk.gov.hmrc.play.test.UnitSpec

class PersonalServiceRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  lazy val personalServiceRules = app.injector.instanceOf[PersonalServiceRules]

  object TestControlDecisionServiceRuleEngine extends PersonalServiceRuleEngine(personalServiceRules)

  "PersonalServiceDecisionServiceSpec" when {

    "decide is called with a PersonalService section with every value provided" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = WeightedAnswerEnum.OUTSIDE_IR35
        val actualAnswer = TestControlDecisionServiceRuleEngine.decide(Some(PersonalService(
          Some(WorkerSentActualSubstitute.yesClientAgreed),
          Some(true),
          Some(PossibleSubstituteRejection.wouldNotReject),
          Some(true),
          Some(true)
        )))

        await(actualAnswer) shouldBe Some(expectedAnswer)

      }
    }

    "decide is called with a PersonalService section with out scenarios populated" should {

      personalServiceRules.ruleSet.zipWithIndex.foreach { item =>

        val (ruleSet, index) = item

        s"return an answer for scenario ${index + 1}" in {

          val actualAnswer = TestControlDecisionServiceRuleEngine.decide(Some(ruleSet.rules.as[PersonalService]))
          val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

          await(actualAnswer) shouldBe Some(expectedAnswer)

        }
      }
    }

    "decide is called with a PersonalService section with None for every value" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = None
        val actualAnswer = TestControlDecisionServiceRuleEngine.decide(Some(PersonalService(None, None, None, None, None)))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }

}
