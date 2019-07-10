package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class PartAndParcel(workerReceivesBenefits: Option[String], workerAsLineManager: Option[String],
                         contactWithEngagerCustomer: Option[String],  workerRepresentsEngagerBusiness: Option[String]) extends Section

object PartAndParcel {
  implicit val format: Format[PartAndParcel] = Json.format[PartAndParcel]
}
