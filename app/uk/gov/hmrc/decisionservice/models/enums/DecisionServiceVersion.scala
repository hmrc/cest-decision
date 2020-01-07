/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object DecisionServiceVersion extends Enumeration with EnumFormats {

  val v1_5_0: DecisionServiceVersion.Value = Value("1.5.0-final")
  val v2_4: DecisionServiceVersion.Value = Value("2.4")

  implicit val format: Format[DecisionServiceVersion.Value] = enumFormat(DecisionServiceVersion)
}