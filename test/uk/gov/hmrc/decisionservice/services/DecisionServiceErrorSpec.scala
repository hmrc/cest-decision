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

package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.model.api.ErrorCodes
import uk.gov.hmrc.decisionservice.model.rules.{>>>, Facts}
import uk.gov.hmrc.decisionservice.ruleengine.RulesFileMetaData
import uk.gov.hmrc.play.test.UnitSpec

class DecisionServiceErrorSpec extends UnitSpec {

  object DecisionServiceNotExistingCsvTestInstance extends DecisionService {
    lazy val maybeSectionRules = loadSectionRules()
    val csvSectionMetadata = List(
      (7, "business_structure_not_existing.csv", "BusinessStructure"),
      (9, "personal_service_not_existing.csv", "PersonalService"),
      (3, "/decisionservicespec/matrix.csv", "matrix")
    ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
  }

  object DecisionServiceCsvWithErrorsTestInstance extends DecisionService {
    lazy val maybeSectionRules = loadSectionRules()
    val csvSectionMetadata = List(
      (7, "/decisionservicespec/business-structure-errors.csv", "BusinessStructure"),
      (9, "/decisionservicespec/personal-service-errors.csv", "PersonalService"),
      (3, "/decisionservicespec/matrix.csv", "matrix")
    ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
  }

  object DecisionServiceCsvWithBadMetadataTestInstance extends DecisionService {
    lazy val maybeSectionRules = loadSectionRules()
    val csvSectionMetadata = List(
      (17, "/decisionservicespec/business-structure.csv", "BusinessStructure"),
      (19, "/decisionservicespec/personal-service.csv", "PersonalService"),
      (12, "/decisionservicespec/matrix.csv", "matrix")
    ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
  }

  object DecisionServiceNoCsvsInstance extends DecisionService {
    lazy val maybeSectionRules = loadSectionRules()
    val csvSectionMetadata = List()
  }

  val facts = Facts(Map("8a" -> >>>("yes"), "8g" -> >>>("no"), "2" -> >>>("yes"), "10" -> >>>("yes")))

  "decision service with initialization error" should {
    "correctly report multiple aggregated error information" in {
      val maybeDecision = facts ==>: DecisionServiceNotExistingCsvTestInstance
      maybeDecision.isValid shouldBe false
      maybeDecision.leftMap { errors =>
        errors should have size 2
        errors.filter(_.message.contains("resource not found")) should have size 2
      }
    }
    "correctly report errors in csv files" in {
      val maybeDecision = facts ==>: DecisionServiceCsvWithErrorsTestInstance
      maybeDecision.isValid shouldBe false
      maybeDecision.leftMap { errors =>
        errors should have size 5
        errors.filter(_.message.contains("all result tokens are empty in file /decisionservicespec/business-structure-errors.csv")) should have size 1
        errors.filter(_.message.contains("invalid carry over value exit - out of IR35 in row")) should have size 3
        errors.filter(_.message.contains("value Medium / High in row 8 in file /decisionservicespec/personal-service-errors.csv")) should have size 1
      }
    }
    "correctly report errors in metadata files" in {
      val maybeDecision = facts ==>: DecisionServiceCsvWithBadMetadataTestInstance
      maybeDecision.isValid shouldBe false
      maybeDecision.leftMap { errors =>
        errors should have size 31
        errors.filter(_.code == ErrorCodes.INVALID_HEADER_SIZE_IN_RULES_FILE) should have size 3
        errors.filter(_.code == ErrorCodes.RESULT_MISSING_IN_RULES_FILE) should have size 28
      }
    }
    "correctly report error when csv list is empty" in {
      val maybeSectionRules = DecisionServiceNoCsvsInstance.maybeSectionRules
      maybeSectionRules.isValid shouldBe false
      maybeSectionRules.leftMap { errors =>
        errors should have size 1
        errors(0).code shouldBe ErrorCodes.MISSING_RULE_FILES
      }
    }
  }

}
