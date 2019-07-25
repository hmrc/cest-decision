package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj

class PartAndParcelDecisionISpec extends BaseISpec {

  "Part and Parcel Section" should {

    Seq(OldRuleEngine, NewRuleEngine).foreach { implicit engine =>

      s"POST ${engine.path}" should {

        "Scenario 1: return a 200, a HIGH for part and parcel" in {

          lazy val res = postRequest(engine.path,
            interview(partAndParcel = obj(
              "workerReceivesBenefits" -> false,
              "workerAsLineManager" -> true,
              "contactWithEngagerCustomer" -> false,
              "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"HIGH"""")
          }
        }

        "Scenario 2: return a 200, a HIGH for part and parcel" in {

          lazy val res = postRequest(engine.path,
            interview(partAndParcel = obj(
              "workerReceivesBenefits" -> true,
              "workerAsLineManager" -> false,
              "contactWithEngagerCustomer" -> false,
              "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"HIGH"""")
          }
        }

        "Scenario 3: return a 200, a LOW for part and parcel" in {

          lazy val res = postRequest(engine.path,
            interview(partAndParcel = obj(
              "workerReceivesBenefits" -> false,
              "workerAsLineManager" -> false,
              "contactWithEngagerCustomer" -> true,
              "workerRepresentsEngagerBusiness" -> "workAsIndependent"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"LOW"""")
          }
        }

        "Scenario 4: return a 200, a MEDIUM for part and parcel" in {

          lazy val res = postRequest(engine.path,
            interview(partAndParcel = obj(
              "workerReceivesBenefits" -> false,
              "workerAsLineManager" -> false,
              "contactWithEngagerCustomer" -> true,
              "workerRepresentsEngagerBusiness" -> "workForEndClient"
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"MEDIUM"""")
          }
        }

        "Scenario 5: return a 200, a LOW for part and parcel" in {

          lazy val res = postRequest(engine.path,
            interview(partAndParcel = obj(
              "workerReceivesBenefits" -> false,
              "workerAsLineManager" -> false,
              "contactWithEngagerCustomer" -> false,
              "workerRepresentsEngagerBusiness" -> ""
            ))
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""partAndParcel":"LOW"""")
          }
        }

        "Scenario 6: return a 200, a LOW for part and parcel" in {

          lazy val res = postRequest(engine.path,
            interview(partAndParcel = obj(
              "workerReceivesBenefits" -> false,
              "workerAsLineManager" -> false,
              "contactWithEngagerCustomer" -> true,
              "workerRepresentsEngagerBusiness" -> "workAsBusiness"
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
}
