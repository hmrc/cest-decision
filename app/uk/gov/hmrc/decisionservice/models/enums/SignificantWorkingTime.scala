/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object SignificantWorkingTime extends Enumeration with EnumFormats {

  val consumesSignificantAmount: SignificantWorkingTime.Value = Value("consumesSignificantAmount")
  val noSignificantAmount: SignificantWorkingTime.Value = Value("noSignificantAmount")
  val timeButNotMoney: SignificantWorkingTime.Value = Value("timeButNotMoney")
  val moneyButNotTime: SignificantWorkingTime.Value = Value("moneyButNotTime")

  implicit val format: Format[SignificantWorkingTime.Value] = enumFormat(SignificantWorkingTime)
}