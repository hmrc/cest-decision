/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ChooseWhereWork extends Enumeration with EnumFormats {

  val workerCannotChoose: ChooseWhereWork.Value = Value("workerCannotChoose")
  val workerChooses: ChooseWhereWork.Value = Value("workerChooses")
  val noLocationRequired: ChooseWhereWork.Value = Value("noLocationRequired")
  val workerAgreeWithOthers: ChooseWhereWork.Value = Value("workerAgreeWithOthers")

  implicit val format: Format[ChooseWhereWork.Value] = enumFormat(ChooseWhereWork)
}