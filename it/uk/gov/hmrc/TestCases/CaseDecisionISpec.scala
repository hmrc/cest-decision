package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.json.Json
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.decisionservice.model.api.DecisionRequest
import uk.gov.hmrc.decisionservice.models.enums.IdentifyToStakeholders
import uk.gov.hmrc.decisionservice.models.{Control, FinancialRisk, PartAndParcel, PersonalService}
import uk.gov.hmrc.helpers._

class CaseDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper with CaseTestData {


  s"For Case 1 a POST /decide}" should {

    "return a 200 and continue response given a early exit request" in {

      val jsonBody = Json.toJson(
        DecisionRequest("1.5.0-final","session-12345",Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
        "exit" -> Map("officeHolder" -> "No"),
        "personalService" -> Map(),"control"-> Map(),
        "financialRisk" -> Map(FinancialRisk.expensesAreNotRelevantForRole -> "No"),"partAndParcel"-> Map()))
      )

      val expectedResponse = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide", jsonBody)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(expectedResponse)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val jsonBody = Json.toJson(DecisionRequest("1.5.0-final","session-12345",
        Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
          "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened",PersonalService.possibleSubstituteRejection -> "wouldReject","wouldWorkerPayHelper" -> "No"))))

      lazy val res = postRequest("/decide", jsonBody)

      val expectedResult = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(expectedResult)
      }
    }

    "return a 200 and continue response given a control request" in {

      val jsonBody = Json.toJson(DecisionRequest("1.5.0-final","session-12345",
        Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),"control" -> Map(Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",Control.whenWorkHasToBeDone -> "workerAgreeSchedule",Control.workerDecideWhere -> "workerCannotChoose"))))

      lazy val res = postRequest("/decide",jsonBody)

      val expectedResult = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(expectedResult)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {


      val jsonBody = Json.toJson(
        DecisionRequest("1.5.0-final","session-12345",Map(
          "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
          "financialRisk" -> Map(FinancialRisk.workerProvidedMaterials -> "No",FinancialRisk.workerProvidedEquipment -> "No",FinancialRisk.workerUsedVehicle -> "No",FinancialRisk.workerHadOtherExpenses -> "No",FinancialRisk.expensesAreNotRelevantForRole -> "Yes",FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",FinancialRisk.paidForSubstandardWork -> "asPartOfUsualRateInWorkingHours"))))

      lazy val res = postRequest("/decide",jsonBody)

      val expectedResult = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(expectedResult)
      }
    }

    "return a 200 and continue response given a All sections request" in {


      val jsonBody = Json.toJson(DecisionRequest("1.5.0-final","session-12345",
        Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
          "exit" -> Map ("officeHolder" -> "No"),
          "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened",PersonalService.possibleSubstituteRejection -> "wouldReject","wouldWorkerPayHelper" -> "No"),
          "control" -> Map(Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",Control.workerDecidingHowWorkIsDone -> "workerAgreeWithOthers",Control.whenWorkHasToBeDone -> "workerAgreeSchedule",Control.workerDecideWhere -> "workerCannotChoose"),
          "financialRisk" -> Map(FinancialRisk.workerProvidedMaterials -> "No",FinancialRisk.workerProvidedEquipment -> "No",FinancialRisk.workerUsedVehicle -> "No",FinancialRisk.workerHadOtherExpenses -> "No",FinancialRisk.expensesAreNotRelevantForRole -> "Yes",FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",FinancialRisk.paidForSubstandardWork -> "asPartOfUsualRateInWorkingHours"),
          "partAndParcel" -> Map(PartAndParcel.workerReceivesBenefits -> "No",PartAndParcel.workerAsLineManager -> "No",PartAndParcel.contactWithEngagerCustomer -> "Yes",PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent))))

      lazy val res = postRequest("/decide",jsonBody)

      val expectedResult = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(expectedResult)
      }
    }

  }

  s"For Case 2 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase2a = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "exit" -> Map("officeHolder" -> "No"),
        "personalService" -> Map(),
        "control"-> Map(),
        "financialRisk" -> Map(FinancialRisk.expensesAreNotRelevantForRole -> "No"),
        "partAndParcel"-> Map())))

      val decisionRespone2 = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide",decisionCase2a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase2b = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened",PersonalService.possibleSubstituteRejection -> "wouldReject","wouldWorkerPayHelper" -> "No"))))

      val decisionRespone2b = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide",decisionCase2b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase2c = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "control" -> Map(Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",Control.whenWorkHasToBeDone -> "workerAgreeSchedule",Control.workerDecideWhere -> "workerCannotChoose"))))

      val decisionRespone2c = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide",decisionCase2c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase2d = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "financialRisk" -> Map(FinancialRisk.workerProvidedMaterials -> "No",FinancialRisk.workerProvidedEquipment -> "No",FinancialRisk.workerUsedVehicle -> "No",FinancialRisk.workerHadOtherExpenses -> "No",FinancialRisk.expensesAreNotRelevantForRole -> "Yes",FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",FinancialRisk.paidForSubstandardWork -> "asPartOfUsualRateInWorkingHours"))))

      val decisionRespone2d = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide",decisionCase2d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase2e = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "exit" -> Map ("officeHolder" -> "No"),
        "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened",PersonalService.possibleSubstituteRejection -> "wouldReject","wouldWorkerPayHelper" -> "No"),
        "control" -> Map(Control.engagerMovingWorker -> "canMoveWorkerWithoutPermission",Control.workerDecidingHowWorkIsDone -> "workerDecidesWithoutInput",Control.whenWorkHasToBeDone -> "workerAgreeSchedule",Control.workerDecideWhere -> "workerCannotChoose"),
        "financialRisk" -> Map(FinancialRisk.workerProvidedMaterials -> "No",FinancialRisk.workerProvidedEquipment -> "No",FinancialRisk.workerUsedVehicle -> "No",FinancialRisk.workerHadOtherExpenses -> "No",FinancialRisk.expensesAreNotRelevantForRole -> "Yes",FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",FinancialRisk.paidForSubstandardWork -> "asPartOfUsualRateInWorkingHours"),
        "partAndParcel" -> Map(PartAndParcel.workerReceivesBenefits -> "No",PartAndParcel.workerAsLineManager -> "No",PartAndParcel.contactWithEngagerCustomer -> "Yes",PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent))))

      val decisionRespone2e = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postRequest("/decide",decisionCase2e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone2e)
      }
    }

  }

  s"For Case 3 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase3a = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
        "exit" -> Map("officeHolder" -> "No"),
        "personalService" -> Map(),
        "control"-> Map(),
        "financialRisk" -> Map(FinancialRisk.expensesAreNotRelevantForRole -> "No"),
        "partAndParcel"-> Map())))

      val decisionRespone3 = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide",decisionCase3a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase3b = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened",PersonalService.possibleSubstituteRejection -> "wouldNotReject",PersonalService.possibleSubstituteWorkerPay -> "No","wouldWorkerPayHelper" -> "No"))))

      val decisionRespone3b = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"MEDIUM","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide",decisionCase3b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase3c = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "control" -> Map(Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",Control.whenWorkHasToBeDone -> "workerDecideSchedule",Control.workerDecideWhere -> "noLocationRequired"))))

      val decisionRespone3c = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"exit":"NotValidUseCase","control":"OUTOFIR35","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postRequest("/decide",decisionCase3c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase3d = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
        "financialRisk" -> Map(FinancialRisk.workerProvidedMaterials -> "No",FinancialRisk.workerProvidedEquipment -> "No",FinancialRisk.workerUsedVehicle -> "No",FinancialRisk.workerHadOtherExpenses -> "No",FinancialRisk.expensesAreNotRelevantForRole -> "Yes",FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",FinancialRisk.paidForSubstandardWork -> "asPartOfUsualRateInWorkingHours"))))

      val decisionRespone3d = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postRequest("/decide",decisionCase3d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase3e = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
        "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
        "exit" -> Map ("officeHolder" -> "No"),
        "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened",PersonalService.possibleSubstituteRejection -> "wouldNotReject",PersonalService.possibleSubstituteWorkerPay -> "No","wouldWorkerPayHelper" -> "Yes"),
        "control" -> Map(Control.engagerMovingWorker -> "cannotMoveWorkerWithoutNewAgreement",Control.workerDecidingHowWorkIsDone -> "workerFollowStrictEmployeeProcedures",Control.whenWorkHasToBeDone -> "workerDecideSchedule",Control.workerDecideWhere -> "noLocationRequired"),
        "financialRisk" -> Map(FinancialRisk.workerProvidedMaterials -> "No",FinancialRisk.workerProvidedEquipment -> "No",FinancialRisk.workerUsedVehicle -> "No",FinancialRisk.workerHadOtherExpenses -> "No",FinancialRisk.expensesAreNotRelevantForRole -> "Yes",FinancialRisk.workerMainIncome -> "incomeCalendarPeriods",FinancialRisk.paidForSubstandardWork -> "asPartOfUsualRateInWorkingHours"),
        "partAndParcel" -> Map(PartAndParcel.workerReceivesBenefits -> "No",PartAndParcel.workerAsLineManager -> "No",PartAndParcel.contactWithEngagerCustomer -> "Yes",PartAndParcel.workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent))))


      val decisionRespone3e = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"exit":"CONTINUE","control":"OUTOFIR35","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postRequest("/decide",decisionCase3e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone3e)
      }
    }

  }


  s"For Case 4 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase4a =  """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone4 = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase4a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase4b =  """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone4b = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase4b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase4c = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"workerAgreeWithOthers"}}}"""
      val decisionRespone4c = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase4c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase4d = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"}}}"""
      val decisionRespone4d = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"MEDIUM","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase4d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase4e =  """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"workerAgreeWithOthers"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone4e = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"LOW","financialRisk":"MEDIUM","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase4e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone4e)
      }
    }

  }


  s"For Case 5 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase5a = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone5 = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase5a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase5b = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone5b = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase5b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase5c = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"workerDecideSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone5c = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","score":{"exit":"NotValidUseCase","control":"OUTOFIR35","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase5c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase5d = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"}}}"""
      val decisionRespone5d = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","score":{"partAndParcel":"NotValidUseCase","financialRisk":"MEDIUM","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase5d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase5e = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"workerDecideSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone5e = """{"version":"1.5.0-final","correlationID":"session-4ab87a6b-2dea-49db-a519-650a12ae06b1","score":{"exit":"CONTINUE","control":"OUTOFIR35","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase5e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone5e)
      }
    }

  }


  s"For Case 6 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase6a = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone6 = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase6a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase6b = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone6b = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase6b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase6c = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"noScheduleRequiredOnlyDeadlines","workerDecideWhere":"workerAgreeWithOthers"}}}"""
      val decisionRespone6c = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase6c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase6d = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"Yes","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"}}}"""
      val decisionRespone6d = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase6d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase6e = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"noScheduleRequiredOnlyDeadlines","workerDecideWhere":"workerAgreeWithOthers"},"financialRisk":{"workerProvidedMaterials":"Yes","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"No"}}}"""
      val decisionRespone6e = """{"version":"1.5.0-final","correlationID":"session-425437df-24ce-4e97-8537-d521697277d5","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase6e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone6e)
      }
    }

  }


  s"For Case 7 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase7a = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone7 = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase7a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase7b = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone7b = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase7b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase7c = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone7c = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase7c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase7d = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"}}}"""
      val decisionRespone7d = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase7d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase7e = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone7e = """{"version":"1.5.0-final","correlationID":"session-3015fd77-c21b-4b49-b166-ff6170b834c8","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase7e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone7e)
      }
    }

  }


  s"For Case 8 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase8a = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone8 = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase8a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase8b = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone8b = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase8b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase8c = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone8c = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase8c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase8d = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"}}}"""
      val decisionRespone8d = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase8d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase8e = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"noObligationToCorrect"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"Yes","contactWithEngagerCustomer":"No"}}}"""
      val decisionRespone8e = """{"version":"1.5.0-final","correlationID":"session-8cbd115f-d486-46d8-ba89-1d7531c96479","score":{"partAndParcel":"HIGH","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase8e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone8e)
      }
    }

  }


  s"For Case 9 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase9a = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone9 = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase9a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase9b = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone9b = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase9b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase9c = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"workerAgreeWithOthers"}}}"""
      val decisionRespone9c = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase9c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase9d = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCosts"}}}"""
      val decisionRespone9d = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase9d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase9e = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"workerAgreeWithOthers"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCosts"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone9e = """{"version":"1.5.0-final","correlationID":"session-a9aed912-33b1-49ad-b972-72b5d8c65be0","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase9e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone9e)
      }
    }

  }


  s"For Case 10 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase10a = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone10 = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase10a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase10b = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"personalService":{"workerSentActualSubstitute":"yesClientAgreed","workerPayActualSubstitute":"No","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone10b = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"MEDIUM","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase10b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase10c = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"noScheduleRequiredOnlyDeadlines","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone10c = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase10c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase10d = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeFixed","paidForSubstandardWork":"outsideOfHoursNoCharge"}}}"""
      val decisionRespone10d = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase10d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase10e = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"yesClientAgreed","workerPayActualSubstitute":"No","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"noScheduleRequiredOnlyDeadlines","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeFixed","paidForSubstandardWork":"outsideOfHoursNoCharge"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"No"}}}"""
      val decisionRespone10e = """{"version":"1.5.0-final","correlationID":"session-30128ec4-15e1-4ff0-a487-3dc866f8d25d","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase10e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone10e)
      }
    }

  }

  //TODO still requires the last case

//  s"For Case 11 a POST /decide" should {
//
//    "return a 200 and continue response given a early exit request" in {
//
//      val decisionCase11a = """"""
//      val decisionRespone11 = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""
//
//      lazy val res = postFullJsonRequest("/decide",decisionCase11a)
//
//      whenReady(res) { result =>
//        result.status shouldBe OK
//        result.body should equal(decisionRespone11)
//      }
//    }
//
//
//    "return a 200 and continue response given a Personal service request" in {
//
//      val decisionCase11b = """"""
//      val decisionRespone11b = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""
//
//      lazy val res = postFullJsonRequest("/decide",decisionCase11b)
//
//      whenReady(res) { result =>
//        result.status shouldBe OK
//        result.body should equal(decisionRespone11b)
//      }
//    }
//
//    "return a 200 and continue response given a control request" in {
//
//      val decisionCase11c = """"""
//      val decisionRespone11c = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""
//
//      lazy val res = postFullJsonRequest("/decide",decisionCase11c)
//
//      whenReady(res) { result =>
//        result.status shouldBe OK
//        result.body should equal(decisionRespone11c)
//      }
//    }
//
//    "return a 200 and continue response given a Financial Risk request" in {
//
//      val decisionCase11d = """"""
//      val decisionRespone11d = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"NotValidUseCase","financialRisk":"MEDIUM","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""
//
//      lazy val res = postFullJsonRequest("/decide",decisionCase11d)
//
//      whenReady(res) { result =>
//        result.status shouldBe OK
//        result.body should equal(decisionRespone11d)
//      }
//    }
//
//    "return a 200 and continue response given a All sections request" in {
//
//      val decisionCase11e = """"""
//      val decisionRespone11e = """{"version":"1.5.0-final","correlationID":"session-72dc03fc-c41e-49be-bee3-f207fb21e8ec","score":{"partAndParcel":"LOW","financialRisk":"MEDIUM","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""
//
//      lazy val res = postFullJsonRequest("/decide",decisionCase11e)
//
//      whenReady(res) { result =>
//        result.status shouldBe OK
//        result.body should equal(decisionRespone11e)
//      }
//    }
//
//  }


  s"For Case 12 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase12a = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone12 = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase12a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase12b = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone12b = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"MEDIUM","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase12b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase12c = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"scheduleDecidedForWorker","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone12c = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase12c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase12d = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"}}}"""
      val decisionRespone12d = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase12d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase12e = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"scheduleDecidedForWorker","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone12e = """{"version":"1.5.0-final","correlationID":"session-8975db1b-80d7-4c76-85d6-03a25c558925","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase12e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone12e)
      }
    }

  }

  s"For Case 13 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase13a = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone13 = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase13a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase13b = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone13b = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"MEDIUM","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase13b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase13c = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone13c = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase13c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase13d = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCosts"}}}"""
      val decisionRespone13d = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase13d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase13e = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCosts"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workForEndClient"}}}"""
      val decisionRespone13e = """{"version":"1.5.0-final","correlationID":"session-f49e56a6-b690-4c06-a0d8-f82f836a58d7","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase13e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone13e)
      }
    }

  }

  s"For Case 14 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase14a = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone14 = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase14a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase14b = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone14b = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase14b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase14c = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerDecideSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone14c = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase14c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase14d = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"}}}"""
      val decisionRespone14d = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase14d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase14e = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerDecideSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone14e = """{"version":"1.5.0-final","correlationID":"session-7ff64104-e39b-4e75-8960-3bccbcb7df23","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase14e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone14e)
      }
    }

  }


  s"For Case 15 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase15a = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone15 = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase15a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase15b = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone15b = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase15b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase15c = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone15c = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase15c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase15d = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"}}}"""
      val decisionRespone15d = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase15d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase15e = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"Yes","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"No"}}}"""
      val decisionRespone15e = """{"version":"1.5.0-final","correlationID":"session-b2d2388f-b241-4957-8141-2d0c5c0836a2","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase15e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone15e)
      }
    }

  }


  s"For Case 16 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase16a = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone16 = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase16a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase16b = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone16b = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase16b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase16c = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerAgreeWithOthers","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone16c = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase16c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase16d = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"}}}"""
      val decisionRespone16d = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase16d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase16e = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerAgreeWithOthers","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"No"}}}"""
      val decisionRespone16e = """{"version":"1.5.0-final","correlationID":"session-f757b601-7eec-46db-8024-652e6d425957","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase16e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone16e)
      }
    }

  }

  s"For Case 17 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase17a = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone17 = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase17a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase17b = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone17b = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase17b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase17c = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"noScheduleRequiredOnlyDeadlines","workerDecideWhere":"workerCannotChoose"}}}"""
      val decisionRespone17c = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase17c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase17d = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"}}}"""
      val decisionRespone17d = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"MEDIUM","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase17d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase17e = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"noScheduleRequiredOnlyDeadlines","workerDecideWhere":"workerCannotChoose"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone17e = """{"version":"1.5.0-final","correlationID":"session-4d138f6f-3656-4044-ba7d-6a8540e094dc","score":{"partAndParcel":"LOW","financialRisk":"MEDIUM","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase17e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone17e)
      }
    }

  }

  s"For Case 18 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase18a = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone18 = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase18a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase18b = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone18b = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase18b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase18c =  """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"workerDecideSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone18c = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase18c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase18d =  """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"}}}"""
      val decisionRespone18d = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase18d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase18e = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerDecidesWithoutInput","whenWorkHasToBeDone":"workerDecideSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"No"}}}"""
      val decisionRespone18e = """{"version":"1.5.0-final","correlationID":"session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase18e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone18e)
      }
    }

  }

  s"For Case 19 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase19a = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone19 = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase19a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase19b = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone19b = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase19b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase19c = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone19c = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase19c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase19d = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeFixed","paidForSubstandardWork":"cannotBeCorrected"}}}"""
      val decisionRespone19d = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","score":{"financialRisk":"OUTOFIR35","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase19d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase19e = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"Yes","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"No","workerMainIncome":"incomeFixed","paidForSubstandardWork":"cannotBeCorrected"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone19e = """{"version":"1.5.0-final","correlationID":"session-6dc80962-346a-4c25-b5a6-7d08cabee4c9","score":{"financialRisk":"OUTOFIR35","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Outside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase19e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone19e)
      }
    }

  }

  s"For Case 20 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase20a = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone20 = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase20a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase20b = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone20b = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"MEDIUM","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase20b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase20c = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone20c = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase20c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase20d = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"}}}"""
      val decisionRespone20d = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","score":{"partAndParcel":"NotValidUseCase","financialRisk":"MEDIUM","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase20d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase20e = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"outsideOfHoursNoCharge"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workAsIndependent"}}}"""
      val decisionRespone20e = """{"version":"1.5.0-final","correlationID":"session-685d7f4f-92c3-49c2-9520-09836fa0390b","score":{"partAndParcel":"LOW","financialRisk":"MEDIUM","personalService":"MEDIUM","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Unknown"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase20e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone20e)
      }
    }

  }

  s"For Case 21 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase21a = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone21 = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase21a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase21b = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone21b = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase21b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase21c = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"}}}"""
      val decisionRespone21c = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase21c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase21d = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"}}}"""
      val decisionRespone21d = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase21d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase21e = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"limitedCompany"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldReject","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone":"workerFollowStrictEmployeeProcedures","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"noLocationRequired"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"asPartOfUsualRateInWorkingHours"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"No"}}}"""
      val decisionRespone21e = """{"version":"1.5.0-final","correlationID":"session-bb2b9dee-2599-4494-9c4d-324709e81adc","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase21e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone21e)
      }
    }

  }


  s"For Case 22 a POST /decide" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase22a = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{},"control":{},"financialRisk":{"expensesAreNotRelevantForRole":"No"},"partAndParcel":{}}}"""
      val decisionRespone22 = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase22a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22)
      }
    }


    "return a 200 and continue response given a Personal service request" in {

      val decisionCase22b = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"}}}"""
      val decisionRespone22b = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"MEDIUM","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase22b)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22b)
      }
    }

    "return a 200 and continue response given a control request" in {

      val decisionCase22c = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"noWorkerInputAllowed","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"workerCannotChoose"}}}"""
      val decisionRespone22c = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"MEDIUM","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase22c)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22c)
      }
    }

    "return a 200 and continue response given a Financial Risk request" in {

      val decisionCase22d = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"cannotBeCorrected"}}}"""
      val decisionRespone22d = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase22d)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22d)
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase22e =  """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","interview":{"setup":{"endUserRole":"personDoingWork","hasContractStarted":"Yes","provideServices":"soleTrader"},"exit":{"officeHolder":"No"},"personalService":{"workerSentActualSubstitute":"noSubstitutionHappened","possibleSubstituteRejection":"wouldNotReject","possibleSubstituteWorkerPay":"No","wouldWorkerPayHelper":"No"},"control":{"engagerMovingWorker":"cannotMoveWorkerWithoutNewAgreement","workerDecidingHowWorkIsDone":"noWorkerInputAllowed","whenWorkHasToBeDone":"workerAgreeSchedule","workerDecideWhere":"workerCannotChoose"},"financialRisk":{"workerProvidedMaterials":"No","workerProvidedEquipment":"No","workerUsedVehicle":"No","workerHadOtherExpenses":"No","expensesAreNotRelevantForRole":"Yes","workerMainIncome":"incomeCalendarPeriods","paidForSubstandardWork":"cannotBeCorrected"},"partAndParcel":{"workerReceivesBenefits":"No","workerAsLineManager":"No","contactWithEngagerCustomer":"Yes","workerRepresentsEngagerBusiness":"workForEndClient"}}}"""
      val decisionRespone22e = """{"version":"1.5.0-final","correlationID":"session-72f6b69a-018e-4ad7-8589-a04658801d32","score":{"partAndParcel":"MEDIUM","financialRisk":"LOW","personalService":"MEDIUM","exit":"CONTINUE","control":"MEDIUM","setup":"CONTINUE"},"result":"Inside IR35"}"""

      lazy val res = postFullJsonRequest("/decide",decisionCase22e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should equal(decisionRespone22e)
      }
    }

  }

  
}
