package uk.gov.hmrc.decisionservice.services

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.enums.{ExitEnum, ResultEnum, WeightedAnswerEnum}

import scala.concurrent.Future

class ResultService @Inject()() {

  def decide(exit: Option[ExitEnum.Value],
             personalService: Option[WeightedAnswerEnum.Value],
             control: Option[WeightedAnswerEnum.Value],
             financialRisk: Option[WeightedAnswerEnum.Value],
             partAndParcel: Option[WeightedAnswerEnum.Value]
            ): Future[ResultEnum.Value] = {

    Future.successful(ResultEnum.UNKNOWN)
  }

}
