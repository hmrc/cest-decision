/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object WorkerSentActualSubstitute extends Enumeration with EnumFormats {

  val yesClientAgreed: WorkerSentActualSubstitute.Value = Value("yesClientAgreed")
  val notAgreedWithClient: WorkerSentActualSubstitute.Value = Value("notAgreedWithClient")
  val noSubstitutionHappened: WorkerSentActualSubstitute.Value = Value("noSubstitutionHappened")

  implicit val format: Format[WorkerSentActualSubstitute.Value] = enumFormat(WorkerSentActualSubstitute)
}