/*
 * Copyright 2018 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.ruleengine

import cats.data.{Validated}
import play.api.Logger
import uk.gov.hmrc.decisionservice.Validation
import uk.gov.hmrc.decisionservice.model.DecisionServiceError
import uk.gov.hmrc.decisionservice.model.rules._

import scala.annotation.tailrec

sealed trait RuleEngineDecision {
  def value: String
  def facts: Map[String,CarryOver]
  def isFinal: Boolean
}

case class RuleEngineDecisionNotMatched(facts: Map[String,CarryOver]) extends RuleEngineDecision {
  override def value = "Not Matched"
  override def isFinal = false
}

case class RuleEngineDecisionImpl(value: String, facts: Map[String,CarryOver]) extends RuleEngineDecision {
  override def isFinal = true
}

object FinalFact {
  def unapply(facts: Facts) = facts.facts.values.find(_.exit)
}

trait RuleEngine {
  def processRules(rules: Rules, facts: Facts): Validation[RuleEngineDecision] = {
    @tailrec
    def go(rules: List[SectionRuleSet], facts: Facts): Validation[Facts] = {
      facts match {
        case FinalFact(_) => Validated.valid(facts)
        case _ =>
          rules match {
            case Nil => Validated.valid(facts)
            case ruleSet :: ruleSets =>
              ruleSet ==+>: facts match {
                case Validated.Valid(newFacts) => go(ruleSets, newFacts)
                case e@Validated.Invalid(_) => e
              }
          }
      }
    }
    Logger.info(s"processing rules for ${rules.rules.size} clusters against ${facts.facts.size} facts")
    val maybeFacts = go(rules.rules, facts)
    maybeFacts.map {
      case f@FinalFact(ff) =>
        Logger.info(s"final decision: ${ff.value}")
        RuleEngineDecisionImpl(ff.value, f.facts)
      case f =>
        Logger.info(s"final decision: not matched")
        RuleEngineDecisionNotMatched(f.facts)
    }
  }
}

object RuleEngineInstance extends RuleEngine
