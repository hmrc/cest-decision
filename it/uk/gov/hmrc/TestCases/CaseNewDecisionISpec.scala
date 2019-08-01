package uk.gov.hmrc.TestCases

import org.scalatest.concurrent.IntegrationPatience
import play.api.http.Status
import play.api.libs.json.Json
import play.api.libs.ws.DefaultBodyWritables
import uk.gov.hmrc.helpers._

class CaseNewDecisionISpec extends IntegrationSpecBase with DefaultBodyWritables
  with Status with IntegrationPatience with CreateRequestHelper with WiremockHelper {


  s"For Case 1 a POST /decide/new}" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase1a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone1 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-12345",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase1a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase1e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone1e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-12345",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "LOW",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "HIGH",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase1e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 2 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase2a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone2 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-12345",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase2a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase2e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone2e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-12345",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "LOW",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase2e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 3 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase3a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone3 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-829e8d15-12345-4988-b825-94c59db392a7",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase3a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase3e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone3e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-829e8d15-12345-4988-b825-94c59db392a7",
        "score" -> Json.obj(
          "exit" -> "CONTINUE",
          "control" -> "OUTOFIR35",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase3e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"OUTOFIR35"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 4 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase4a =  Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone4 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-72dc03fc-c41e-49be-bee3-f207fb21e8ec",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase4a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase4e =  Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone4e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-72dc03fc-c41e-49be-bee3-f207fb21e8ec",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "MEDIUM",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "HIGH",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase4e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 5 a POST /decide/new" should {

    val decisionCase5a = Json.obj(
      "version" -> "1.5.0-final",
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

        ),
        "control" -> Json.obj(

        ),
        "financialRisk" -> Json.obj(
          "expensesAreNotRelevantForRole" -> false
        ),
        "partAndParcel" -> Json.obj(
        )
      )
    )

    val decisionRespone5 = Json.obj(
      "version" -> "1.5.0-final",
      "correlationID" -> "session-4ab87a6b-2dea-49db-a519-650a12ae06b1",
      "score" -> Json.obj(
        "partAndParcel" -> "NotValidUseCase",
        "financialRisk" -> "NotValidUseCase",
        "personalService" -> "NotValidUseCase",
        "exit" -> "CONTINUE",
        "control" -> "NotValidUseCase",
        "setup" -> "CONTINUE"
      ),
      "result" -> "Not Matched")

    "return a 200 and continue response given a early exit request" in {

      lazy val res = postRequest("/decide/new",decisionCase5a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase5e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone5e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-4ab87a6b-2dea-49db-a519-650a12ae06b1",
        "score" -> Json.obj(
          "exit" -> "CONTINUE",
          "control" -> "OUTOFIR35",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase5e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"OUTOFIR35"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 6 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase6a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone6 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-425437df-24ce-4e97-8537-d521697277d5",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase6a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase6e = Json.obj(
        "version" -> "1.5.0-final",
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
            "contactWithEngagerCustomer" -> false)
        )
      )

      val decisionRespone6e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-425437df-24ce-4e97-8537-d521697277d5",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase6e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 7 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase7a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone7 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-3015fd77-c21b-4b49-b166-ff6170b834c8",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase7a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase7e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone7e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-3015fd77-c21b-4b49-b166-ff6170b834c8",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "LOW",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "HIGH",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase7e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 8 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase8a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone8 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-8cbd115f-d486-46d8-ba89-1d7531c96479",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase8a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase8e = Json.obj(
        "version" -> "1.5.0-final",
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
            "contactWithEngagerCustomer" -> false)
        )
      )

      val decisionRespone8e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-8cbd115f-d486-46d8-ba89-1d7531c96479",
        "score" -> Json.obj(
          "partAndParcel" -> "HIGH",
          "financialRisk" -> "LOW",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase8e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"HIGH"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 9 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase9a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone9 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-a9aed912-33b1-49ad-b972-72b5d8c65be0",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase9a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase9e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone9e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-a9aed912-33b1-49ad-b972-72b5d8c65be0",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "LOW",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase9e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 10 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase10a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone10 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-30128ec4-15e1-4ff0-a487-3dc866f8d25d",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase10a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase10e = Json.obj(
        "version" -> "1.5.0-final",
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
            "contactWithEngagerCustomer" -> false)
        )
      )

      val decisionRespone10e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-30128ec4-15e1-4ff0-a487-3dc866f8d25d",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase10e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  //TODO still requires the last case

  /*
  s"For Case 11 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase11a = """"""
      val decisionRespone11 = Json.obj(
"version" -> "1.5.0-final",
"correlationID" -> "session-72dc03fc-c41e-49be-bee3-f207fb21e8ec",
"score" -> Json.obj(
"partAndParcel" -> "NotValidUseCase",
"financialRisk" -> "NotValidUseCase",
"personalService" -> "NotValidUseCase",
"exit" -> "CONTINUE",
"control" -> "NotValidUseCase",
"setup" -> "CONTINUE"
),
"result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase11a)

      whenReady(res) { result =>
 result.status shouldBe OK
 result.body should include(""""exit":"CONTINUE"""")
 result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase11e = """"""
      val decisionRespone11e = Json.obj(
"version" -> "1.5.0-final",
"correlationID" -> "session-72dc03fc-c41e-49be-bee3-f207fb21e8ec",
"score" -> Json.obj(
"partAndParcel" -> "LOW",
"financialRisk" -> "MEDIUM",
"personalService" -> "HIGH",
"exit" -> "CONTINUE",
"control" -> "HIGH",
"setup" -> "CONTINUE"
),
"result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase11e)

      whenReady(res) { result =>
 result.status shouldBe OK
 result.body should include(""""partAndParcel":"MEDIUM"""")
 result.body should include(""""financialRisk":"LOW"""")
 result.body should include(""""personalService":"MEDIUM"""")
 result.body should include(""""exit":"CONTINUE"""")
 result.body should include(""""control":"MEDIUM"""")
 result.body should include(""""result":"Inside IR35"""")
      }
    }

  }
  */



  s"For Case 12 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase12a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone12 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-8975db1b-80d7-4c76-85d6-03a25c558925",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase12a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase12e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone12e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-8975db1b-80d7-4c76-85d6-03a25c558925",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase12e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 13 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase13a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone13 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-f49e56a6-b690-4c06-a0d8-f82f836a58d7",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase13a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase13e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workForEndClient")
        )
      )

      val decisionRespone13e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-f49e56a6-b690-4c06-a0d8-f82f836a58d7",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase13e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 14 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase14a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone14 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-7ff64104-e39b-4e75-8960-3bccbcb7df23",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase14a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase14e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone14e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-7ff64104-e39b-4e75-8960-3bccbcb7df23",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase14e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 15 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase15a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone15 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-b2d2388f-b241-4957-8141-2d0c5c0836a2",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase15a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase15e = Json.obj(
        "version" -> "1.5.0-final",
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
            "contactWithEngagerCustomer" -> false)
        )
      )

      val decisionRespone15e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-b2d2388f-b241-4957-8141-2d0c5c0836a2",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase15e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }


  s"For Case 16 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase16a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone16 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-f757b601-7eec-46db-8024-652e6d425957",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase16a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase16e = Json.obj(
        "version" -> "1.5.0-final",
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
            "contactWithEngagerCustomer" -> false)
        )
      )

      val decisionRespone16e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-f757b601-7eec-46db-8024-652e6d425957",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "LOW",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "HIGH",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase16e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 17 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase17a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone17 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-4d138f6f-3656-4044-ba7d-6a8540e094dc",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase17a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }

    "return a 200 and continue response given a All sections request" in {

      val decisionCase17e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone17e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-4d138f6f-3656-4044-ba7d-6a8540e094dc",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "MEDIUM",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "HIGH",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase17e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }

  s"For Case 18 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase18a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone18 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase18a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase18e = Json.obj(
        "version" -> "1.5.0-final",
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
            "contactWithEngagerCustomer" -> false)
        )
      )

      val decisionRespone18e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-e7d07a17-3cfe-4ff6-bcca-cda7d0eb651e",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase18e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 19 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase19a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone19 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-6dc80962-346a-4c25-b5a6-7d08cabee4c9",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase19a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase19e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone19e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-6dc80962-346a-4c25-b5a6-7d08cabee4c9",
        "score" -> Json.obj(
          "financialRisk" -> "OUTOFIR35",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Outside IR35")

      lazy val res = postRequest("/decide/new",decisionCase19e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"OUTOFIR35"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Outside IR35"""")
      }
    }

  }

  s"For Case 20 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase20a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone20 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-685d7f4f-92c3-49c2-9520-09836fa0390b",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase20a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase20e = Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workAsIndependent")
        )
      )

      val decisionRespone20e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-685d7f4f-92c3-49c2-9520-09836fa0390b",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "MEDIUM",
          "personalService" -> "MEDIUM",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Unknown")

      lazy val res = postRequest("/decide/new",decisionCase20e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"MEDIUM"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Unknown"""")
      }
    }

  }

  s"For Case 21 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase21a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone21 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-bb2b9dee-2599-4494-9c4d-324709e81adc",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase21a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase21e = Json.obj(
        "version" -> "1.5.0-final",
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
            "contactWithEngagerCustomer" -> false)
        )
      )

      val decisionRespone21e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-bb2b9dee-2599-4494-9c4d-324709e81adc",
        "score" -> Json.obj(
          "partAndParcel" -> "LOW",
          "financialRisk" -> "LOW",
          "personalService" -> "HIGH",
          "exit" -> "CONTINUE",
          "control" -> "HIGH",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase21e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"LOW"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"HIGH"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"HIGH"""")
        result.body should include(""""result":"Inside IR35"""")
      }
    }

  }


  s"For Case 22 a POST /decide/new" should {

    "return a 200 and continue response given a early exit request" in {

      val decisionCase22a = Json.obj(
        "version" -> "1.5.0-final",
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
          "personalService" -> Json.obj(),
          "control" -> Json.obj(),
          "financialRisk" -> Json.obj(
            "expensesAreNotRelevantForRole" -> false
          ),
          "partAndParcel" -> Json.obj()
        )
      )

      val decisionRespone22 = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-72f6b69a-018e-4ad7-8589-a04658801d32",
        "score" -> Json.obj(
          "partAndParcel" -> "NotValidUseCase",
          "financialRisk" -> "NotValidUseCase",
          "personalService" -> "NotValidUseCase",
          "exit" -> "CONTINUE",
          "control" -> "NotValidUseCase",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Not Matched")

      lazy val res = postRequest("/decide/new",decisionCase22a)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""result":"Not Matched"""")
      }
    }


    "return a 200 and continue response given a All sections request" in {

      val decisionCase22e =  Json.obj(
        "version" -> "1.5.0-final",
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
            "workerRepresentsEngagerBusiness" -> "workForEndClient")
        )
      )

      val decisionRespone22e = Json.obj(
        "version" -> "1.5.0-final",
        "correlationID" -> "session-72f6b69a-018e-4ad7-8589-a04658801d32",
        "score" -> Json.obj(
          "partAndParcel" -> "MEDIUM",
          "financialRisk" -> "LOW",
          "personalService" -> "MEDIUM",
          "exit" -> "CONTINUE",
          "control" -> "MEDIUM",
          "setup" -> "CONTINUE"
        ),
        "result" -> "Inside IR35")

      lazy val res = postRequest("/decide/new",decisionCase22e)

      whenReady(res) { result =>
        result.status shouldBe OK
        result.body should include(""""partAndParcel":"MEDIUM"""")
        result.body should include(""""financialRisk":"LOW"""")
        result.body should include(""""personalService":"MEDIUM"""")
        result.body should include(""""exit":"CONTINUE"""")
        result.body should include(""""control":"MEDIUM"""")
        result.body should include(""""result":"Inside IR35"""")

      }
    }

  }


}
