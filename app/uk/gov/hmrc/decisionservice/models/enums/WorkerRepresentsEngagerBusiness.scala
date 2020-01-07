/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object WorkerRepresentsEngagerBusiness extends Enumeration with EnumFormats {

  val workForEndClient: WorkerRepresentsEngagerBusiness.Value = Value("workForEndClient")
  val workAsIndependent: WorkerRepresentsEngagerBusiness.Value = Value("workAsIndependent")
  val workAsBusiness: WorkerRepresentsEngagerBusiness.Value = Value("workAsBusiness")

  implicit val format: Format[WorkerRepresentsEngagerBusiness.Value] = enumFormat(WorkerRepresentsEngagerBusiness)
}