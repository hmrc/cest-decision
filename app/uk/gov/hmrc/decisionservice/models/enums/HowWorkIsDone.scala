/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object HowWorkIsDone extends Enumeration with EnumFormats {

  val noWorkerInputAllowed: HowWorkIsDone.Value = Value("noWorkerInputAllowed")
  val workerDecidesWithoutInput: HowWorkIsDone.Value = Value("workerDecidesWithoutInput")
  val workerFollowStrictEmployeeProcedures: HowWorkIsDone.Value = Value("workerFollowStrictEmployeeProcedures")
  val workerAgreeWithOthers: HowWorkIsDone.Value = Value("workerAgreeWithOthers")

  implicit val format: Format[HowWorkIsDone.Value] = enumFormat(HowWorkIsDone)
}