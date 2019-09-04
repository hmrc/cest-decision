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
import uk.gov.hmrc.decisionservice.models.Control
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.decisionservice.ruleSets.ControlRules
import uk.gov.hmrc.play.test.UnitSpec

class ControlRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  lazy val controlRules = app.injector.instanceOf[ControlRules]

  object TestControlRuleEngine extends ControlRuleEngine(controlRules)

  "ControlDecisionServiceSpec" when {

    "decide is called with a Control section with every value provided" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = WeightedAnswerEnum.OUTSIDE_IR35
        val actualAnswer = TestControlRuleEngine.decide(Some(Control(
          Some(MoveWorker.cannotMoveWorkerWithoutNewAgreement),
          Some(HowWorkIsDone.workerDecidesWithoutInput),
          Some(ScheduleOfWorkingHours.workerDecideSchedule),
          Some(ChooseWhereWork.workerChooses)
        )))

        await(actualAnswer) shouldBe Some(expectedAnswer)

      }
    }

    "decide is called with a Control section with triggered rules" should {

      controlRules.ruleSet.zipWithIndex.foreach { item =>

        val (ruleSet, index) = item

        s"return an answer for scenario ${index + 1}" in {

          val actualAnswer = TestControlRuleEngine.decide(Some(ruleSet.rules.as[Control]))
          val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

          await(actualAnswer) shouldBe Some(expectedAnswer)
        }
      }
    }

    "decide is called with a Control section with some values populated" should {

      "return an answer" in {

        val expectedAnswer = WeightedAnswerEnum.OUTSIDE_IR35
        val actualAnswer = TestControlRuleEngine.decide(Some(Control(
          Some(MoveWorker.cannotMoveWorkerWithoutNewAgreement),
          Some(HowWorkIsDone.workerDecidesWithoutInput),
          Some(ScheduleOfWorkingHours.workerDecideSchedule),
          Some(ChooseWhereWork.workerChooses)
        )))

        await(actualAnswer) shouldBe Some(expectedAnswer)
      }
    }

    "decide is called with a Control section with None for every value" should {

      "return a WeightedAnswer" in {

        val actualAnswer = TestControlRuleEngine.decide(Some(Control(None, None, None, None)))

        await(actualAnswer) shouldBe None

      }
    }
  }
}
