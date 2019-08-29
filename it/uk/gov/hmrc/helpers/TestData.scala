package uk.gov.hmrc.helpers

import org.joda.time.DateTime
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.model.analytics.InterviewFormat._
import uk.gov.hmrc.decisionservice.model.analytics.{Exit, Interview, InterviewSearch, Setup}

trait TestData {

  val defaultInterview = Json.toJson(Interview(
    "1.1.0-final","df7826*hW@#$","IR35","OUT",None,
    Setup("personDoingWork","No","partnership"),
    Exit("Yes"),
    None,None,None,None,DateTime.now()))

  val defaultInterviewSearch = Json.toJson(InterviewSearch("1.1.0-final",DateTime.now(),DateTime.now()))

}
