package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.json.Json
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.decisionservice.model.api.DecisionRequest
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, TestData, WiremockHelper}

class PersonalServicesDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with TestData {

  implicit def booleanToStr(value: Boolean)(implicit engine: DecisionEngine) = engine match {
    case NewRuleEngine => value.toString
    case OldRuleEngine => if(value) "Yes" else "No"
  }

  sealed trait DecisionEngine {
    val path: String
  }
  case object NewRuleEngine extends DecisionEngine {
    override val path = "/decide/new"
  }
  case object OldRuleEngine extends DecisionEngine {
    override val path = "/decide"
  }

  val decisionEngines = Seq(OldRuleEngine, NewRuleEngine)

  //TODO - Personal Services Section
  def personalServices(personalService: Map[String, String])(implicit engine: DecisionEngine) = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
    "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> true,"provideServices" -> "soleTrader"),
    "exit" -> Map {"officeHolder" -> false},
    "personalService" -> personalService)))

  def personalServicesResponse(personalServiceResult: String, result: String) = s"""{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"$personalServiceResult","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"$result"}"""


  "Personal Service Section" should {

    decisionEngines.foreach { implicit engine =>

      s"POST ${engine.path}" should {

        "Scenario 1: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> true
            )))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("OUTOFIR35", "Outside IR35"))
          }
        }

        "Scenario 2: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> true
            )))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("OUTOFIR35", "Outside IR35"))
          }
        }

        "Scenario 3: return a 200, a MEDIUM for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> false,
              "wouldWorkerPayHelper" -> true
            )))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
          }
        }

        "Scenario 4: return a 200, a MEDIUM for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> false,
              "wouldWorkerPayHelper" -> false
            )))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
          }
        }

        "Scenario 5: return a 200, a MEDIUM for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> false,
              "wouldWorkerPayHelper" -> true)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
          }
        }

        "Scenario 6: return a 200, a MEDIUM for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> false,
              "wouldWorkerPayHelper" -> false)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
          }
        }

        "Scenario 7: return a 200, a MEDIUM for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldReject",
              "wouldWorkerPayHelper" -> true)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
          }
        }


        "Scenario 8: return a 200, a HIGH for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldReject",
              "wouldWorkerPayHelper" -> false)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("HIGH", "Not Matched"))
          }
        }

        "Scenario 9: return a 200, a MEDIUM for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "notAgreedWithClient",
              "wouldWorkerPayHelper" -> true)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
          }
        }

        "Scenario 10: return a 200, a HIGH for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("workerSentActualSubstitute" -> "notAgreedWithClient",
              "wouldWorkerPayHelper" -> false)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("HIGH", "Not Matched"))
          }
        }


        "Scenario 11: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> true)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("OUTOFIR35", "Outside IR35"))
          }
        }

        "Scenario 12: return a 200, a MEDIUM for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> false)))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("MEDIUM", "Not Matched"))
          }
        }

        "Scenario 13: return a 200, a HIGH for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("possibleSubstituteRejection" -> "wouldReject")))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("HIGH", "Not Matched"))
          }
        }

        "Scenario 14: return a 200, a NotValidUseCase for personal services and Not Matched result" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map()))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should equal(personalServicesResponse("NotValidUseCase", "Not Matched"))
          }
        }

        "Scenario 15: return a 400, when personal service is sent with an empty answer" in {

          lazy val res = postRequest(engine.path,
            personalServices(Map("possibleSubstituteRejection" -> "")))

          whenReady(res) { result =>
            result.status shouldBe BAD_REQUEST
            result.body should equal(errorResponse(6007, "facts have too many empty values"))
          }
        }
      }
    }
  }
}
