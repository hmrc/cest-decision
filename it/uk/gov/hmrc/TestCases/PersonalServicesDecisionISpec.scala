package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj
import uk.gov.hmrc.decisionservice.models.PersonalService
import uk.gov.hmrc.decisionservice.models.enums.{PossibleSubstituteRejection, WorkerSentActualSubstitute}

class PersonalServicesDecisionISpec extends BaseISpec {

  "Personal Service Section" should {



      s"POST $path" should {

        "Scenario 1: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.yesClientAgreed,
              PersonalService.workerPayActualSubstitute -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 2: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.noSubstitutionHappened,
              PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
              PersonalService.possibleSubstituteWorkerPay -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 3: return a 200, a MEDIUM for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.yesClientAgreed,
              PersonalService.workerPayActualSubstitute -> false,
              PersonalService.wouldWorkerPayHelper -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 4: return a 200, a MEDIUM for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.yesClientAgreed,
              PersonalService.workerPayActualSubstitute -> false,
              PersonalService.wouldWorkerPayHelper -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 5: return a 200, a MEDIUM for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.noSubstitutionHappened,
              PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
              PersonalService.possibleSubstituteWorkerPay -> false,
              PersonalService.wouldWorkerPayHelper -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 6: return a 200, a MEDIUM for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.noSubstitutionHappened,
              PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
              PersonalService.possibleSubstituteWorkerPay -> false,
              PersonalService.wouldWorkerPayHelper -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 7: return a 200, a MEDIUM for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.noSubstitutionHappened,
              PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldReject,
              PersonalService.wouldWorkerPayHelper -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }


        "Scenario 8: return a 200, a HIGH for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.noSubstitutionHappened,
              PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldReject,
              PersonalService.wouldWorkerPayHelper -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
          }
        }

        "Scenario 9: return a 200, a MEDIUM for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
              PersonalService.wouldWorkerPayHelper -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 10: return a 200, a HIGH for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
              PersonalService.wouldWorkerPayHelper -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
          }
        }


        "Scenario 11: return a 200, a OUTOFIR35 for personal services and Outside IR35 result" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
              PersonalService.possibleSubstituteWorkerPay -> true
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"OUTOFIR35"""")
            result.body should include(""""result":"Outside IR35"""")
          }
        }

        "Scenario 12: return a 200, a MEDIUM for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(
              PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
              PersonalService.possibleSubstituteWorkerPay -> false
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
          }
        }

        "Scenario 13: return a 200, a HIGH for personal services" in {

          lazy val res = postRequest(path,
            interview(personalService = obj(PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldReject))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
          }
        }

        "Scenario 14: return a 200, a NotValidUseCase for personal services" in {

          lazy val res = postRequest(path, interview(personalService = obj()))

          whenReady(res) { result =>
            result.status shouldBe OK
          }
        }
      }
    }
  }

