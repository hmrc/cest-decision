/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object RejectSubstitute extends Enumeration with EnumFormats {

  val wouldNotReject: RejectSubstitute.Value = Value("wouldNotReject")
  val wouldReject: RejectSubstitute.Value = Value("wouldReject")

  implicit val format: Format[RejectSubstitute.Value] = enumFormat(RejectSubstitute)
}