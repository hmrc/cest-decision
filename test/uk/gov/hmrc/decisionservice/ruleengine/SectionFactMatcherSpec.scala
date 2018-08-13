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

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.{BeforeAndAfterEach, Inspectors, LoneElement}
import uk.gov.hmrc.decisionservice.model.rules._
import uk.gov.hmrc.play.test.UnitSpec

class SectionFactMatcherSpec extends UnitSpec with BeforeAndAfterEach with ScalaFutures with LoneElement with Inspectors with IntegrationPatience {

  "section fact matcher" should {
    "produce correct result for sample facts" in {
      val facts = Facts(Map(
        "question1" -> >>>("yes"),
        "question2" -> >>>("no"),
        "question3" -> >>>("yes")))
      val rules = List(
        SectionRule(List(>>>("yes"),>>>("yes"),>>>("yes")), >>>("high"  , true)),
        SectionRule(List(>>>("yes"),>>>("no" ),>>>("no" )), >>>("medium", true)),
        SectionRule(List(>>>("yes"),>>>("no" ),>>>("yes")), >>>("low"   , true)),
        SectionRule(List(>>>("no" ),>>>(""   ),>>>("yes")), >>>("low"         ))
      )
      val ruleSet = SectionRuleSet("sectionName", List("question1", "question2", "question3"), rules)
      val response = FactMatcherInstance.matchFacts(facts.facts, ruleSet)
      response.isValid shouldBe true
      response.map { sectionResult =>
        sectionResult.value shouldBe "low"
        sectionResult.exit shouldBe true
      }
    }
    "produce 'section not valid use case' result for a fact with missing obligatory answers" in {
      val facts = Facts(Map(
        "question1" -> >>>("yes"),
        "question3" -> >>>("yes")))
      val rules = List(
        SectionRule(List(>>>("yes"),>>>("yes"),>>>("yes")), >>>("high"  , true)),
        SectionRule(List(>>>("yes"),>>>("no" ),>>>("no" )), >>>("medium", true)),
        SectionRule(List(>>>("yes"),>>>("no" ),>>>("yes")), >>>("low"   , true)),
        SectionRule(List(>>>("no" ),>>>(""   ),>>>("yes")), >>>("low"         ))
      )
      val ruleSet = SectionRuleSet("sectionName", List("question1", "question2", "question3"), rules)
      val response = FactMatcherInstance.matchFacts(facts.facts, ruleSet)
      response.isValid shouldBe true
      response.map { sectionResult =>
        sectionResult shouldBe NotValidUseCase
      }
    }
    "produce 'section not valid use case' result when match is not found" in {
      val facts = Facts(Map(
        "question1" -> >>>("yes"),
        "question2" -> >>>("no"),
        "question3" -> >>>("yes")))
      val rules = List(
        SectionRule(List(>>>("yes"),>>>("yes"),>>>("yes")), >>>("high"  , true)),
        SectionRule(List(>>>("yes"),>>>("no" ),>>>("no" )), >>>("medium", true)),
        SectionRule(List(>>>("no" ),>>>(""   ),>>>("yes")), >>>("low"         ))
      )
      val ruleSet = SectionRuleSet("sectionName", List("question1", "question2", "question3"), rules)
      val response = FactMatcherInstance.matchFacts(facts.facts, ruleSet)
      response.isValid shouldBe true
      response.map { r =>
        r shouldBe NotValidUseCase
      }
    }
  }

}
