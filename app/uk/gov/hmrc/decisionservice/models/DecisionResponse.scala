/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Json, OFormat}
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, ResultEnum}

case class DecisionResponse(version: DecisionServiceVersion.Value,
                            correlationID: String,
                            score: Score,
                            result: ResultEnum.Value,
                            resultWithoutBooa: ResultEnum.Value)

object DecisionResponse {
  implicit val formats: OFormat[DecisionResponse] = Json.format[DecisionResponse]
}
