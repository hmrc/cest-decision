/*
 * Copyright 2018 HM Revenue & Customs
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


import org.joda.time.DateTime
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import play.api.http.Status
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.test.{FakeRequest, Helpers}
import reactivemongo.api.commands.{DefaultWriteResult, WriteError}
import uk.gov.hmrc.decisionservice.model.analytics.{Exit, Interview, InterviewSearch, Setup}
import uk.gov.hmrc.decisionservice.repository.InterviewRepository
import uk.gov.hmrc.play.test.UnitSpec
import org.mockito.Matchers._
import org.mockito.Mockito._
import uk.gov.hmrc.decisionservice.model.analytics.InterviewFormat._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class AnalyticsControllerSpec extends UnitSpec with MockitoSugar with ScalaFutures{

  val repository = mock[InterviewRepository]
  val analyticsController = new AnalyticsController(repository)
  val okResult = DefaultWriteResult(true, 0, Seq.empty, None, None, None)
  val failResult = DefaultWriteResult(false, 1, Seq(WriteError(1, 1, "Error")), None, None, Some("Error"))

  private val exit = Exit("Yes")
  private val setup = Setup("personDoingWork","Yes","partnership")
  private val interview = Interview("0.1", "jghdfsu@#", "IR35", "OUT", Option("123"), setup, exit, Option.empty,
    Option.empty, Option.empty, Option.empty, new DateTime())

  private val interviewSearch = InterviewSearch("1.5.0-final", new DateTime(), new DateTime())

  val  interviewWithMissingRequiredFields = """
                                 |{
                                 |  "version": "1.5",
                                 |  "compressedInterview": "laborum ullamco mollit laboris",
                                 |  "route": "cupidatat",
                                 |  "decision": "voluptate",
                                 |  "setup": {
                                 |    "endUserRole": "placingAgency",
                                 |    "hasContractStarted": "Yes",
                                 |    "provideServices": "intermediary"
                                 |  }
                                 |}
                               """.stripMargin

  val  interviewSearchWithMissingRequiredFields = """
        |{
        |  "version": "1.5.0-final",
        |  "start": "2017-06-27 11:00:00"
        |}
        """.stripMargin

  val validLogInterviewRequest: FakeRequest[JsValue] = FakeRequest(Helpers.POST, "/log").withBody(toJson(interview))
  val validLogInterviewSearchRequest: FakeRequest[JsValue] = FakeRequest(Helpers.POST, "/search").withBody(toJson(interviewSearch))
  val invalidJsonLogInterviewRequest: FakeRequest[JsValue] = FakeRequest(Helpers.POST, "/log").withBody(toJson(interviewWithMissingRequiredFields))
  val invalidJsonSearchInterviewRequest: FakeRequest[JsValue] = FakeRequest(Helpers.POST, "/search").withBody(toJson(interviewSearchWithMissingRequiredFields))

  "The AnalyticsController" should {
    "respond with 200 OK when an Interview is received" in {
      when(repository.save(any(classOf[Interview]))).thenReturn(Future.successful(okResult))
      val result = analyticsController.logInterview(validLogInterviewRequest)
      status(result) shouldBe Status.OK
    }

    "respond with 500 Internal Server Error when not able to save due to a DB error" in {
      when(repository.save(any(classOf[Interview]))).thenReturn(Future.successful(failResult))
      val result = analyticsController.logInterview(validLogInterviewRequest)
      status(result) shouldBe Status.INTERNAL_SERVER_ERROR
    }

    "respond with 400 Bad Request when json is not valid" in {
      val result = analyticsController.logInterview(invalidJsonSearchInterviewRequest)
      status(result) shouldBe Status.BAD_REQUEST
    }

    "respond with 400 Bad Request when json is not valid for interview search" in {
      val result = analyticsController.searchInterview(invalidJsonSearchInterviewRequest)
      status(result) shouldBe Status.BAD_REQUEST
    }

    "respond with 200 OK when json is valid for interview search" in {
      when(repository.get(any(classOf[InterviewSearch]))).thenReturn(Future(List()))
      val result = analyticsController.searchInterview(validLogInterviewSearchRequest)
      status(result) shouldBe Status.OK
    }

  }

}
