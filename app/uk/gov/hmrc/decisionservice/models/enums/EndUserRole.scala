/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object EndUserRole extends Enumeration with EnumFormats {

  val personDoingWork: EndUserRole.Value = Value("personDoingWork")
  val endClient: EndUserRole.Value = Value("endClient")
  val placingAgency: EndUserRole.Value = Value("placingAgency")

  implicit val format: Format[EndUserRole.Value] = enumFormat(EndUserRole)
}