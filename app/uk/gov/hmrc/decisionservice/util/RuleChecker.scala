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
import uk.gov.hmrc.decisionservice.models.rules.{RulesSet, RulesSetWithResult}

sealed abstract class RuleChecker {

  def ruleSet: Seq[RulesSetWithResult]

  def checkRules[T,A](section: T, notMatched: A = WeightedAnswerEnum.NOT_VALID_USE_CASE)(implicit writes: Writes[T]): String = {
    val jsObject: JsObject = Json.toJson(section).as[JsObject]
    checkOutcome(jsObject, ruleSet, notMatched)
  }

  private def checkOutcome[T](section: JsObject, rules: Seq[RulesSetWithResult], notMatched: T): String = {

    case class WeightedRuleAndResult(weighting: Int, rule: JsObject, result: String)

    val weightedRulesWithResults: Seq[WeightedRuleAndResult] = rules.flatMap { ruleSetAndResult =>
      ruleSetAndResult.rulesSet.map { rule =>
        WeightedRuleAndResult(
          weighting = rule.fields.size,
          rule = rule,
          result = ruleSetAndResult.result
        )
      }
    }

    val matchedRules = weightedRulesWithResults.filter { weightedRuleAndResult =>
      weightedRuleAndResult.rule.fields.forall(answer => section.fields.contains(answer))
    }

    matchedRules
      .sortBy(rule => -rule.weighting) // sort by highest weighting
      .headOption.map(_.result) // get the first result
      .getOrElse(notMatched.toString) // or return unknown if no result found
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
