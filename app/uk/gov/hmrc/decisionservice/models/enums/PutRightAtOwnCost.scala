/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object PutRightAtOwnCost extends Enumeration with EnumFormats {

  val outsideOfHoursNoCharge: PutRightAtOwnCost.Value = Value("outsideOfHoursNoCharge")
  val outsideOfHoursNoCosts: PutRightAtOwnCost.Value = Value("outsideOfHoursNoCosts")
  val asPartOfUsualRateInWorkingHours: PutRightAtOwnCost.Value = Value("asPartOfUsualRateInWorkingHours")
  val cannotBeCorrected: PutRightAtOwnCost.Value = Value("cannotBeCorrected")
  val noObligationToCorrect: PutRightAtOwnCost.Value = Value("noObligationToCorrect")

  implicit val format: Format[PutRightAtOwnCost.Value] = enumFormat(PutRightAtOwnCost)
}