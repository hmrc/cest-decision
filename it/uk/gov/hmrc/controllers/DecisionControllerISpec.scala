package uk.gov.hmrc.controllers

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.json.Json.obj
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.TestCases.BaseISpec
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, WiremockHelper}


class DecisionControllerISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with BaseISpec {

  s"POST $path" should {

    "return a 400 given a 1.1.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.1.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.1.1-final version" in {

      lazy val res = postRequest(path, interview(version = "1.1.1-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.2.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.2.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.3.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.3.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 400 given a 1.4.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.4.0-final"))

      whenReady(res) { result =>
        result.status shouldBe BAD_REQUEST
      }
    }

    "return a 200 given a 1.5.0-final version" in {

      lazy val res = postRequest(path, interview(version = "1.5.0-final"))

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }

    "return a 200 given the default version" in {

      lazy val res = postRequest(path, interview(version = defaultVersion))

      whenReady(res) { result =>
        result.status shouldBe OK
      }
    }
  }

  "Decision Controller" should {

    s"POST $path" should {

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
