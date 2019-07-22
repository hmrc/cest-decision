package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, TestData, WiremockHelper}

class PersonalServicesDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with TestData {

  "Personal Service Section" should {

    "POST /decide" should {

      "Scenario 1: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> "Yes"
              )))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("OUTOFIR35", "Outside IR35"))
        }
      }

      "Scenario 2: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> "Yes"
            )))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("OUTOFIR35", "Outside IR35"))
        }
      }

      "Scenario 3: return a 200, a MEDIUM for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> "No",
              "wouldWorkerPayHelper" -> "Yes"
            )))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
        }
      }

      "Scenario 4: return a 200, a MEDIUM for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> "No",
              "wouldWorkerPayHelper" -> "No"
            )))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
        }
      }

      "Scenario 5: return a 200, a MEDIUM for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> "No",
              "wouldWorkerPayHelper" -> "Yes")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
        }
      }

      "Scenario 6: return a 200, a MEDIUM for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> "No",
              "wouldWorkerPayHelper" -> "No")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
        }
      }

      "Scenario 7: return a 200, a MEDIUM for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldReject",
              "wouldWorkerPayHelper" -> "Yes")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
        }
      }


      "Scenario 8: return a 200, a HIGH for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldReject",
              "wouldWorkerPayHelper" -> "No")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("HIGH", "Not Matched"))
        }
      }

      "Scenario 9: return a 200, a MEDIUM for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "notAgreedWithClient",
              "wouldWorkerPayHelper" -> "Yes")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
        }
      }

      "Scenario 10: return a 200, a HIGH for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("workerSentActualSubstitute" -> "notAgreedWithClient",
              "wouldWorkerPayHelper" -> "No")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("HIGH", "Not Matched"))
        }
      }


      "Scenario 11: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> "Yes")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("OUTOFIR35", "Outside IR35"))
        }
      }

      "Scenario 12: return a 200, a MEDIUM for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> "No")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
        }
      }

      "Scenario 13: return a 200, a HIGH for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("possibleSubstituteRejection" -> "wouldReject")))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("HIGH", "Not Matched"))
        }
      }

      "Scenario 14: return a 200, a NotValidUseCase for personal services and Not Matched result" in {

        lazy val res = postRequest("/decide",
          personalServices(Map()))

        whenReady(res) { result =>
          result.status shouldBe OK
          result.body should equal(personalServicesResponse("NotValidUseCase", "Not Matched"))
        }
      }

      "Scenario 15: return a 400, when personal service is sent with an empty answer" in {

        lazy val res = postRequest("/decide",
          personalServices(Map("possibleSubstituteRejection" -> "")))

        whenReady(res) { result =>
          result.status shouldBe BAD_REQUEST
          result.body should equal(errorResponse(6007, "facts have too many empty values"))
        }
      }
    }
  }
}
