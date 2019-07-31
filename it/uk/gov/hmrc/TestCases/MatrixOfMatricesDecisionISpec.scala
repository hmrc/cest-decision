package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj
import uk.gov.hmrc.decisionservice.models.{Control, FinancialRisk, PartAndParcel, PersonalService}
import uk.gov.hmrc.decisionservice.models.enums.IdentifyToStakeholders

class MatrixOfMatricesDecisionISpec extends BaseISpec {

  "Matrix of Matrices" should {

    Seq(OldRuleEngine, NewRuleEngine).foreach { implicit engine =>

      s"POST ${engine.path}" should {

        "Scenario 1: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(engine.path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> "wouldNotReject",
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "noLocationRequired"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> true,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                PersonalService.possibleSubstituteRejection -> "wouldNotReject",
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "noLocationRequired"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                PersonalService.possibleSubstituteRejection -> "wouldNotReject",
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "noLocationRequired"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
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
                PersonalService.possibleSubstituteRejection -> "wouldNotReject",
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "noLocationRequired"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
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
                PersonalService.possibleSubstituteRejection -> "wouldNotReject",
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "noLocationRequired"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
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
                PersonalService.possibleSubstituteRejection -> "wouldNotReject",
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "noLocationRequired"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerChooses"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerChooses"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerChooses"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerCannotChoose"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerCannotChoose"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerCannotChoose"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerCannotChoose"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerCannotChoose"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
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
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                PersonalService.possibleSubstituteRejection -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "workerChooses"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
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
                PersonalService.possibleSubstituteRejection -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "workerChooses"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                PersonalService.possibleSubstituteRejection -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",
                Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",
                Control.whenWorkHasToBeDone -> "scheduleDecidedForWorker",
                Control.workerDecideWhere -> "workerChooses"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
                PersonalService.possibleSubstituteRejection -> "wouldReject",
                "wouldWorkerPayHelper" -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",
                Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",
                Control.whenWorkHasToBeDone -> "noScheduleRequiredOnlyDeadlines",
                Control.workerDecideWhere -> "workerAgreeWithOthers"
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",
                FinancialRisk.paidForSubstandardWork -> "outsideOfHoursNoCosts"
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
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
