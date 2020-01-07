/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait PersonalServiceRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("personal-service", version)
}

object PersonalServiceRules_v150 extends PersonalServiceRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v1_5_0)
}

object PersonalServiceRules_v24 extends PersonalServiceRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object PersonalServiceRules {
  def apply(version: DecisionServiceVersion.Value): PersonalServiceRules = version match {
    case DecisionServiceVersion.`v1_5_0` => PersonalServiceRules_v150
    case DecisionServiceVersion.`v2_4` => PersonalServiceRules_v24
  }
}