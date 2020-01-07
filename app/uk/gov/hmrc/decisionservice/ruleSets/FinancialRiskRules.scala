/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait FinancialRiskRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("financial-risk", version)
}

object FinancialRiskRules_v150 extends FinancialRiskRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v1_5_0)
}

object FinancialRiskRules_v24 extends FinancialRiskRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object FinancialRiskRules {
  def apply(version: DecisionServiceVersion.Value): FinancialRiskRules = version match {
    case DecisionServiceVersion.`v1_5_0` => FinancialRiskRules_v150
    case DecisionServiceVersion.`v2_4` => FinancialRiskRules_v24
  }
}
