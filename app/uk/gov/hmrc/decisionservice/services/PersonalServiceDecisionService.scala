package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.models.Section
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

trait PersonalServiceDecisionService {

  def decide(section: Section): WeightedAnswerEnum.Value

}
