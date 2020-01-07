/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.services.mocks

import org.scalamock.scalatest.MockFactory
import uk.gov.hmrc.decisionservice.models.{DecisionRequest, DecisionResponse}
import uk.gov.hmrc.decisionservice.services.DecisionService

import scala.concurrent.{ExecutionContext, Future}

trait MockDecisionService extends MockFactory {

  val mockDecisionService = mock[DecisionService]

  def mockCalculateResult(request: DecisionRequest)(response: DecisionResponse): Unit = {
    (mockDecisionService.calculateResult(_: DecisionRequest)(_: ExecutionContext))
      .expects(request, *)
      .returns(Future.successful(response))
  }

}
