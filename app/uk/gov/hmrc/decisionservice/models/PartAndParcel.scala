/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums.WorkerRepresentsEngagerBusiness

case class PartAndParcel(workerReceivesBenefits: Option[Boolean],
                         workerAsLineManager: Option[Boolean],
                         contactWithEngagerCustomer: Option[Boolean],
                         workerRepresentsEngagerBusiness: Option[WorkerRepresentsEngagerBusiness.Value])

object PartAndParcel {
  implicit val format: Format[PartAndParcel] = Json.format[PartAndParcel]

  val workerReceivesBenefits = "workerReceivesBenefits"
  val workerAsLineManager = "workerAsLineManager"
  val contactWithEngagerCustomer = "contactWithEngagerCustomer"
  val workerRepresentsEngagerBusiness = "workerRepresentsEngagerBusiness"
}
