package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj

class MatrixOfMatricesDecisionISpec extends BaseISpec {

  "Matrix of Matrices" should {

    Seq(OldRuleEngine, NewRuleEngine).foreach { implicit engine =>

      s"POST ${engine.path}" should {

        "Scenario 1: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "possibleSubstituteRejection" -> "wouldNotReject",
                "possibleSubstituteWorkerPay" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "noLocationRequired"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> true,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 2: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "possibleSubstituteRejection" -> "wouldNotReject",
                "possibleSubstituteWorkerPay" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "noLocationRequired"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workForEndClient"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 3: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "possibleSubstituteRejection" -> "wouldNotReject",
                "possibleSubstituteWorkerPay" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "noLocationRequired"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workAsBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 4: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "possibleSubstituteRejection" -> "wouldNotReject",
                "possibleSubstituteWorkerPay" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "noLocationRequired"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workAsBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 5: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "possibleSubstituteRejection" -> "wouldNotReject",
                "possibleSubstituteWorkerPay" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "noLocationRequired"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workAsBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 6: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "possibleSubstituteRejection" -> "wouldNotReject",
                "possibleSubstituteWorkerPay" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "noLocationRequired"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> true,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 7: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerChooses"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workAsIndependent"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 8: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerChooses"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workForEndClient"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 9: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerChooses"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> true,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 10: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerCannotChoose"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workAsIndependent"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 11: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerCannotChoose"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workForEndClient"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 12: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerCannotChoose"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> false,
                "expensesAreNotRelevantForRole" -> true,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> true,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 13: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerCannotChoose"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workAsBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 14: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerCannotChoose"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workForEndClient"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 15: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> true
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workAsBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 16: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> true
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workForEndClient"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 17: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> true
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> true,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 18: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> true
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workAsBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 19: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> true
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workForEndClient"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 20: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "notAgreedWithClient",
                "wouldWorkerPayHelper" -> true
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> true,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 21: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "noSubstitutionHappened",
                "possibleSubstituteRejection" -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "workerChooses"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workAsIndependent"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 22: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "noSubstitutionHappened",
                "possibleSubstituteRejection" -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "workerChooses"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> false,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> true,
                "workerRepresentsEngagerBusiness" -> "workForEndClient"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 23: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "noSubstitutionHappened",
                "possibleSubstituteRejection" -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
                "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
                "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
                "workerDecideWhere" -> "workerChooses"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> true,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 24: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                "workerSentActualSubstitute" -> "noSubstitutionHappened",
                "possibleSubstituteRejection" -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
                "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
                "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
                "workerDecideWhere" -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                "workerProvidedMaterials" -> false,
                "workerProvidedEquipment" -> false,
                "workerUsedVehicle" -> false,
                "workerHadOtherExpenses" -> true,
                "expensesAreNotRelevantForRole" -> false,
                "workerMainIncome" -> "incomeCalendarPeriods",
                "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                "workerReceivesBenefits" -> true,
                "workerAsLineManager" -> false,
                "contactWithEngagerCustomer" -> false,
                "workerRepresentsEngagerBusiness" -> "workerRepresentsEngagerBusiness"
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }
      }
    }
  }
}
