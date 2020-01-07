/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ProvideServices extends Enumeration with EnumFormats {

  val limitedCompany: ProvideServices.Value = Value("limitedCompany")
  val partnership: ProvideServices.Value = Value("partnership")
  val intermediary: ProvideServices.Value = Value("throughIndividual")
  val soleTrader: ProvideServices.Value = Value("soleTrader")

  implicit val format: Format[ProvideServices.Value] = enumFormat(ProvideServices)
}