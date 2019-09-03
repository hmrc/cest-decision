package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj
import uk.gov.hmrc.decisionservice.models.PartAndParcel
import uk.gov.hmrc.decisionservice.models.enums.IdentifyToStakeholders

class PartAndParcelDecisionISpec extends BaseISpec {

  "Part and Parcel Section" should {
    

      s"POST $path" should {

        "Scenario 1: return a 200, a HIGH for part and parcel" in {

          lazy val res = postRequest(path,
            interview(partAndParcel = obj(
              PartAndParcel.workerReceivesBenefits -> false,
              PartAndParcel.workerAsLineManager -> true,
              PartAndParcel.contactWithEngagerCustomer -> false,
              PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"HIGH"""")
          }
        }

        "Scenario 2: return a 200, a HIGH for part and parcel" in {

          lazy val res = postRequest(path,
            interview(partAndParcel = obj(
              PartAndParcel.workerReceivesBenefits -> true,
              PartAndParcel.workerAsLineManager -> false,
              PartAndParcel.contactWithEngagerCustomer -> false,
              PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"HIGH"""")
          }
        }

        "Scenario 3: return a 200, a LOW for part and parcel" in {

          lazy val res = postRequest(path,
            interview(partAndParcel = obj(
              PartAndParcel.workerReceivesBenefits -> false,
              PartAndParcel.workerAsLineManager -> false,
              PartAndParcel.contactWithEngagerCustomer -> true,
              PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"LOW"""")
          }
        }

        "Scenario 4: return a 200, a MEDIUM for part and parcel" in {

          lazy val res = postRequest(path,
            interview(partAndParcel = obj(
              PartAndParcel.workerReceivesBenefits -> false,
              PartAndParcel.workerAsLineManager -> false,
              PartAndParcel.contactWithEngagerCustomer -> true,
              PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"MEDIUM"""")
          }
        }

        "Scenario 5: return a 200, a LOW for part and parcel" in {

          lazy val res = postRequest(path,
            interview(partAndParcel = obj(
              PartAndParcel.workerReceivesBenefits -> false,
              PartAndParcel.workerAsLineManager -> false,
              PartAndParcel.contactWithEngagerCustomer -> false,
              PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"LOW"""")
          }
        }

        "Scenario 6: return a 200, a LOW for part and parcel" in {

          lazy val res = postRequest(path,
            interview(partAndParcel = obj(
              PartAndParcel.workerReceivesBenefits -> false,
              PartAndParcel.workerAsLineManager -> false,
              PartAndParcel.contactWithEngagerCustomer -> true,
              PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"MEDIUM"""")
          }
        }
      }
    }
  }

