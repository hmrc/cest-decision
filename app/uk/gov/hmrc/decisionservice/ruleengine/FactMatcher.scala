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

package uk.gov.hmrc.decisionservice.ruleengine


import cats.data.Validated
import play.api.Logger
import uk.gov.hmrc.decisionservice.Validation
import uk.gov.hmrc.decisionservice.model._
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes.{FACT_WITH_TOO_MANY_EMPTY_VALUES, INCORRECT_FACT}
import uk.gov.hmrc.decisionservice.model.rules.{CarryOver, _}

import scala.annotation.tailrec


sealed trait FactMatcher {
  import FactMatcherHelper._

  def matchFacts(facts: Map[String,CarryOver], ruleSet: SectionRuleSet): Validation[CarryOver] =
  {
    @tailrec
    def go(factValues: List[CarryOver], rules:List[SectionRule]):Validation[CarryOver] = rules match {
      case Nil => noMatchResult(facts, ruleSet.rules)
      case rule :: xs if !factsValid(factValues, rule) => Validated.invalid(List(FactError(INCORRECT_FACT, "incorrect fact")))
      case rule :: xs =>
        rule.matchingFunction(rule,factValues) match {
          case Some(result) => Validated.valid(result)
          case None => go(factValues, xs)
        }
    }

    val factValues = ruleSet.headings.map(a => facts.getOrElse(a,EmptyCarryOver))
    go(factValues, ruleSet.rules)
  }

  def noMatchResult(facts: Map[String,CarryOver], rules: List[SectionRule]): Validation[CarryOver] = {
    val factSet = factsEmptySet(facts)
    val rulesSet = rulesMaxEmptySet(rules)
    if (factSet.subsetOf(rulesSet)) Validated.valid(NotValidUseCase)
    else Validated.invalid(List(FactError(FACT_WITH_TOO_MANY_EMPTY_VALUES, ("facts have too many empty values"))))
  }

}

object FactMatcherInstance extends FactMatcher

object FactMatcherHelper {
  def factsValid(factValues: List[CarryOver], rule:SectionRule):Boolean = rule.validateFact(rule,factValues)
  def factsEmptySet(facts:Map[String,CarryOver]):Set[Int] = >>>.emptyPositions(facts.values)
  def rulesMaxEmptySet(rules: List[SectionRule]):Set[Int] = {
    def ruleEmptySet(rules: SectionRule):Set[Int] = >>>.emptyPositions(rules.values)
    val sets = for { r <- rules } yield { ruleEmptySet(r) }
    sets.foldLeft(Set[Int]())((a,b) => a ++ b)
  }
}
