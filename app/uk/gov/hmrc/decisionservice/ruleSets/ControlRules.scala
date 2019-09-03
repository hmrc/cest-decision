/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.decisionservice.ruleSets

import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.decisionservice.config.AppConfig
import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait ControlRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("control", version)
}

object ControlRules_V110 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.VERSION110_FINAL)
}

object ControlRules_V111 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.VERSION111_FINAL)
}

object ControlRules_V120 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.VERSION120_FINAL)
}

object ControlRules_V130 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.VERSION130_FINAL)
}

object ControlRules_V140 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.VERSION140_FINAL)
}

object ControlRules_V150 extends ControlRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.VERSION150_FINAL)
}

object ControlRules {
  def apply(version: DecisionServiceVersion.Value): ControlRules = version match {
    case DecisionServiceVersion.VERSION110_FINAL => ControlRules_V110
    case DecisionServiceVersion.VERSION111_FINAL => ControlRules_V111
    case DecisionServiceVersion.VERSION120_FINAL => ControlRules_V120
    case DecisionServiceVersion.VERSION130_FINAL => ControlRules_V130
    case DecisionServiceVersion.VERSION140_FINAL => ControlRules_V140
    case DecisionServiceVersion.VERSION150_FINAL => ControlRules_V150
  }
}