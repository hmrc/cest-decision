package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj

class PersonalServicesDecisionISpec extends BaseISpec {

  "Personal Service Section" should {

    Seq(OldRuleEngine, NewRuleEngine).foreach { implicit engine =>

      s"POST ${engine.path}" should {

        "Scenario 1: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 2: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 3: return a 200, a MEDIUM for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> false,
              "wouldWorkerPayHelper" -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 4: return a 200, a MEDIUM for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "yesClientAgreed",
              "workerPayActualSubstitute" -> false,
              "wouldWorkerPayHelper" -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 5: return a 200, a MEDIUM for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> false,
              "wouldWorkerPayHelper" -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 6: return a 200, a MEDIUM for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> false,
              "wouldWorkerPayHelper" -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 7: return a 200, a MEDIUM for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldReject",
              "wouldWorkerPayHelper" -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }


        "Scenario 8: return a 200, a HIGH for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "noSubstitutionHappened",
              "possibleSubstituteRejection" -> "wouldReject",
              "wouldWorkerPayHelper" -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
          }
        }

        "Scenario 9: return a 200, a MEDIUM for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "notAgreedWithClient",
              "wouldWorkerPayHelper" -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 10: return a 200, a HIGH for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "workerSentActualSubstitute" -> "notAgreedWithClient",
              "wouldWorkerPayHelper" -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
          }
        }


        "Scenario 11: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 12: return a 200, a MEDIUM for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj(
              "possibleSubstituteRejection" -> "wouldNotReject",
              "possibleSubstituteWorkerPay" -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 13: return a 200, a HIGH for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path,
            interview(personalService = obj("possibleSubstituteRejection" -> "wouldReject"))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
          }
        }

        "Scenario 14: return a 200, a NotValidUseCase for personal services and Unknown result" in {

          lazy val res = postRequest(engine.path, interview(personalService = obj()))

          whenReady(res) { result =>
            result.status shouldBe OK
          }
        }
      }
    }
  }
}
