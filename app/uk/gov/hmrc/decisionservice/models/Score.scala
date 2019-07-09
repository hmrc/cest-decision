package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Json, OFormat}
import uk.gov.hmrc.decisionservice.models.enums.{ExitEnum, SetupEnum, WeightedAnswerEnum}

case class Score(setup: Option[SetupEnum.Value] = None, exit: Option[ExitEnum.Value] = None, personalService: Option[WeightedAnswerEnum.Value] = None,
                 control: Option[WeightedAnswerEnum.Value] = None, financialRisk: Option[WeightedAnswerEnum.Value] = None,
                 partAndParcel: Option[WeightedAnswerEnum.Value] = None)

object Score {
  implicit val formats: OFormat[Score] = Json.format[Score]
}