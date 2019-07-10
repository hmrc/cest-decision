package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.models.Section
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

import scala.concurrent.Future

trait PartAndParcelDecisionService {

  def decide(section: Section): Future[Option[WeightedAnswerEnum.Value]]

}
