/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.controllers

import com.google.inject.Inject
import play.api.Logger
import play.api.libs.json._
import play.api.mvc.{Action, MessagesControllerComponents}
import uk.gov.hmrc.decisionservice.models.{DecisionRequest, ErrorResponse}
import uk.gov.hmrc.decisionservice.services._
import uk.gov.hmrc.play.bootstrap.controller.BackendController

import scala.concurrent.{ExecutionContext, Future}

class DecisionController @Inject()(mcc: MessagesControllerComponents,
                                   service: DecisionService) extends BackendController(mcc) {

  implicit val ec: ExecutionContext = defaultExecutionContext

  def decide(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[DecisionRequest] match {
      case JsSuccess(validRequest, _) =>
        Logger.info("Valid interview request to decide API")
        service.calculateResult(validRequest).map(response => Ok(Json.toJson(response)))
      case JsError(jsonErrors) =>
        val errorDetails = s"""{"incorrectRequest":$jsonErrors}"""
        val errorResponseBody = Json.toJson(ErrorResponse(BAD_REQUEST, JsError.toJson(jsonErrors).toString(),errorDetails))
        Logger.error(s"incorrect request response: $errorResponseBody")
        Future.successful(BadRequest(errorResponseBody))
    }
  }
}
