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

}
