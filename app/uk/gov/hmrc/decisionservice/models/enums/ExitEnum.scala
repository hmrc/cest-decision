/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format


object ExitEnum extends Enumeration with EnumFormats{

  val CONTINUE: ExitEnum.Value = Value("CONTINUE")
  val NOT_VALID_USE_CASE: ExitEnum.Value = Value("NotValidUseCase")
  val INSIDE_IR35: ExitEnum.Value = Value("INIR35")
  implicit val format: Format[ExitEnum.Value] = enumFormat(ExitEnum)
}
