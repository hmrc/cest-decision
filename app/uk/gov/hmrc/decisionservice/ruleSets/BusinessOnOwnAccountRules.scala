/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait BusinessOnOwnAccountRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("business-on-own-account", version)
}

object BusinessOnOwnAccountRules_v24 extends BusinessOnOwnAccountRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object BusinessOnOwnAccountRules {
  def apply(version: DecisionServiceVersion.Value): BusinessOnOwnAccountRules = BusinessOnOwnAccountRules_v24
}
