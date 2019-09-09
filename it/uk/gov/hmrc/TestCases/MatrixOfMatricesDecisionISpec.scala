package uk.gov.hmrc.TestCases

import play.api.libs.json.Json.obj
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.models.enums._

class MatrixOfMatricesDecisionISpec extends BaseISpec {

  "Matrix of Matrices" should {



      s"POST $path" should {

        "Scenario 1: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> true,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 2: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> true,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 3: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 4: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 5: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }


        "Scenario 6: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }


        "Scenario 7: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 8: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 9: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 10: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 11: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 12: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 13: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 14: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 15: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 16: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 17: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 18: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 19: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 20: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 21: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 22: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 23: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 24: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> false,
                FinancialRisk.expensesAreNotRelevantForRole -> true,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"LOW"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 25: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 26: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 27: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 28: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 29: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 30: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 31: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 32: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 33: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }


        "Scenario 34: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 35: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 36: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 37: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }


        "Scenario 38: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }


        "Scenario 39: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }


        "Scenario 40: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.possibleSubstituteRejection -> PossibleSubstituteRejection.wouldNotReject,
                PersonalService.possibleSubstituteWorkerPay -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"MEDIUM"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 41: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 42: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"LOW"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 43: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 44: return a 200 and a determination of Unknown" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> false,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> true,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"MEDIUM"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Unknown"""")
          }
        }

        "Scenario 45: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 46: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"MEDIUM"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 47: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.noRightsArise,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.moneyButNotTime,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"LOW"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

        "Scenario 48: return a 200 and a determination of Inside IR35" in {

          lazy val res = postRequest(path,
            interview(
              personalService = obj(
                PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.notAgreedWithClient,
                PersonalService.wouldWorkerPayHelper -> false
              ),
              control = obj(
                Control.engagerMovingWorker -> MoveWorker.canMoveWorkerWithoutPermission,
                Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
                Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
                Control.workerDecideWhere -> ChooseWhereWork.noLocationRequired
              ),
              financialRisk = obj(
                FinancialRisk.workerProvidedMaterials -> false,
                FinancialRisk.workerProvidedEquipment -> false,
                FinancialRisk.workerUsedVehicle -> false,
                FinancialRisk.workerHadOtherExpenses -> true,
                FinancialRisk.expensesAreNotRelevantForRole -> false,
                FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
                FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.outsideOfHoursNoCosts
              ),
              partAndParcel = obj(
                PartAndParcel.workerReceivesBenefits -> true,
                PartAndParcel.workerAsLineManager -> false,
                PartAndParcel.contactWithEngagerCustomer -> false,
                PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
              ),
              businessOnOwnAccount = obj(
                BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
                BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
                BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
                BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
                BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.standAloneContract
              )
            )
          )

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(""""personalService":"HIGH"""")
            result.body should include(""""control":"HIGH"""")
            result.body should include(""""financialRisk":"MEDIUM"""")
            result.body should include(""""partAndParcel":"HIGH"""")
            result.body should include(""""businessOnOwnAccount":"MEDIUM"""")
            result.body should include(""""result":"Inside IR35"""")
          }
        }

      }
  }
}
