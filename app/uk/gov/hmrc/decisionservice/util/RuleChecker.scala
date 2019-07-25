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

import play.api.libs.json.{JsObject, _}
import uk.gov.hmrc.decisionservice.config.ruleSets._
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

abstract class RuleChecker {

  def ruleSet: Seq[RuleSet]

  def checkRules[T,A](section: T, notMatched: A = WeightedAnswerEnum.NOT_VALID_USE_CASE)(implicit writes: Writes[T]): String = {
    val jsObject: JsObject = Json.toJson(section).as[JsObject]
    checkOutcome(jsObject, ruleSet, notMatched)
  }

  private def checkOutcome[T](section: JsObject ,rules: Seq[RuleSet], notMatched: T): String = {
    rules.find(ruleSet => {
      ruleSet.rules.fields.forall(section.fields.contains)
    }) match {
      case Some(matchedRule) => matchedRule.result
      case _ => notMatched.toString
    }
  }
}

class ControlRulesSet extends RuleChecker {
  override def ruleSet: Seq[RuleSet] = ControlRules.ruleSet
}

class ExitRulesSet extends RuleChecker {
  override def ruleSet: Seq[RuleSet] = EarlyExitRules.ruleSet
}

class PersonalServiceRulesSet extends RuleChecker {
  override def ruleSet: Seq[RuleSet] = PersonalServiceRules.ruleSet
}

class PartAndParcelRulesSet extends RuleChecker {
  override def ruleSet: Seq[RuleSet] = PartAndParcelRules.ruleSet
}

class FinancialRiskRulesSet extends RuleChecker {
  override def ruleSet: Seq[RuleSet] = FinancialRiskRules.ruleSet
}

class MatrixOfMatricesRulesSet extends RuleChecker {
  override def ruleSet: Seq[RuleSet] = MatrixOfMatricesRules.ruleSet
}
