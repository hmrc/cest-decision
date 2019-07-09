package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.models.Section
import uk.gov.hmrc.decisionservice.models.enums.ExitEnum

trait ExitDecisionService {

  def decide(section: Section): ExitEnum.Value


}
