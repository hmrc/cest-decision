/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object WeightedAnswerEnum extends Enumeration with EnumFormats {

  val LOW: WeightedAnswerEnum.Value = Value("LOW")
  val MEDIUM: WeightedAnswerEnum.Value = Value("MEDIUM")
  val HIGH: WeightedAnswerEnum.Value = Value("HIGH")
  val NOT_VALID_USE_CASE: WeightedAnswerEnum.Value = Value("NotValidUseCase")
  val OUTSIDE_IR35: WeightedAnswerEnum.Value = Value("OUTOFIR35")
  val INSIDE_IR35: WeightedAnswerEnum.Value = Value("INIR35")
  val UNKNOWN: WeightedAnswerEnum.Value = Value("Unknown")

  implicit val format: Format[WeightedAnswerEnum.Value] = enumFormat(WeightedAnswerEnum)
}

