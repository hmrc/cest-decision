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

package uk.gov.hmrc.decisionservice.model.rules

import cats.Semigroup
import uk.gov.hmrc.decisionservice.ruleengine.FactValidatingFunctions._
import uk.gov.hmrc.decisionservice.ruleengine.MatchingFunctions._

case class SectionRule(values:List[CarryOver],
                       result:CarryOver,
                       matchingFunction:(SectionRule,List[CarryOver]) => Option[CarryOver] = matches,
                       validateFact:(SectionRule,List[CarryOver]) => Boolean = factsValid
                      )

case class SectionRuleSet(section:String, headings:List[String],rules:List[SectionRule]) extends Semigroup[SectionRuleSet] {
  override def combine(x: SectionRuleSet, y: SectionRuleSet): SectionRuleSet = x
}

case class Rules(rules:List[SectionRuleSet])
