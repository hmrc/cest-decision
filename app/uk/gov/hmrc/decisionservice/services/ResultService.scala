package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.models.enums.{ExitEnum, ResultEnum, WeightedAnswerEnum}

trait ResultService {

  def decide(exit: ExitEnum.Value,
             personalService: WeightedAnswerEnum.Value,
             control: WeightedAnswerEnum.Value,
             financialRisk: WeightedAnswerEnum.Value,
             partAndParcel: WeightedAnswerEnum.Value
            ): ResultEnum.Value

}
