/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object IdentifyToStakeholders extends Enumeration with EnumFormats {

  val workForEndClient: IdentifyToStakeholders.Value = Value("workForEndClient")
  val workAsIndependent: IdentifyToStakeholders.Value = Value("workAsIndependent")
  val workAsBusiness: IdentifyToStakeholders.Value = Value("workAsBusiness")
  val wouldNotHappen: IdentifyToStakeholders.Value = Value("wouldNotHappen")

  implicit val format: Format[IdentifyToStakeholders.Value] = enumFormat(IdentifyToStakeholders)
}