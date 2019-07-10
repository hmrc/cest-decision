package uk.gov.hmrc.decisionservice.services

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Section
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

import scala.concurrent.Future

class BusinessOnOwnAccountDecisionService @Inject()() {

  def decide(section: Section): Future[Option[WeightedAnswerEnum.Value]] = {

    Future.successful(None)
  }
}