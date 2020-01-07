/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object Section extends Enumeration with EnumFormats {

  val exit: Section.Value = Value("exit")
  val personalService: Section.Value = Value("personalService")
  val control: Section.Value = Value("control")
  val financialRisk: Section.Value = Value("financialRisk")
  val partAndParcel: Section.Value = Value("partAndParcel")
  val businessOnOwnAccount: Section.Value = Value("businessOnOwnAccount")

  implicit val format: Format[Section.Value] = enumFormat(Section)
}