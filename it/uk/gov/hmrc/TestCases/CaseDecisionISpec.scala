package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers._

class CaseDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with CaseTestData {


  s"For Case 1 a POST /decide}" should {

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

  s"For Case 3 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postRequest("/decide",decisionCase3a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postRequest("/decide",decisionCase3b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postRequest("/decide",decisionCase3c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postRequest("/decide",decisionCase3d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postRequest("/decide",decisionCase3e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3e)
      }
    }

  }


  s"For Case 4 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase4a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase4b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase4c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase4d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase4e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4e)
      }
    }

  }


  s"For Case 5 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase5a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase5b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase5c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase5d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase5e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5e)
      }
    }

  }


  s"For Case 6 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase6a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase6b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase6c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase6d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase6e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6e)
      }
    }

  }


  s"For Case 7 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase7a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase7b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase7c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase7d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase7e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7e)
      }
    }

  }


  s"For Case 8 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase8a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase8b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase8c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase8d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase8e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8e)
      }
    }

  }


  s"For Case 9 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase9a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase9b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase9c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase9d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase9e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9e)
      }
    }

  }


  s"For Case 10 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase10a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase10b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase10c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase10d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase10e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10e)
      }
    }

  }

  //TODO still requires the last case
/*
  s"For Case 11 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase11a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone11)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase11b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone11b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase11c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone11c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase11d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone11d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase11e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone11e)
      }
    }

  }
*/


  s"For Case 12 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase12a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase12b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase12c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase12d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase12e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12e)
      }
    }

  }

  s"For Case 13 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase13a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase13b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase13c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase13d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase13e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13e)
      }
    }

  }

  s"For Case 14 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase14a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase14b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase14c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase14d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase14e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14e)
      }
    }

  }


  s"For Case 15 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase15a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase15b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase15c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase15d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase15e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15e)
      }
    }

  }


  s"For Case 16 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase16a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase16b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase16c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase16d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase16e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16e)
      }
    }

  }

  s"For Case 17 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase17a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase17b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase17c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase17d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase17e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17e)
      }
    }

  }

  s"For Case 18 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase18a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase18b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase18c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase18d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase18e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18e)
      }
    }

  }

  s"For Case 19 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase19a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase19b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase19c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase19d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase19e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19e)
      }
    }

  }

  s"For Case 20 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase20a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase20b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase20c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase20d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase20e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20e)
      }
    }

  }

  s"For Case 21 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase21a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase21b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase21c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase21d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase21e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21e)
      }
    }

  }


  s"For Case 22 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase22a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase22b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22b)
      }
    }

    "return a 200 and continue response given a control request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase22c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase22d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide",decisionCase22e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22e)
      }
    }

  }

  
}
