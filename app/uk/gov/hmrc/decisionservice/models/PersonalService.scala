/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums.{PossibleSubstituteRejection, WorkerSentActualSubstitute}

case class PersonalService(workerSentActualSubstitute: Option[WorkerSentActualSubstitute.Value] = None,
                           workerPayActualSubstitute: Option[Boolean] = None,
                           possibleSubstituteRejection: Option[PossibleSubstituteRejection.Value] = None,
                           possibleSubstituteWorkerPay: Option[Boolean] = None,
                           wouldWorkerPayHelper: Option[Boolean] = None)

object PersonalService {
  implicit val format: Format[PersonalService] = Json.format[PersonalService]

  val workerSentActualSubstitute = "workerSentActualSubstitute"
  val workerPayActualSubstitute = "workerPayActualSubstitute"
  val possibleSubstituteRejection = "possibleSubstituteRejection"
  val possibleSubstituteWorkerPay = "possibleSubstituteWorkerPay"
  val wouldWorkerPayHelper = "wouldWorkerPayHelper"
}
