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

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.kenshoo.play.metrics.PlayModule
import org.scalatest.mockito.MockitoSugar
import play.api.libs.json.Json.toJson
import play.api.libs.json.{JsValue, Json}
import play.api.test.{FakeRequest, Helpers}
import uk.gov.hmrc.decisionservice.models
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.services._
import uk.gov.hmrc.decisionservice.util.TestFixture
import play.api.http.Status.OK
import play.api.http.Status.BAD_REQUEST
import org.mockito.Mockito.{verify, when}
import org.mockito.ArgumentMatchers.any
import uk.gov.hmrc.decisionservice.models.enums.ResultEnum
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

import scala.concurrent.Future

class NewDecisionControllerSpec extends UnitSpec with WithFakeApplication with MockitoSugar with TestFixture{
  override def bindModules = Seq(new PlayModule)
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val fakeRequest = FakeRequest()
  val json: JsValue = Json.parse("{}")

  val control = mock[ControlDecisionService]
  val exit = mock[ExitDecisionService]
  val financialRisk = mock[FinancialRiskDecisionService]
  val personalService = mock[PersonalServiceDecisionService]
  val partAndParcel = mock[PartAndParcelDecisionService]
  val result = mock[ResultService]

  def controller = new NewDecisionController(stubMessagesControllerComponents(),control, exit, financialRisk, personalService, partAndParcel, result)

  "Decision Controller" must {

    "return a OK with a decision" in {

      when(control.decide(any())).thenReturn(Future.successful(None))
      when(exit.decide(any())).thenReturn(Future.successful(None))
      when(financialRisk.decide(any())).thenReturn(Future.successful(None))
      when(personalService.decide(any())).thenReturn(Future.successful(None))
      when(partAndParcel.decide(any())).thenReturn(Future.successful(None))
      when(result.decide(any(),any(),any(),any(),any())).thenReturn(Future.successful(ResultEnum.UNKNOWN))

      val fakeRequest = FakeRequest(Helpers.POST, "/decide").withBody(toJson(DecisionRequest("","", Interview(
        Setup("","",""),
        Exit(""),
        PersonalService(None,None,None,None,None),
        Control(None,None,None,None),
        FinancialRisk(None,None,None,None,None,None,None),
        models.PartAndParcel(None,None,None,None)
      ))))

      val response = await(controller.decide()(fakeRequest))

      status(response) shouldBe OK
      jsonBodyOf(response) shouldBe Json.parse(
            """{"version":"1.0.0-beta","correlationID":"","score":{},"result":"Unknown"}""".stripMargin
      )
    }

    "return a 400 error" in {

      when(control.decide(any())).thenReturn(Future.successful(None))
      when(exit.decide(any())).thenReturn(Future.successful(None))
      when(financialRisk.decide(any())).thenReturn(Future.successful(None))
      when(personalService.decide(any())).thenReturn(Future.successful(None))
      when(partAndParcel.decide(any())).thenReturn(Future.successful(None))
      when(result.decide(any(),any(),any(),any(),any())).thenReturn(Future.successful(ResultEnum.UNKNOWN))

      val fakeRequest = FakeRequest(Helpers.POST, "/decide").withBody(toJson("{}"))

      val response = await(controller.decide()(fakeRequest))

      status(response) shouldBe BAD_REQUEST
      jsonBodyOf(response) shouldBe Json.parse(
        """{"code":4001,"message":"{\"obj\":[{\"msg\":[\"error.expected.jsobject\"],\"args\":[]}]}"}""".stripMargin
      )
    }
  }
}
