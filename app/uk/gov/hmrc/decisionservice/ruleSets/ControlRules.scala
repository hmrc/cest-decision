/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait ControlRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("control", version)
}

object ControlRules_v150 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v1_5_0)
}

object ControlRules_v24 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object ControlRules {
  def apply(version: DecisionServiceVersion.Value): ControlRules = version match {
    case DecisionServiceVersion.`v1_5_0` => ControlRules_v150
    case DecisionServiceVersion.`v2_4` => ControlRules_v24
  }
}