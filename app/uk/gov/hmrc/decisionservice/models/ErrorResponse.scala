/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class ErrorResponse(code: Int, message: String, details: String)

object ErrorResponse {
  implicit val errorResponseFormat: Format[ErrorResponse] = Json.format[ErrorResponse]
}
