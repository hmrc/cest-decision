package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class PersonalService(workerSentActualSubstitute: Option[String], workerPayActualSubstitute: Option[String],
                           possibleSubstituteRejection: Option[String], possibleSubstituteWorkerPay: Option[String],
                           wouldWorkerPayHelper: Option[String]) extends Section

object PersonalService {
  implicit val format: Format[PersonalService] = Json.format[PersonalService]
}
