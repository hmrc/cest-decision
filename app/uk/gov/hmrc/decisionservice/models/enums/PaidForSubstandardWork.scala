/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object PaidForSubstandardWork extends Enumeration with EnumFormats {

  val outsideOfHoursNoCharge: PaidForSubstandardWork.Value = Value("outsideOfHoursNoCharge")
  val outsideOfHoursNoCosts: PaidForSubstandardWork.Value = Value("outsideOfHoursNoCosts")
  val asPartOfUsualRateInWorkingHours: PaidForSubstandardWork.Value = Value("asPartOfUsualRateInWorkingHours")
  val cannotBeCorrected: PaidForSubstandardWork.Value = Value("cannotBeCorrected")
  val noObligationToCorrect: PaidForSubstandardWork.Value = Value("noObligationToCorrect")

  implicit val format: Format[PaidForSubstandardWork.Value] = enumFormat(PaidForSubstandardWork)
}