package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class DecisionRequest(version:String, correlationID:String, interview: Interview)

object DecisionRequest {
  implicit val questionSetFormat: Format[DecisionRequest] = Json.format[DecisionRequest]
}