/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Control
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.ControlRules

import scala.concurrent.Future

class ControlRuleEngine @Inject()() extends RuleEngine {

  def decide(control: Option[Control])(implicit version: DecisionServiceVersion.Value): Future[Option[WeightedAnswerEnum.Value]] = {

    val rules = ControlRules(version)

    Future.successful(control flatMap {
      case Control(None, None, None, None) => None
      case section => {
        val result = checkRules(section, rules.ruleSet)
        Some(WeightedAnswerEnum.withName(result))
      }
    })
  }
}
