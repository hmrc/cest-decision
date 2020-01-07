/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ArrangedSubstitute extends Enumeration with EnumFormats {

  val yesClientAgreed: ArrangedSubstitute.Value = Value("yesClientAgreed")
  val notAgreedWithClient: ArrangedSubstitute.Value = Value("notAgreedWithClient")
  val noSubstitutionHappened: ArrangedSubstitute.Value = Value("noSubstitutionHappened")

  implicit val format: Format[ArrangedSubstitute.Value] = enumFormat(ArrangedSubstitute)
}