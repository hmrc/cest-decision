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

import uk.gov.hmrc.decisionservice.model.rules._
import uk.gov.hmrc.play.test.UnitSpec

class RuleEngineSpec extends UnitSpec {
  val ruleEngine = RuleEngineInstance
  val sectionRules1 = List(
    SectionRule(List(>>>("yes"), >>>("yes"), >>>("yes")),   >>>("high"  , true)),
    SectionRule(List(>>>("yes"), >>>("no") , >>>("no")) ,   >>>("medium", true)),
    SectionRule(List(>>>("yes"), >>>("no") , >>>("yes")),   >>>("low"   , false)),
    SectionRule(List(>>>("yes"), >>>("")   , >>>("yes")),   >>>("low"   ))
  )
  val sectionRules2 = List(
    SectionRule(List(>>>("no"), >>>("low"   ), >>>("yes")),   >>>("medium2"  , true)),
    SectionRule(List(>>>("no"), >>>("medium"), >>>("yes")),   >>>("high2"    , true)),
    SectionRule(List(>>>("no"), >>>("high"  ), >>>("yes")),   >>>("low2"     , true))
  )
  val ruleSet1 = SectionRuleSet("sectionName1", List("question1", "question2", "question3"), sectionRules1)
  val ruleSet2 = SectionRuleSet("sectionName2", List("question4", "sectionName1", "question6"), sectionRules2)

  val rules = Rules(List(ruleSet1,ruleSet2))

  "rule engine" should {
    "produce correct decision for a sample fact and rules with a simple inference, new fact has a section name ('sectionName1')" in {
      val facts = Facts(Map(
        "question1" -> >>>("yes"),
        "question2" -> >>>("no" ),
        "question3" -> >>>("yes"),
        "question4" -> >>>("no" ),
        "question6" -> >>>("yes")))
      val maybeDecision = ruleEngine.processRules(rules, facts)
      maybeDecision.isValid shouldBe true
      maybeDecision.map { decision =>
        decision.value shouldBe "medium2"
      }
    }
    "produce correct decision for a sample fact and rules with a simple inference, new fact has a custom name ('customName')" in {
      val customName = "customName"
      val sectionRules1 = List(
        SectionRule(List(>>>("yes"), >>>("yes"), >>>("yes")),   >>>("high"  , false, Some(customName))),
        SectionRule(List(>>>("yes"), >>>("no") , >>>("no")) ,   >>>("medium", true , Some(customName))),
        SectionRule(List(>>>("yes"), >>>("no") , >>>("yes")),   >>>("low"   , false, Some(customName))),
        SectionRule(List(>>>("yes"), >>>("")   , >>>("yes")),   >>>("low"   ))
      )
      val ruleSet1 = SectionRuleSet("sectionName1", List("question1", "question2", "question3"), sectionRules1)
      val ruleSet2 = SectionRuleSet("sectionName2", List("question4", customName, "question6"), sectionRules2)
      val rules = Rules(List(ruleSet1,ruleSet2))
      val facts = Facts(Map(
        "question1" -> >>>("yes"),
        "question2" -> >>>("yes" ),
        "question3" -> >>>("yes"),
        "question4" -> >>>("no" ),
        "question6" -> >>>("yes")))
      val maybeDecision = ruleEngine.processRules(rules, facts)
      maybeDecision.isValid shouldBe true
      maybeDecision.map { decision =>
        decision.value shouldBe "low2"
      }
    }
    "produce correct decision for a sample fact and rules with a short-circuit exit" in {
      val facts = Facts(Map(
        "question1" -> >>>("yes"),
        "question2" -> >>>("yes"),
        "question3" -> >>>("yes"),
        "question4" -> >>>("no" ),
        "question6" -> >>>("yes")))
      val maybeDecision = ruleEngine.processRules(rules, facts)
      maybeDecision.isValid shouldBe true
      maybeDecision.map { decision =>
        decision.value shouldBe "high"
      }
    }
    "produce undecided for facts leading to no decision" in {
      val facts = Facts(Map(
        "question1" -> >>>("yes"),
        "question2" -> >>>("no" ),
        "question3" -> >>>("yes"),
        "question4" -> >>>("yes"),
        "question6" -> >>>("yes")))
      val maybeDecision = ruleEngine.processRules(rules, facts)
      maybeDecision.isValid shouldBe true
      maybeDecision.map { decision =>
        decision.value shouldBe "Not Matched"
        val maybeCarryOver = decision.facts.get("sectionName2")
        maybeCarryOver.isDefined shouldBe true
        maybeCarryOver.map{ carryOver =>
          carryOver shouldBe NotValidUseCase
        }
      }
    }
  }
}
