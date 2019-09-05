package uk.gov.hmrc.controllers

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.TestCases.BaseISpec
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, TestData, WiremockHelper}
import play.api.libs.json.Json.obj


class DecisionControllerISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with BaseISpec with TestData {

  s"POST $path" should {

    "return a 400 given an invalid request" in {

      lazy val res = postRequest(path, defaultInterview)

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.1.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.1.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.1.1-final version" in {

      lazy val res = postRequest(path, interview(version = "1.1.1-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.2.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.2.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.3.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.3.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.4.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.4.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 200 given a 1.5.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.5.0-final"))

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }
  }
}
