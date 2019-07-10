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

import javax.inject.Inject
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.{Action, MessagesControllerComponents}
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes._
import uk.gov.hmrc.decisionservice.model.api._
import uk.gov.hmrc.decisionservice.models._DecisionResponse
import uk.gov.hmrc.decisionservice.models.enums.SetupEnum
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
  def correlationId = ""

  def decide(): Action[JsValue] = Action.async(parse.json) { implicit request =>

    import uk.gov.hmrc.decisionservice.models.DecisionRequest

    request.body.validate[DecisionRequest] match {
      case JsSuccess(validRequest, _) =>

        calculateResult(validRequest.interview).map {
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

  import uk.gov.hmrc.decisionservice.models.Interview

  def calculateResult(interview: Interview)(implicit ec: ExecutionContext): Future[_DecisionResponse] = {

    import uk.gov.hmrc.decisionservice.models.{Score, _DecisionResponse}

    val setup: Option[SetupEnum.Value] = None

    for {

      exit <- exitDecisionService.decide(interview.exit)
      personalService <- personalServiceDecisionService.decide(interview.personalService)
      control <- controlDecisionService.decide(interview.control)
      financialRisk <- financialRiskDecisionService.decide(interview.financialRisk)
      partAndParcel <- partAndParcelDecisionService.decide(interview.partAndParcel)
      result <- resultService.decide(exit, personalService, control, financialRisk, partAndParcel)

    } yield _DecisionResponse(version, correlationId, Score(setup, exit, personalService, control, financialRisk, partAndParcel), result
    )
  }
}
