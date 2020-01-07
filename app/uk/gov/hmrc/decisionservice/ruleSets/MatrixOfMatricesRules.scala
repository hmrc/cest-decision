/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait MatrixOfMatricesRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("matrix-of-matrices", version)
}

object MatrixOfMatricesRules_v150 extends MatrixOfMatricesRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v1_5_0)
}

object MatrixOfMatricesRules_v24 extends MatrixOfMatricesRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object MatrixOfMatricesRules {
  def apply(version: DecisionServiceVersion.Value): MatrixOfMatricesRules = version match {
    case DecisionServiceVersion.`v1_5_0` => MatrixOfMatricesRules_v150
    case DecisionServiceVersion.`v2_4` => MatrixOfMatricesRules_v24
  }
}