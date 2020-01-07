/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.FinancialRisk
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.FinancialRiskRules

import scala.concurrent.Future

class FinancialRiskRuleEngine @Inject()() extends RuleEngine {

  def decide(financialRisk: Option[FinancialRisk])(implicit version: DecisionServiceVersion.Value): Future[Option[WeightedAnswerEnum.Value]] = {

    val rules = FinancialRiskRules(version)

    Future.successful(financialRisk flatMap {
      case FinancialRisk(None, None, None, None, None, None, None) => None
      case section => {
        val result = checkRules(section, rules.ruleSet)
        Some(WeightedAnswerEnum.withName(result))
      }
    })
  }
}
