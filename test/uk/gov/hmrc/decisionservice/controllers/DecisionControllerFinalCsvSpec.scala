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

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.http.Status
import play.api.libs.json.{JsValue, Json}
import play.api.test.{FakeRequest, Helpers}
import uk.gov.hmrc.decisionservice.model.api.{DecisionRequest, DecisionResponse}
import uk.gov.hmrc.decisionservice.testutil.RequestAndDecision
import uk.gov.hmrc.decisionservice.util.{JsonRequestValidatorFactory, JsonResponseValidatorFactory}
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

trait DecisionControllerCsvSpec extends UnitSpec with WithFakeApplication {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  val decisionController = DecisionController

  def createRequestSendVerifyDecision(path: String, version:String): Unit = {
    val testCasesTry = RequestAndDecision.readFlattenedTransposed(path, version)
    testCasesTry.isSuccess shouldBe true
    val testCase = testCasesTry.get
    val request = testCase.request
    val jsonRequest = toJsonWithValidation(request, version)
    val fakeRequest = FakeRequest(Helpers.POST, "/decide").withBody(jsonRequest)
    val result = decisionController.decide()(fakeRequest)
    status(result) shouldBe Status.OK
    val response = jsonBodyOf(await(result))
    verifyResponse(response, testCase.expectedDecision, version)
  }

  def createMultipleRequestsSendVerifyDecision(path: String, version:String): Unit = {
    val testCasesTry = RequestAndDecision.readAggregatedTransposed(path, version)
    testCasesTry.isSuccess shouldBe true
    val testCases = testCasesTry.get
    testCases.map { testCase =>
      val request = testCase.request
      val jsonRequest = toJsonWithValidation(request, version)
      val fakeRequest = FakeRequest(Helpers.POST, "/decide").withBody(jsonRequest)
      val result = decisionController.decide()(fakeRequest)
      status(result) shouldBe Status.OK
      val response = jsonBodyOf(await(result))
      verifyResponse(response, testCase.expectedDecision, version)
    }
  }

  def verifyDecision(expectedResult: String, decisionResponse: DecisionResponse): Unit

  def verifyResponse(response: JsValue, expectedResult:String, version:String): Unit = {
    val responseString = Json.prettyPrint(response)
    val maybeResponseValidator = JsonResponseValidatorFactory(version)
    maybeResponseValidator.isDefined shouldBe true
    val validationResult = maybeResponseValidator.get.validate(responseString)
    validationResult.isRight shouldBe true
    val jsResult = Json.fromJson[DecisionResponse](response)
    jsResult.isSuccess shouldBe true
    val decisionResponse = jsResult.get
    verifyDecision(expectedResult, decisionResponse)
  }

  def toJsonWithValidation(request:DecisionRequest, version:String):JsValue = {
    val requestJson = Json.toJson(request)
    val requestJsonString = Json.prettyPrint(requestJson)
    val maybeRequestValidator = JsonRequestValidatorFactory(version)
    maybeRequestValidator.isDefined shouldBe true
    val validationResult = maybeRequestValidator.get.validate(requestJsonString)
    println(validationResult)
    validationResult.isRight shouldBe true
    requestJson
  }
}

trait DecisionControllerFinalCsvSpec extends DecisionControllerCsvSpec {
  override def verifyDecision(expectedResult: String, decisionResponse: DecisionResponse): Unit = {
    decisionResponse.result shouldBe expectedResult
  }
}

trait DecisionControllerClusterCsvSpec extends DecisionControllerFinalCsvSpec {
  val clusterName:String
  override def verifyDecision(expectedResult: String, decisionResponse: DecisionResponse): Unit = {
    val maybeClusterResult = decisionResponse.score.get(clusterName)
    maybeClusterResult.isDefined shouldBe true
    maybeClusterResult.map(_.toLowerCase) shouldBe Some(expectedResult.toLowerCase)
  }
}
