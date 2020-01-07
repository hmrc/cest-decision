/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object PossibleSubstituteRejection extends Enumeration with EnumFormats {

  val wouldReject: PossibleSubstituteRejection.Value = Value("wouldReject")
  val wouldNotReject: PossibleSubstituteRejection.Value = Value("wouldNotReject")

  implicit val format: Format[PossibleSubstituteRejection.Value] = enumFormat(PossibleSubstituteRejection)
}