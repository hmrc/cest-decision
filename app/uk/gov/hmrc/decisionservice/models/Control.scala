package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class Control(engagerMovingWorker: Option[String], workerDecidingHowWorkIsDone: Option[String], workHasToBeDone: Option[String],
                   workerDecideWhere: Option[String]) extends Section

object Control {
  implicit val format: Format[Control] = Json.format[Control]
}
