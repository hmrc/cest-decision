/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.decisionservice.controllers

import java.util.concurrent.atomic.AtomicInteger

import cats.data.Validated
import org.slf4j.MDC
import play.api.Logger
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.Action
import uk.gov.hmrc.decisionservice.{Validation, DecisionServiceVersions}
import uk.gov.hmrc.decisionservice.model.VersionError
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes._
import uk.gov.hmrc.decisionservice.model.api._
import uk.gov.hmrc.decisionservice.model.rules.{>>>, Facts}
import uk.gov.hmrc.decisionservice.ruleengine.RuleEngineDecision
import uk.gov.hmrc.decisionservice.services._
import uk.gov.hmrc.play.microservice.controller.BaseController

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



trait DecisionController extends BaseController {
  val decisionServices: Map[String, DecisionService]
  val id: AtomicInteger = new AtomicInteger

  def decide() = Action.async(parse.json) { implicit request =>
    request.body.validate[DecisionRequest] match {
      case JsSuccess(req, _) =>
        MDC.put("correlation", req.correlationID)
        Logger.info(s"request: ${request.body.toString.replaceAll("\"", "")}")
        doDecide(req).map {
          case Validated.Valid(decision) =>
            val response = decisionToResponse(req, decision)
            val responseBody = Json.toJson(response)
            Logger.info(s"response: ${responseBody.toString.replaceAll("\"", "")}")
            Logger.info(s"${response.result}")
            Ok(responseBody)
          case Validated.Invalid(error) =>
            val errorResponse = ErrorResponse(error(0).code, error(0).message)
            val errorResponseBody = Json.toJson(errorResponse)
            Logger.info(s"error response: ${errorResponseBody}")
            BadRequest(errorResponseBody)
        }
      case JsError(jsonErrors) =>
        Logger.info("{\"incorrectRequest\":" + jsonErrors + "}")
        val errorResponseBody = Json.toJson(ErrorResponse(REQUEST_FORMAT, JsError.toJson(jsonErrors).toString()))
        Logger.info(s"incorrect request response: ${errorResponseBody}")
        Future.successful(BadRequest(errorResponseBody))
    }
  }

  def doDecide(decisionRequest: DecisionRequest): Future[Validation[RuleEngineDecision]] = Future {
    decisionServiceInstance(decisionRequest.version).fold[Validation[RuleEngineDecision]]{
      Validated.Invalid(List(VersionError(ErrorCodes.INVALID_VERSION, s"not supported version ${decisionRequest.version}")))} {
      decisionService => requestToFacts(decisionRequest) ==>: decisionService
    }
  }

  def decisionServiceInstance(version: String): Option[DecisionService] = {
    decisionServices.get(version)
  }

  def requestToFacts(decisionRequest: DecisionRequest): Facts = {
    val listsOfStringPairs = decisionRequest.interview.toList.collect { case (a, b) => b.toList }.flatten
    Facts(listsOfStringPairs.collect { case (a, b) => (a, >>>(b)) }.toMap)
  }

  def decisionToResponse(decisionRequest: DecisionRequest, ruleEngineDecision: RuleEngineDecision): DecisionResponse = {
    DecisionResponse(
      decisionRequest.version,
      decisionRequest.correlationID,
      Score.create(ruleEngineDecision.facts, decisionRequest.version), responseString(ruleEngineDecision))
  }

  def responseString(ruleEngineDecision: RuleEngineDecision): String = ruleEngineDecision.value.toLowerCase match {
    case "inir35" => "Inside IR35"
    case "outofir35" => "Outside IR35"
    case "unknown" => "Unknown"
    case _ => "Not Matched"
  }
}

object DecisionController extends DecisionController {
  lazy val decisionServices = Map(
    DecisionServiceVersions.VERSION110_FINAL -> DecisionServiceInstance110Final,
    DecisionServiceVersions.VERSION111_FINAL -> DecisionServiceInstance111Final,
    DecisionServiceVersions.VERSION120_FINAL -> DecisionServiceInstance120Final,
    DecisionServiceVersions.VERSION130_FINAL -> DecisionServiceInstance130Final,
    DecisionServiceVersions.VERSION140_FINAL -> DecisionServiceInstance140Final,
    DecisionServiceVersions.VERSION150_FINAL -> DecisionServiceInstance150Final
  )
}

