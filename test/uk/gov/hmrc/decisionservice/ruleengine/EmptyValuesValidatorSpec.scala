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
import uk.gov.hmrc.decisionservice.model._
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes
import uk.gov.hmrc.decisionservice.model.rules._
import uk.gov.hmrc.play.test.UnitSpec

class EmptyValuesValidatorSpec extends UnitSpec with BeforeAndAfterEach with ScalaFutures with LoneElement with Inspectors with IntegrationPatience {

  val facts = Facts(Map(
    ("question1" -> >>>("yes")),
    ("question2" -> >>>("")),
    ("question3" -> >>>(""))))

  "empty values validator" should {
    "produce fact error if FactsEmptySet is a subset of MaximumRulesEmptySet" in {
      val rules = List(
        SectionRule(List(>>>("yes"), >>>("yes"), >>>("yes")), >>>("high"  , true)),
        SectionRule(List(>>>("yes"), >>>("no" ), >>>("no" )), >>>("medium", true)),
        SectionRule(List(>>>("no" ), >>>("yes"), >>>(""   )), >>>("low"         ))
      )

      val error = FactMatcherInstance.noMatchResult(facts.facts,rules)
      error.isInvalid should be (true)
      error.leftMap { errors =>
        errors should have size 1
        errors(0).code shouldBe ErrorCodes.FACT_WITH_TOO_MANY_EMPTY_VALUES
      }
    }
    "produce 'not valid use case' result if FactsEmptySet is a superset of MaximumRulesEmptySet" in {
      val rules = List(
        SectionRule(List(>>>("yes"), >>>("yes"), >>>("yes")), >>>("high"  , true)),
        SectionRule(List(>>>("yes"), >>>("no" ), >>>(""   )), >>>("medium", true)),
        SectionRule(List(>>>("no" ), >>>(""   ), >>>(""   )), >>>("low"         ))
      )

      val result = FactMatcherInstance.noMatchResult(facts.facts,rules)
      result.isValid should be (true)
      result.map { r =>
        r shouldBe NotValidUseCase
      }
    }
  }

}
