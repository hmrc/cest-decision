package uk.gov.hmrc.TestCases

import play.api.http.Status
import play.api.libs.json.Json.obj
import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.decisionservice.models.{Control, FinancialRisk, PartAndParcel, PersonalService}
import uk.gov.hmrc.decisionservice.models.enums.{ChooseWhereWork, HowWorkIsDone, IdentifyToStakeholders, MoveWorker, PaidForSubstandardWork, ScheduleOfWorkingHours, WorkerMainIncome, WorkerSentActualSubstitute}
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase, TestData}

trait BaseISpec extends IntegrationSpecBase with CreateRequestHelper with Status with TestData {

  sealed trait DecisionEngine {
    val path: String
  }
  case object NewRuleEngine extends DecisionEngine {
    override val path = "/decide/new"
  }
  case object OldRuleEngine extends DecisionEngine {
    override val path = "/decide"
  }

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

  def interview(exit: JsValue = defaultExit,
                personalService: JsValue = defaultPersonalService,
                control: JsValue = defaultControl,
                financialRisk: JsValue = defaultFinancialRisk,
                partAndParcel: JsValue = defaultPartAndParcel)(implicit engine: DecisionEngine) = {

    val interview = obj(
      "version" -> "1.5.0-final",
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
        "partAndParcel" -> partAndParcel
      )
    )

    engine match {
      case NewRuleEngine => interview
      case OldRuleEngine => Json.parse(interview.toString
        .replace("true", "\"Yes\"")
        .replace("false", "\"No\"")
      )
    }
  }
}
