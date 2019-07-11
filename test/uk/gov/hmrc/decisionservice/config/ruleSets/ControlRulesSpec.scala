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

package uk.gov.hmrc.decisionservice.config.ruleSets

import play.api.libs.json.{JsValue, Json}
import play.twirl.api.JavaScript
import uk.gov.hmrc.decisionservice.config.ruleSets.js.ControlRules
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class ControlRulesSpec extends UnitSpec with TestFixture {

  val testControlRules: JavaScript = ControlRules()

  val json = Json.parse(testControlRules.body)

  "Contain all the expected OUT rules" in {

    val actual = (json \ "OutOfIR35").as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    }
        |  ]
    """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected HIGH rules" in {

    val actual = (json \ "HIGH").as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected MEDIUM rules" in {

    val actual = (json \ "MEDIUM").as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "canMoveWorkerWithoutPermission",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerDecidesWithoutInput",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerAgreeWithOthers",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "noWorkerInputAllowed",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "scheduleDecidedForWorker",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerChooses"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "noLocationRequired"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerAgreeSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "workerDecideSchedule",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerCannotChoose"
        |    },
        |    {
        |      "engagerMovingWorker": "cannotMoveWorkerWithoutNewAgreement",
        |      "workerDecidingHowWorkIsDone": "workerFollowStrictEmployeeProcedures",
        |      "whenWorkHasToBeDone": "noScheduleRequiredOnlyDeadlines",
        |      "workerDecideWhere": "workerAgreeWithOthers"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }


}
