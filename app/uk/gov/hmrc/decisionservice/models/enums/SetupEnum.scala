package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object SetupEnum extends Enumeration with EnumFormats {

  val CONTINUE: SetupEnum.Value = Value("CONTINUE")
  val NOT_VALID_USE_CASE: SetupEnum.Value = Value("NotValidUseCase")
  implicit val format: Format[SetupEnum.Value] = enumFormat(SetupEnum)
}