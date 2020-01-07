/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object SeriesOfContracts extends Enumeration with EnumFormats {

  val contractIsPartOfASeries: SeriesOfContracts.Value = Value("contractIsPartOfASeries")
  val standAloneContract: SeriesOfContracts.Value = Value("standAloneContract")
  val contractCouldBeExtended: SeriesOfContracts.Value = Value("contractCouldBeExtended")

  implicit val format: Format[SeriesOfContracts.Value] = enumFormat(SeriesOfContracts)
}