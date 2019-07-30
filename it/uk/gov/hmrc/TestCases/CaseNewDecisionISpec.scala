package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers._

class CaseNewDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with CaseNewTestData {


  s"For Case 1 a POST /decide/new}" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase1a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase1e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 2 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase2a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase2e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 3 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase3a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase3e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"OUTOFIR35"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 4 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase4a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase4e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 5 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase5a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase5e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"OUTOFIR35"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 6 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase6a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase6e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 7 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase7a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase7e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 8 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase8a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase8e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"HIGH"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 9 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase9a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase9e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 10 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase10a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase10e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  //TODO still requires the last case
/*
  s"For Case 11 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase11a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase11e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }
*/


  s"For Case 12 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase12a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase12e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 13 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase13a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase13e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 14 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase14a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase14e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 15 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase15a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase15e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 16 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase16a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase16e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 17 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase17a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase17e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 18 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase18a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase18e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 19 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase19a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase19e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 20 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase20a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase20e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Unknown"""")
      }
    }

  }

  s"For Case 21 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase21a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase21e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 22 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase22a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      lazy val res = postFullJsonRequest("/decide/new",decisionCase22e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")

      }
    }

  }

  
}
