package uk.gov.hmrc.helpers

import org.joda.time.DateTime
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.model.analytics.InterviewFormat._
import uk.gov.hmrc.decisionservice.model.analytics.{Exit, Interview, InterviewSearch, Setup}
import uk.gov.hmrc.decisionservice.model.api.DecisionRequest

trait TestData {

  val defaultInterview = Json.toJson(Interview(
    "1.1.0-final","df7826*hW@#$","IR35","OUT",None,
    Setup("personDoingWork","No","partnership"),
    Exit("Yes"),
    None,None,None,None,DateTime.now()))

  val defaultInterviewSearch = Json.toJson(InterviewSearch("1.1.0-final",DateTime.now(),DateTime.now()))

  val decisionBadVersion = Json.toJson(DecisionRequest("chazDingle","12345",Map("personalService" -> Map("contractualRightForSubstitute" -> "Yes"))))

  val decisionVersion1 = Json.toJson(DecisionRequest("1.1.0-final","12345",Map("personalService" -> Map("contractualRightForSubstitute" -> "Yes"))))
  val decisionVersion11 = Json.toJson(DecisionRequest("1.1.1-final","12345",Map("personalService" -> Map("contractualRightForSubstitute" -> "Yes"))))
  val decisionVersion2 = Json.toJson(DecisionRequest("1.2.0-final","12345",Map("personalService" -> Map("contractualRightForSubstitute" -> "Yes"))))
  val decisionVersion3 = Json.toJson(DecisionRequest("1.3.0-final","12345",Map("personalService" -> Map("contractualRightForSubstitute" -> "Yes"))))
  val decisionVersion4 = Json.toJson(DecisionRequest("1.4.0-final","12345",Map("personalService" -> Map("contractualRightForSubstitute" -> "Yes"))))
  val decisionVersion5 = Json.toJson(DecisionRequest("1.5.0-final","12345",Map("personalService" -> Map("contractualRightForSubstitute" -> "Yes"))))

  // scalastyle:off

  val decisionCase1a = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
    "exit" -> Map("officeHolder" -> "No"),
    "personalService" -> Map(),"control"-> Map(),
    "financialRisk" -> Map("expensesAreNotRelevantForRole" -> "No"),"partAndParcel"-> Map())))

  val decisionRespone1 = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

  val decisionCase1b = Json.toJson(DecisionRequest("1.5.0-final","session-12345",
    Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
    "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened","possibleSubstituteRejection" -> "wouldReject","wouldWorkerPayHelper" -> "No"))))

  val decisionRespone1b = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

  val decisionCase1c = Json.toJson(DecisionRequest("1.5.0-final","session-12345",
    Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),"control" -> Map("engagerMovingWorker" -> "canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers","whenWorkHasToBeDone" -> "workerAgreeSchedule","workerDecideWhere" -> "workerCannotChoose"))))
  val decisionRespone1c = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""


  val decisionCase1d = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
    "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
    "financialRisk" -> Map("workerProvidedMaterials" -> "No","workerProvidedEquipment" -> "No","workerUsedVehicle" -> "No","workerHadOtherExpenses" -> "No","expensesAreNotRelevantForRole" -> "Yes","workerMainIncome" -> "incomeCalendarPeriods","paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"))))

  val decisionRespone1d = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

  val decisionCase1e = Json.toJson(DecisionRequest("1.5.0-final","session-12345",
    Map("setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "limitedCompany"),
      "exit" -> Map {"officeHolder" -> "No"},
      "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened","possibleSubstituteRejection" -> "wouldReject","wouldWorkerPayHelper" -> "No"),
      "control" -> Map("engagerMovingWorker" -> "canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers","whenWorkHasToBeDone" -> "workerAgreeSchedule","workerDecideWhere" -> "workerCannotChoose"),
      "financialRisk" -> Map("workerProvidedMaterials" -> "No","workerProvidedEquipment" -> "No","workerUsedVehicle" -> "No","workerHadOtherExpenses" -> "No","expensesAreNotRelevantForRole" -> "Yes","workerMainIncome" -> "incomeCalendarPeriods","paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"),
      "partAndParcel" -> Map("workerReceivesBenefits" -> "No","workerAsLineManager" -> "No","contactWithEngagerCustomer" -> "Yes","workerRepresentsEngagerBusiness" -> "workAsIndependent"))))

  val decisionRespone1e = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""



  val decisionCase2a = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
    "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
    "exit" -> Map("officeHolder" -> "No"),
    "personalService" -> Map(),
    "control"-> Map(),
    "financialRisk" -> Map("expensesAreNotRelevantForRole" -> "No"),
    "partAndParcel"-> Map())))

  val decisionRespone2 = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"CONTINUE","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

  val decisionCase2b = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
    "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
    "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened","possibleSubstituteRejection" -> "wouldReject","wouldWorkerPayHelper" -> "No"))))

  val decisionRespone2b = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"HIGH","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

  val decisionCase2c = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
    "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
    "control" -> Map("engagerMovingWorker" -> "canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers","whenWorkHasToBeDone" -> "workerAgreeSchedule","workerDecideWhere" -> "workerCannotChoose"))))

  val decisionRespone2c = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"NotValidUseCase","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"HIGH","setup":"CONTINUE"},"result":"Not Matched"}"""


  val decisionCase2d = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
    "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
    "financialRisk" -> Map("workerProvidedMaterials" -> "No","workerProvidedEquipment" -> "No","workerUsedVehicle" -> "No","workerHadOtherExpenses" -> "No","expensesAreNotRelevantForRole" -> "Yes","workerMainIncome" -> "incomeCalendarPeriods","paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"))))

  val decisionRespone2d = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"NotValidUseCase","financialRisk":"LOW","personalService":"NotValidUseCase","exit":"NotValidUseCase","control":"NotValidUseCase","setup":"CONTINUE"},"result":"Not Matched"}"""

  val decisionCase2e = Json.toJson(DecisionRequest("1.5.0-final","session-12345",Map(
    "setup" -> Map("endUserRole" -> "personDoingWork","hasContractStarted" -> "Yes","provideServices" -> "soleTrader"),
    "exit" -> Map {"officeHolder" -> "No"},
    "personalService" -> Map("workerSentActualSubstitute" -> "noSubstitutionHappened","possibleSubstituteRejection" -> "wouldReject","wouldWorkerPayHelper" -> "No"),
    "control" -> Map("engagerMovingWorker" -> "canMoveWorkerWithoutPermission","workerDecidingHowWorkIsDone" -> "workerAgreeWithOthers","whenWorkHasToBeDone" -> "workerAgreeSchedule","workerDecideWhere" -> "workerCannotChoose"),
    "financialRisk" -> Map("workerProvidedMaterials" -> "No","workerProvidedEquipment" -> "No","workerUsedVehicle" -> "No","workerHadOtherExpenses" -> "No","expensesAreNotRelevantForRole" -> "Yes","workerMainIncome" -> "incomeCalendarPeriods","paidForSubstandardWork" -> "asPartOfUsualRateInWorkingHours"),
    "partAndParcel" -> Map("workerReceivesBenefits" -> "No","workerAsLineManager" -> "No","contactWithEngagerCustomer" -> "Yes","workerRepresentsEngagerBusiness" -> "workAsIndependent"))))

  val decisionRespone2e = """{"version":"1.5.0-final","correlationID":"session-12345","score":{"partAndParcel":"LOW","financialRisk":"LOW","personalService":"HIGH","exit":"CONTINUE","control":"HIGH","setup":"CONTINUE"},"result":"Inside IR35"}"""

  //TODO - Error codes

  def errorResponse(errorCode: Int, errorMessage: String) = s"""{"code":$errorCode,"message":"$errorMessage"}"""
  // scalastyle:on

}
