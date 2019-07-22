package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, TestData, WiremockHelper}

class CaseDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with TestData {

  s"For Case 1 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postRequest("/decide",decisionCase1a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone1)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postRequest("/decide",decisionCase1b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone1b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postRequest("/decide",decisionCase1c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone1c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postRequest("/decide",decisionCase1d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone1d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postRequest("/decide",decisionCase1e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone1e)
      }
    }

  }

  s"For Case 2 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postRequest("/decide",decisionCase2a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postRequest("/decide",decisionCase2b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postRequest("/decide",decisionCase2c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postRequest("/decide",decisionCase2d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postRequest("/decide",decisionCase2e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2e)
      }
    }

  }


}
