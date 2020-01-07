/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait EarlyExitRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("exit", version)
}

object EarlyExitRules_v150 extends EarlyExitRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v1_5_0)
}

object EarlyExitRules_v24 extends EarlyExitRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object EarlyExitRules {
  def apply(version: DecisionServiceVersion.Value): EarlyExitRules = version match {
    case DecisionServiceVersion.`v1_5_0` => EarlyExitRules_v150
    case DecisionServiceVersion.`v2_4` => EarlyExitRules_v24
  }
}