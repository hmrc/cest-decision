/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ScheduleOfWorkingHours extends Enumeration with EnumFormats {

  val scheduleDecidedForWorker: ScheduleOfWorkingHours.Value = Value("scheduleDecidedForWorker")
  val workerDecideSchedule: ScheduleOfWorkingHours.Value = Value("workerDecideSchedule")
  val workerAgreeSchedule: ScheduleOfWorkingHours.Value = Value("workerAgreeSchedule")
  val noScheduleRequiredOnlyDeadlines: ScheduleOfWorkingHours.Value = Value("noScheduleRequiredOnlyDeadlines")

  implicit val format: Format[ScheduleOfWorkingHours.Value] = enumFormat(ScheduleOfWorkingHours)
}