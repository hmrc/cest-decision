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

import uk.gov.hmrc.decisionservice.config.ruleSets.ControlRules
import uk.gov.hmrc.decisionservice.models.Control
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.decisionservice.util.ControlRulesSet
import uk.gov.hmrc.play.test.UnitSpec

class ControlDecisionServiceSpec extends UnitSpec {

  object TestControlDecisionService extends ControlDecisionService(new ControlRulesSet)

  "ControlDecisionServiceSpec" when {

    "decide is called with a Control section with every value provided" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = Some(WeightedAnswerEnum.OUTSIDE_IR35)
        val actualAnswer = TestControlDecisionService.decide(Control(
          Some(MoveWorker.cannotMoveWorkerWithoutNewAgreement),
          Some(HowWorkIsDone.workerDecidesWithoutInput),
          Some(ScheduleOfWorkingHours.workerDecideSchedule),
          Some(ChooseWhereWork.workerChooses)
        ))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }

    "decide is called with a Control section with out scenarios populated" should {

      val expectedAnswer = Some(WeightedAnswerEnum.OUTSIDE_IR35)
      val indexedArray = ControlRules.out.value.zipWithIndex

        indexedArray.foreach {
          item =>

            val (jsValue, index) = item

            s"return an answer for scenario ${index + 1}" in {

              val actualAnswer = TestControlDecisionService.decide(jsValue.as[Control])

              await(actualAnswer) shouldBe expectedAnswer

            }
        }
    }

    "decide is called with a Control section with high scenarios populated" should {

      val expectedAnswer = Some(WeightedAnswerEnum.HIGH)
      val indexedArray = ControlRules.high.value.zipWithIndex

        indexedArray.foreach {
          item =>

            val (jsValue, index) = item

            s"return an answer for scenario ${index + 1}" in {

              val actualAnswer = TestControlDecisionService.decide(jsValue.as[Control])

              await(actualAnswer) shouldBe expectedAnswer

            }
        }
    }

    "decide is called with a Control section with medium scenarios populated" should {

      val expectedAnswer = Some(WeightedAnswerEnum.MEDIUM)
      val indexedArray = ControlRules.medium.value.zipWithIndex

        indexedArray.foreach {
          item =>

            val (jsValue, index) = item

            s"return an answer for scenario ${index + 1}" in {

              val actualAnswer = TestControlDecisionService.decide(jsValue.as[Control])

              await(actualAnswer) shouldBe expectedAnswer

            }
        }
    }

    "decide is called with a Control section with some values populated" should {

      "return an answer" in {

        val expectedAnswer = Some(WeightedAnswerEnum.OUTSIDE_IR35)
        val actualAnswer = TestControlDecisionService.decide(Control(
          Some(MoveWorker.cannotMoveWorkerWithoutNewAgreement),
          Some(HowWorkIsDone.workerDecidesWithoutInput),
          Some(ScheduleOfWorkingHours.workerDecideSchedule),
          Some(ChooseWhereWork.workerChooses)
        ))

        await(actualAnswer) shouldBe expectedAnswer
      }
    }

    "decide is called with a Control section with None for every value" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = None
        val actualAnswer = TestControlDecisionService.decide(Control(None, None, None, None))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }
}
