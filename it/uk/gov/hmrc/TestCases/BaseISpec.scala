package uk.gov.hmrc.TestCases

import play.api.http.Status
import play.api.libs.json.{JsBoolean, JsString, JsValue}
import play.api.libs.json.Json.JsValueWrapper
import uk.gov.hmrc.helpers.{CreateRequestHelper, IntegrationSpecBase}

trait BaseISpec extends IntegrationSpecBase with CreateRequestHelper with Status {

  implicit def bool(value: Boolean)(implicit engine: DecisionEngine): JsValueWrapper = engine match {
    case NewRuleEngine => JsBoolean(value)
    case OldRuleEngine => if(value) JsString("Yes") else JsString("No")
  }

  sealed trait DecisionEngine {
    val path: String
  }
  case object NewRuleEngine extends DecisionEngine {
    override val path = "/decide/new"
  }
  case object OldRuleEngine extends DecisionEngine {
    override val path = "/decide"
  }

}
