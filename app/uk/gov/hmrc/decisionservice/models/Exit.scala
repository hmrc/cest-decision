package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class Exit(officeHolder: String) extends Section

object Exit {
  implicit val format: Format[Exit] = Json.format[Exit]
}
