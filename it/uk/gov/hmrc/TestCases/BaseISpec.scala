package uk.gov.hmrc.TestCases

import play.api.http.Status
import play.api.libs.json.JsValue
import play.api.libs.json.Json.obj
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase}

trait BaseISpec extends IntegrationSpecBase with CreateRequestHelper with Status {



  val path = "/decide"

  val defaultVersion = "2.4"

  val defaultExit = obj("officeHolder" -> false)

  val defaultPersonalService = obj(
    PersonalService.workerSentActualSubstitute -> WorkerSentActualSubstitute.yesClientAgreed,
    PersonalService.workerPayActualSubstitute -> false,
    PersonalService.wouldWorkerPayHelper -> false
  )
  val defaultControl = obj(
    Control.engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
    Control.workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
    Control.whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
    Control.workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
  )

  val defaultFinancialRisk = obj(
    FinancialRisk.workerProvidedMaterials -> false,
    FinancialRisk.workerProvidedEquipment -> false,
    FinancialRisk.workerUsedVehicle -> false,
    FinancialRisk.workerHadOtherExpenses -> false,
    FinancialRisk.expensesAreNotRelevantForRole -> true,
    FinancialRisk.workerMainIncome -> WorkerMainIncome.incomeCalendarPeriods,
    FinancialRisk.paidForSubstandardWork -> PaidForSubstandardWork.cannotBeCorrected
  )

  val defaultPartAndParcel = obj(
    PartAndParcel.workerReceivesBenefits -> false,
    PartAndParcel.workerAsLineManager -> false,
    PartAndParcel.contactWithEngagerCustomer -> true,
    PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
  )

  val defaultBusinessOnOwnAccount = obj(
    BusinessOnOwnAccount.exclusiveContract -> ExclusiveContract.unableToProvideServices,
    BusinessOnOwnAccount.transferRights -> TransferRights.rightsTransferredToClient,
    BusinessOnOwnAccount.multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
    BusinessOnOwnAccount.significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
    BusinessOnOwnAccount.seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
  )

  def interview(version: String = defaultVersion,
                exit: JsValue = defaultExit,
                personalService: JsValue = defaultPersonalService,
                control: JsValue = defaultControl,
                financialRisk: JsValue = defaultFinancialRisk,
                partAndParcel: JsValue = defaultPartAndParcel,
                businessOnOwnAccount: JsValue = defaultBusinessOnOwnAccount) = {

    obj(
       "version" -> version,
      "correlationID" -> "session-12345",
      "interview" -> obj(
        "setup" -> obj(
          "endUserRole" -> "personDoingWork",
          "hasContractStarted" -> true,
          "provideServices" -> "soleTrader"
        ),
        "exit" -> exit,
        "personalService" -> personalService,
        "control" -> control,
        "financialRisk" -> financialRisk,
        "partAndParcel" -> partAndParcel,
        "businessOnOwnAccount" -> businessOnOwnAccount
      )
    )
  }
}
