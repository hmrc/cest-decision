package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.models.Section
import uk.gov.hmrc.decisionservice.models.enums.ExitEnum

import scala.concurrent.Future

trait ExitDecisionService {

  def decide(section: Section): Future[Option[ExitEnum.Value]]

}
