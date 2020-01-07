/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object WorkerMainIncome extends Enumeration with EnumFormats {

  val incomeCalendarPeriods: WorkerMainIncome.Value = Value("incomeCalendarPeriods")
  val incomeFixed: WorkerMainIncome.Value = Value("incomeFixed")
  val incomePieceRate: WorkerMainIncome.Value = Value("incomePieceRate")
  val incomeCommission: WorkerMainIncome.Value = Value("incomeCommission")
  val incomeProfitOrLosses: WorkerMainIncome.Value = Value("incomeProfitOrLosses")

  implicit val format: Format[WorkerMainIncome.Value] = enumFormat(WorkerMainIncome)
}