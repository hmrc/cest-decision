/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait PartAndParcelRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("part-and-parcel", version)
}

object PartAndParcelRules_v150 extends PartAndParcelRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v1_5_0)
}

object PartAndParcelRules_v24 extends PartAndParcelRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object PartAndParcelRules {
  def apply(version: DecisionServiceVersion.Value): PartAndParcelRules = version match {
    case DecisionServiceVersion.`v1_5_0` => PartAndParcelRules_v150
    case DecisionServiceVersion.`v2_4` => PartAndParcelRules_v24
  }
}