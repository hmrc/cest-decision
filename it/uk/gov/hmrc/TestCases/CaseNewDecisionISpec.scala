package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.json.Json
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers._

class CaseNewDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper {


  s"For Case 1 a POST /decide}" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase1 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-149274dd-0261-4da9-8b68-6131932bb6dd",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
            "workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "workerCannotChoose"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"unableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"providedServicesToOtherEngagers",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"contractIsPartOfASeries"
          )
        )
      )


    lazy val res = postRequest("/decide",decisionCase1)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 2 a POST /decide" should {


    "return a 200 and continue response given a All sections request" in {

      val decisionCase2 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-829e8d15-3699-4988-b825-94c59db392a7",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "soleTrader"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
            "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "workerCannotChoose"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase2)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 3 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase3 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-829e8d15-12345-4988-b825-94c59db392a7",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldNotReject",
            "possibleSubstituteWorkerPay" -> false,
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerDecideSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase3)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"OUTOFIR35"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 4 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase4 =  Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-72dc03fc-c41e-49be-bee3-f207fb21e8ec",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "workerAgreeWithOthers"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> true,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "noObligationToCorrect"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase4)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 5 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase5 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-4ab87a6b-2dea-49db-a519-650a12ae06b1",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
            "whenWorkHasToBeDone" -> "workerDecideSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> true,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "noObligationToCorrect"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase5)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"OUTOFIR35"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 6 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase6 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-425437df-24ce-4e97-8537-d521697277d5",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
            "workerDecideWhere" -> "workerAgreeWithOthers"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> true,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> true,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "noObligationToCorrect"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> false
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase6)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 7 a POST /decide" should {


    "return a 200 and continue response given a All sections request" in {

      val decisionCase7 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-3015fd77-c21b-4b49-b166-ff6170b834c8",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "noObligationToCorrect"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase7)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 8 a POST /decide" should {


    "return a 200 and continue response given a All sections request" in {

      val decisionCase8 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-8cbd115f-d486-46d8-ba89-1d7531c96479",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "noObligationToCorrect"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> true,
            "contactWithEngagerCustomer" -> false
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase8)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"HIGH"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 9 a POST /decide" should {


    "return a 200 and continue response given a All sections request" in {

      val decisionCase9 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-a9aed912-33b1-49ad-b972-72b5d8c65be0",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "workerAgreeWithOthers"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase9)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 10 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase10 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-30128ec4-15e1-4ff0-a487-3dc866f8d25d",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "soleTrader"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "yesClientAgreed",
            "workerPayActualSubstitute" -> false,
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
            "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> true,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeFixed",
            "paidForSubstandardWork" -> "outsideOfHoursNoCharge"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> false
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase10)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 11 a POST /decide" should {


    "return a 200 and continue response given a All sections request" in {

      val decisionCase11 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-2cf487a1-1186-4ab4-adbc-39c4203d3a57",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"contractIsPartOfASeries"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase11)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 12 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase12 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-8975db1b-80d7-4c76-85d6-03a25c558925",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldNotReject",
            "possibleSubstituteWorkerPay" -> false,
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "scheduleDecidedForWorker",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> true,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase12)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 13 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase13 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-f49e56a6-b690-4c06-a0d8-f82f836a58d7",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "soleTrader"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldNotReject",
            "possibleSubstituteWorkerPay" -> false,
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> true,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "outsideOfHoursNoCosts"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workForEndClient"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase13)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 14 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase14 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-7ff64104-e39b-4e75-8960-3bccbcb7df23",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerDecideSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> true,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "outsideOfHoursNoCharge"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase14)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 15 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase15 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-b2d2388f-b241-4957-8141-2d0c5c0836a2",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> true,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "outsideOfHoursNoCharge"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> false
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase15)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 16 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase16 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-f757b601-7eec-46db-8024-652e6d425957",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
            "workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> false
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase16)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 17 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase17 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-4d138f6f-3656-4044-ba7d-6a8540e094dc",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
            "workerDecideWhere" -> "workerCannotChoose"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "outsideOfHoursNoCharge"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase17)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 18 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase18 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "soleTrader"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "workerDecidesWithoutInput",
            "whenWorkHasToBeDone" -> "workerDecideSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> true,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> false
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase18)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 19 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase19 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-6dc80962-346a-4c25-b5a6-7d08cabee4c9",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "soleTrader"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> true,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> false,
            "workerMainIncome" -> "incomeFixed",
            "paidForSubstandardWork" -> "cannotBeCorrected"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )


      lazy val res = postRequest("/decide",decisionCase19)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 20 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase20 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-685d7f4f-92c3-49c2-9520-09836fa0390b",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldNotReject",
            "possibleSubstituteWorkerPay" -> false,
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "outsideOfHoursNoCharge"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workAsIndependent"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase20)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Unknown"""")
      }
    }

  }

  s"For Case 21 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase21 = Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-bb2b9dee-2599-4494-9c4d-324709e81adc",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "limitedCompany"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithoutPermission",
            "workerDecidingHowWorkIsDone" -> "workerFollowStrictEmployeeProcedures",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> false
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase21)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 22 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase22 =  Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-72f6b69a-018e-4ad7-8589-a04658801d32",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "personDoingWork",
            "hasContractStarted" -> true,
            "provideServices" -> "soleTrader"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldNotReject",
            "possibleSubstituteWorkerPay" -> false,
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "cannotMoveWorkerWithoutNewAgreement",
            "workerDecidingHowWorkIsDone" -> "noWorkerInputAllowed",
            "whenWorkHasToBeDone" -> "workerAgreeSchedule",
            "workerDecideWhere" -> "workerCannotChoose"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "cannotBeCorrected"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workForEndClient"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase22)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")

      }
    }

  }

  s"For Case 23 a POST /decide" should {

    "return a 200 and continue response given a All sections request" in {

      val decisionCase23 =  Json.obj(
        "version" -> "2.4",
        "correlationID" -> "session-5ddf914b-89f4-4669-a4b1-ba20af7bcf6f",
        "interview" -> Json.obj(
          "setup" -> Json.obj(
            "endUserRole" -> "endClient",
            "hasContractStarted" -> true,
            "provideServices" -> "soleTrader"
          ),
          "exit" -> Json.obj(
            "officeHolder" -> false
          ),
          "personalService" -> Json.obj(
            "workerSentActualSubstitute" -> "noSubstitutionHappened",
            "possibleSubstituteRejection" -> "wouldReject",
            "wouldWorkerPayHelper" -> false
          ),
          "control" -> Json.obj(
            "engagerMovingWorker" -> "canMoveWorkerWithPermission",
            "workerDecidingHowWorkIsDone" -> "noWorkerInputAllowed",
            "whenWorkHasToBeDone" -> "noScheduleRequiredOnlyDeadlines",
            "workerDecideWhere" -> "noLocationRequired"
          ),
          "financialRisk" -> Json.obj(
            "workerProvidedMaterials" -> false,
            "workerProvidedEquipment" -> false,
            "workerUsedVehicle" -> false,
            "workerHadOtherExpenses" -> false,
            "expensesAreNotRelevantForRole" -> true,
            "workerMainIncome" -> "incomeCalendarPeriods",
            "paidForSubstandardWork" -> "cannotBeCorrected"
          ),
          "partAndParcel" -> Json.obj(
            "workerReceivesBenefits" -> false,
            "workerAsLineManager" -> false,
            "contactWithEngagerCustomer" -> true,
            "workerRepresentsEngagerBusiness" -> "workForEndClient"
          ),
          "businessOnOwnAccount"-> Json.obj(
            "exclusiveContract"->"ableToProvideServices",
            "transferRights"->"noRightsArise",
            "multipleEngagements"->"onlyContractForPeriod",
            "significantWorkingTime"->"consumesSignificantAmount",
            "seriesOfContracts"->"standAloneContract"
          )
        )
      )

      lazy val res = postRequest("/decide",decisionCase23)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""businessOnOwnAccount":"LOW"""")
        result.body should include(""""result":"Inside IR35"""")

      }
    }

  }

}