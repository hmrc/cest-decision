/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums.{ChooseWhereWork, HowWorkIsDone, MoveWorker, ScheduleOfWorkingHours}

case class Control(engagerMovingWorker: Option[MoveWorker.Value],
                   workerDecidingHowWorkIsDone: Option[HowWorkIsDone.Value],
                   whenWorkHasToBeDone: Option[ScheduleOfWorkingHours.Value],
                   workerDecideWhere: Option[ChooseWhereWork.Value])

object Control {
  implicit val format: Format[Control] = Json.format[Control]

  val engagerMovingWorker = "engagerMovingWorker"
  val workerDecidingHowWorkIsDone = "workerDecidingHowWorkIsDone"
  val whenWorkHasToBeDone = "whenWorkHasToBeDone"
  val workerDecideWhere = "workerDecideWhere"
}
