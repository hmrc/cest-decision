/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

case class DecisionRequest(version: DecisionServiceVersion.Value, correlationID: String, interview: Interview)

object DecisionRequest {
  implicit val questionSetFormat: Format[DecisionRequest] = Json.format[DecisionRequest]
}
