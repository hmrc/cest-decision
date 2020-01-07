/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object HowWorkerIsPaid extends Enumeration with EnumFormats {

  val hourlyDailyOrWeekly: HowWorkerIsPaid.Value = Value("incomeCalendarPeriods")
  val fixedPrice: HowWorkerIsPaid.Value = Value("incomeFixed")
  val pieceRate: HowWorkerIsPaid.Value = Value("incomePieceRate")
  val commission: HowWorkerIsPaid.Value = Value("incomeCommission")
  val profitOrLosses: HowWorkerIsPaid.Value = Value("incomeProfitOrLosses")

  implicit val format: Format[HowWorkerIsPaid.Value] = enumFormat(HowWorkerIsPaid)
}