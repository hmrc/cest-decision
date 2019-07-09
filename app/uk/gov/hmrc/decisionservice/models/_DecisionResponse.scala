package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Json, OFormat}
import uk.gov.hmrc.decisionservice.models.enums.ResultEnum

case class _DecisionResponse(version: String, correlationID: String, score: Score, result: ResultEnum.Value)

object _DecisionResponse {
  implicit val formats: OFormat[_DecisionResponse] = Json.format[_DecisionResponse]
}
