package uk.gov.hmrc.decisionservice.services

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Section
import uk.gov.hmrc.decisionservice.models.enums.ExitEnum

import scala.concurrent.Future

class ExitDecisionService @Inject()() {

  def decide(section: Section): Future[Option[ExitEnum.Value]] = {

    Future.successful(None)
  }

}