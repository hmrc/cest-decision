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

import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.models.Control._
import uk.gov.hmrc.decisionservice.models.enums.MoveWorker._
import uk.gov.hmrc.decisionservice.models.enums.{ChooseWhereWork, HowWorkIsDone, ScheduleOfWorkingHours, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class ControlRulesSpec extends UnitSpec with TestFixture {

  val json = ControlRules.ruleSet

  "Contain all the expected OUT rules" in {

    val actual = (json \ WeightedAnswerEnum.OUTSIDE_IR35).get

    val expected = Json.arr(
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ))

    actual shouldBe expected

  }

  "Contain all the expected HIGH rules" in {

    val actual = (json \ WeightedAnswerEnum.HIGH).get

    val expected = Json.arr(
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      )
    )

    actual shouldBe expected

  }

  "Contain all the expected MEDIUM rules" in {

    val actual = (json \ WeightedAnswerEnum.MEDIUM).get

    val expected = Json.arr(
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      )
    )

    actual shouldBe expected

  }


}
