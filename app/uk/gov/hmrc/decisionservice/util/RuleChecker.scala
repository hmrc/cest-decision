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

package uk.gov.hmrc.decisionservice.util

import play.api.libs.json._
import uk.gov.hmrc.decisionservice.config.ruleSets._
import uk.gov.hmrc.decisionservice.models.rules.{RulesSet, RulesSetWithResult}

import scala.annotation.tailrec

abstract class RuleChecker {

  def ruleSet: Seq[RulesSetWithResult]

  def checkRules[T](section: T)(implicit writes: Writes[T]): String = {
    val jsObject: JsObject = Json.toJson(section).as[JsObject]
    checkOutcome(jsObject,ruleSet)
  }

  @tailrec
  private def checkOutcome(section: JsObject, rules: Seq[RulesSetWithResult]): String = {
    if(rules.isEmpty) "undetermined" else {
      val currentRule = rules.head
      if(currentRule.rulesSet.toStream exists(rule => {
        rule.fields.forall(section.fields.contains)
      })) currentRule.result else checkOutcome(section, rules.tail)
    }
  }

}

class ControlRulesSet extends RuleChecker {
  override def ruleSet: Seq[RulesSetWithResult] = ControlRules.ruleSet.as[RulesSet].rulesInOrder
}

class ExitRulesSet extends RuleChecker {
  override def ruleSet: Seq[RulesSetWithResult] = EarlyExitRules.ruleSet.as[RulesSet].rulesInOrder
}

class PersonalServiceRulesSet extends RuleChecker {
  override def ruleSet: Seq[RulesSetWithResult] = PersonalServiceRules.ruleSet.as[RulesSet].rulesInOrder
}

class PartAndParcelRulesSet extends RuleChecker {
  override def ruleSet: Seq[RulesSetWithResult] = PartAndParcelRules.ruleSet.as[RulesSet].rulesInOrder
}

class FinancialRiskRulesSet extends RuleChecker {
  override def ruleSet: Seq[RulesSetWithResult] = FinancialRiskRules.ruleSet.as[RulesSet].rulesInOrder
}

class MatrixOfMatricesRulesSet extends RuleChecker {
  override def ruleSet: Seq[RulesSetWithResult] = MatrixOfMatricesRules.ruleSet.as[RulesSet].rulesInOrder
}
