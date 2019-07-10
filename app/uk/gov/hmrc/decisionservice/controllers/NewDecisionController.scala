/*
 * Copyright 2019 HM Revenue & Customs
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

import com.google.inject.Inject
import play.api.Logger
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.{Action, MessagesControllerComponents}
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes._
import uk.gov.hmrc.decisionservice.model.api._
import uk.gov.hmrc.decisionservice.models.enums.SetupEnum
import uk.gov.hmrc.decisionservice.models.{DecisionRequest, _DecisionResponse}
import uk.gov.hmrc.decisionservice.services._
import uk.gov.hmrc.play.bootstrap.controller.FrontendController

import scala.concurrent.{ExecutionContext, Future}


class NewDecisionController @Inject()(mcc: MessagesControllerComponents,
                                      controlDecisionService: ControlDecisionService,
                                      exitDecisionService: ExitDecisionService,
                                      financialRiskDecisionService: FinancialRiskDecisionService,
                                      personalServiceDecisionService: PersonalServiceDecisionService,
                                      partAndParcelDecisionService: PartAndParcelDecisionService,
                                      resultService: ResultService) extends FrontendController(mcc) {

  val version = "1.0.0-beta"

  import uk.gov.hmrc.decisionservice.models.DecisionRequest

  implicit val ec: ExecutionContext = defaultExecutionContext

  def decide(): Action[JsValue] = Action.async(parse.json) { implicit request =>

    request.body.validate[DecisionRequest] match {
      case JsSuccess(validRequest, _) =>

        calculateResult(validRequest).map {
          response =>
            Ok(Json.toJson(response))
        }

      case JsError(jsonErrors) =>
        Logger.info("{\"incorrectRequest\":" + jsonErrors + "}")
        val errorResponseBody = Json.toJson(ErrorResponse(REQUEST_FORMAT, JsError.toJson(jsonErrors).toString()))
        Logger.info(s"incorrect request response: $errorResponseBody")
        Future.successful(BadRequest(errorResponseBody))
    }
  }

  def calculateResult(request: DecisionRequest)(implicit ec: ExecutionContext): Future[_DecisionResponse] = {

    val interview = request.interview

    import uk.gov.hmrc.decisionservice.models.{Score, _DecisionResponse}

    val setup: Option[SetupEnum.Value] = None

    for {

      exit <- exitDecisionService.decide(interview.exit)
      personalService <- personalServiceDecisionService.decide(interview.personalService)
      control <- controlDecisionService.decide(interview.control)
      financialRisk <- financialRiskDecisionService.decide(interview.financialRisk)
      partAndParcel <- partAndParcelDecisionService.decide(interview.partAndParcel)
      result <- resultService.decide(exit, personalService, control, financialRisk, partAndParcel)

    } yield _DecisionResponse(version, request.correlationID, Score(setup, exit, personalService, control, financialRisk, partAndParcel), result)
  }
}
