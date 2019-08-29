package uk.gov.hmrc.controllers

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, TestData, WiremockHelper}

class DecisionControllerISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with TestData {

  s"POST /decide" should {

    "return a 400 given an invalid request" in {

      lazy val res = postRequest("/decide",defaultInterview)

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 200 given a 1.1.0-final version" in {

      lazy val res = postRequest("/decide",decisionVersion1)

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

    "return a 200 given a 1.1.1-final version" in {

      lazy val res = postRequest("/decide",decisionVersion11)

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

    "return a 200 given a 1.2.0-final version" in {

      lazy val res = postRequest("/decide",decisionVersion2)

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

    "return a 200 given a 1.3.0-final version" in {

      lazy val res = postRequest("/decide",decisionVersion3)

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

    "return a 200 given a 1.4.0-final version" in {

      lazy val res = postRequest("/decide",decisionVersion4)

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

    "return a 200 given a 1.5.0-final version" in {

      lazy val res = postRequest("/decide",decisionVersion5)

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

  }

}
