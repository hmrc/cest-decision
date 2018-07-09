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
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes
import uk.gov.hmrc.decisionservice.model.rules.{>>>, Facts}
import uk.gov.hmrc.play.test.UnitSpec

class RulesLoaderSpec extends UnitSpec with BeforeAndAfterEach with ScalaFutures with LoneElement with Inspectors with IntegrationPatience {

  val csvFilePath = "/rulesloaderspec/section-rules-sample.csv"
  val csvFilePathError = "/rulesloaderspec/section-rules-sample-error.csv"
  val csvFileEmpty = "/rulesloaderspec/section-rules-empty.csv"
  val csvFileHeadersOnly = "/rulesloaderspec/section-rules-headers-only.csv"
  val csvFileHeadersError = "/rulesloaderspec/section-rules-headers-error.csv"
  val csvFileHeadersAndExitError = "/rulesloaderspec/section-rules-headers-exit-error.csv"
  val csvMetadata = RulesFileMetaData(3, csvFilePath, "sectionName")
  val csvMetadataError = RulesFileMetaData(3, csvFilePathError, "sectionName")
  val csvMetadataEmpty = RulesFileMetaData(3, csvFileEmpty, "sectionName")
  val csvMetadataHeadersOnly = RulesFileMetaData(3, csvFileHeadersOnly, "sectionName")
  val csvMetadataHeadersError = RulesFileMetaData(3, csvFileHeadersError, "sectionName")
  val csvMetadataHeadersAndExitError = RulesFileMetaData(3, csvFileHeadersAndExitError, "sectionName")

  "section rules loader" should {
    "load section rules from a csv file" in {
      val maybeRules = RulesLoaderInstance.load(csvMetadata)
      maybeRules.isValid shouldBe true
      maybeRules.map { ruleSet =>
        ruleSet.rules should have size 4
        ruleSet.headings should have size 3
      }
    }
    "return error if a csv file is not found" in {
      val maybeRules = RulesLoaderInstance.load(RulesFileMetaData(3, csvFilePath + "xx", ""))
      maybeRules.isInvalid shouldBe true
      maybeRules.leftMap { errors =>
        errors should have size 1
      }
    }
    "return error if the csv file contains invalid data" in {
      val maybeRules = RulesLoaderInstance.load(csvMetadataError)
      maybeRules.isValid shouldBe false
      maybeRules.leftMap { errors =>
        errors should have size 2
        errors(0).code shouldBe ErrorCodes.INVALID_EXIT_VALUE_IN_RULES_FILE
        errors(1).code shouldBe ErrorCodes.INVALID_EXIT_VALUE_IN_RULES_FILE
      }
    }
    "return error if the csv file is empty" in {
      val maybeRules = RulesLoaderInstance.load(csvMetadataEmpty)
      maybeRules.isValid shouldBe false
      maybeRules.leftMap { errors =>
        errors should have size 1
        errors(0).code shouldBe ErrorCodes.EMPTY_RULES_FILE
      }
    }
    "return no error if the csv file contains only headers" in {
      val maybeRules = RulesLoaderInstance.load(csvMetadataHeadersOnly)
      maybeRules.isValid shouldBe true
      maybeRules.map { ruleSet =>
        ruleSet.rules should have size 0
      }
    }
    "return error if the csv file contains incorrect headers" in {
      val maybeRules = RulesLoaderInstance.load(csvMetadataHeadersError)
      maybeRules.isValid shouldBe false
      maybeRules.leftMap { errors =>
        errors should have size 1
        errors(0).code shouldBe ErrorCodes.INVALID_HEADER_SIZE_IN_RULES_FILE
      }
    }
    "return error if the csv file contains incorrect headers and exit value error" in {
      val maybeRules = RulesLoaderInstance.load(csvMetadataHeadersAndExitError)
      maybeRules.isValid shouldBe false
      maybeRules.leftMap { errors =>
        errors should have size 2
        errors(0).code shouldBe ErrorCodes.INVALID_HEADER_SIZE_IN_RULES_FILE
        errors(1).code shouldBe ErrorCodes.INVALID_EXIT_VALUE_IN_RULES_FILE
      }
    }
    "provide valid input rules for a matcher against a given fact" in {
      val facts = Facts(Map(
        "Q1" -> >>>("yes"),
        "Q2" -> >>>("no"),
        "Q3" -> >>>("yes")))
      val maybeRules = RulesLoaderInstance.load(csvMetadata)
      maybeRules.isValid shouldBe true
      maybeRules.map { ruleSet =>
        ruleSet.rules should have size 4
        ruleSet.headings should have size 3
        val response = FactMatcherInstance.matchFacts(facts.facts, ruleSet)
        response.isValid shouldBe true
        response.map { sectionResult =>
          sectionResult.value shouldBe "low"
          sectionResult.exit shouldBe true
        }
      }
    }
  }
}
