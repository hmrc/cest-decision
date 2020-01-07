/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ExclusiveContract extends Enumeration with EnumFormats {

  val unableToProvideServices: ExclusiveContract.Value = Value("unableToProvideServices")
  val ableToProvideServicesWithPermission: ExclusiveContract.Value = Value("ableToProvideServicesWithPermission")
  val ableToProvideServices: ExclusiveContract.Value = Value("ableToProvideServices")

  implicit val format: Format[ExclusiveContract.Value] = enumFormat(ExclusiveContract)
}