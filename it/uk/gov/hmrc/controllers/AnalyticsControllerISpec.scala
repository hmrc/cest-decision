package uk.gov.hmrc.controllers

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, TestData, WiremockHelper}

class AnalyticsControllerISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with TestData {

  s"POST /log" should {

    "return a 204 given a successful post" in {

      lazy val res = postRequest("/log",defaultInterview)

      whenReady(res) { result =>
        result.status shouldBe NO_CONTENT
      }
    }

    "return a 400 given a bad form" in {

      lazy val res = postRequest("/log",defaultInterviewSearch)

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }
  }

  s"POST /search" should {

    "return a 200 given a successful post" in {

      lazy val res = postRequest("/search",defaultInterviewSearch)

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

    "return a 400 given a bad request" in {

      lazy val res = postRequest("/search",defaultInterview)

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }
  }
}
