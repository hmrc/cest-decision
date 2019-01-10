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

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.{BeforeAndAfterEach, Inspectors, LoneElement}
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes.FACT_WITH_TOO_MANY_EMPTY_VALUES
import uk.gov.hmrc.decisionservice.model.rules.{SectionRuleSet, _}
import uk.gov.hmrc.play.test.UnitSpec

class SectionEmptyValuesMatchingSpec extends UnitSpec with BeforeAndAfterEach with ScalaFutures with LoneElement with Inspectors with IntegrationPatience {

  "section fact with empty values matcher" should {
    "produce fact error when there is no match and rule empty values set is does not contain fact empty values set" in {
      val facts = Facts(Map("question1" -> >>>("yes"), "question2" -> >>>(""), "question3" -> >>>("")))
      val sectionRules = List(
        SectionRule(List(>>>("yes"),>>>("yes"),>>>("yes")), >>>("high"  , true)),
        SectionRule(List(>>>("yes"),>>>("no" ),>>>("no" )), >>>("medium", true)),
        SectionRule(List(>>>("no" ),>>>("yes"),>>>(""   )), >>>("low"         ))
      )
      val sectionRuleSet = SectionRuleSet("sectionName",List("question1", "question2", "question3"), sectionRules)
      val response = FactMatcherInstance.matchFacts(facts.facts, sectionRuleSet)
      response.isInvalid shouldBe true
      response.leftMap { error =>
        error(0).code shouldBe FACT_WITH_TOO_MANY_EMPTY_VALUES
      }
    }
    "produce decision when fact has as many empty values as the matching rule" in {
      val facts = Facts(Map("question1" -> >>>("yes"), "question2" -> >>>(""), "question3" -> >>>(""), "question4" -> >>>(""), "question5" -> >>>("")))
      val sectionRules = List(
        SectionRule(List(>>>("yes"),>>>(""),>>>(""),>>>(""),>>>("")), >>>("high"  , false)),
        SectionRule(List(>>>(""),>>>("yes"),>>>(""),>>>(""),>>>("")), >>>("high"  , false)),
        SectionRule(List(>>>("no"),>>>("no"),>>>("no"),>>>(""),>>>("")), >>>("high"  , false)),
        SectionRule(List(>>>("no"),>>>("no"),>>>("yes"),>>>("yes"),>>>("")), >>>("high"  , false))
      )
      val sectionRuleSet = SectionRuleSet("sectionName",List("question1", "question2", "question3", "question4", "question5"), sectionRules)
      val response = FactMatcherInstance.matchFacts(facts.facts, sectionRuleSet)
      response.isValid shouldBe true
      response.map { co =>
        co.value shouldBe "high"
      }
    }
    "produce 'section not valid use case' result when there is no match and rule empty values set is contains fact empty values set" in {
      val facts = Facts(Map("question1" -> >>>("yes"), "question2" -> >>>(""), "question3" -> >>>("")))
      val sectionRules = List(
        SectionRule(List(>>>("yes"),>>>("yes"),>>>("yes")), >>>("high"  , true)),
        SectionRule(List(>>>("yes"),>>>("no" ),>>>(""   )), >>>("medium", true)),
        SectionRule(List(>>>("no" ),>>>(""   ),>>>(""   )), >>>("low"         ))
      )
      val sectionRuleSet = SectionRuleSet("sectionName",List("question1", "question2", "question3"), sectionRules)
      val response = FactMatcherInstance.matchFacts(facts.facts, sectionRuleSet)
      response.isValid shouldBe true
      response.map { r =>
        r shouldBe NotValidUseCase
      }
    }
  }

}
