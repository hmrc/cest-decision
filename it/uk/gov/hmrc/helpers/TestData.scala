package uk.gov.hmrc.helpers

import org.joda.time.DateTime
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.model.analytics.InterviewFormat._
import uk.gov.hmrc.decisionservice.model.analytics.{Exit, Interview, InterviewSearch, Setup}
import uk.gov.hmrc.decisionservice.model.api.DecisionRequest

trait TestData {

  val defaultInterview = Json.toJson(Interview(
    "x","x","x","x",None,
    Setup("x","x","x"),
    Exit("x"),
    None,None,None,None,DateTime.now()))

  val defaultInterviewSearch = Json.toJson(InterviewSearch("x",DateTime.now(),DateTime.now()))

  val decisionBadVersion = Json.toJson(DecisionRequest("x","x",Map("x" -> Map("x" -> "x"))))

  val decisionVersion1 = Json.toJson(DecisionRequest("1.1.0-final","x",Map("x" -> Map("x" -> "x"))))
  val decisionVersion11 = Json.toJson(DecisionRequest("1.1.1-final","x",Map("x" -> Map("x" -> "x"))))
  val decisionVersion2 = Json.toJson(DecisionRequest("1.2.0-final","x",Map("x" -> Map("x" -> "x"))))
  val decisionVersion3 = Json.toJson(DecisionRequest("1.3.0-final","x",Map("x" -> Map("x" -> "x"))))
  val decisionVersion4 = Json.toJson(DecisionRequest("1.4.0-final","x",Map("x" -> Map("x" -> "x"))))
  val decisionVersion5 = Json.toJson(DecisionRequest("1.5.0-final","x",Map("x" -> Map("x" -> "x"))))

}
