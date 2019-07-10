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

import org.scalatest.mockito.MockitoSugar
import play.api.http.Status.BAD_REQUEST
import play.api.libs.json.{JsValue, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.decisionservice.services._
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class NewDecisionControllerSpec extends TestFixture with MockitoSugar with UnitSpec {

  val fakeRequest = FakeRequest()
  val json: JsValue = Json.parse("{}")

  val control = mock[ControlDecisionService]
  val exit = mock[ExitDecisionService]
  val financialRisk = mock[FinancialRiskDecisionService]
  val personalService = mock[PersonalServiceDecisionService]
  val partAndParcel = mock[PartAndParcelDecisionService]
  val result = mock[ResultService]

  def controller() = new NewDecisionController(stubMessagesControllerComponents(),control, exit, financialRisk, personalService, partAndParcel, result)

  "Decision Controller" must {

    "return a decision" in {

      val response = controller().decide()(fakeRequest.withJsonBody(json))

      response.map{
        result =>

          status(result) shouldBe BAD_REQUEST
          contentAsJson(result) shouldBe Json.parse(
            """
              |{
              | "code": "INVALID_SERVICE",
              | "message": "The provided service is not valid for this operation"
              |}
            """.stripMargin)
      }
    }
  }
}
