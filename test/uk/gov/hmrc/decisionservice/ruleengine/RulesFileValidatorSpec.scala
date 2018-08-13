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

import uk.gov.hmrc.decisionservice.model.api.ErrorCodes
import uk.gov.hmrc.decisionservice.ruleengine.RulesFileLineValidatorInstance._
import uk.gov.hmrc.play.test.UnitSpec


class RulesFileValidatorSpec extends UnitSpec {

  "section rules file validator" should {
    "validate correct column header size" in {
      val (v,r) = (List("Q1", "Q2", "Q3", "Q4"), List("CarryOver", "Exit"))
      val mayBeValid = validateColumnHeaders(v ::: r, RulesFileMetaData(v.size, "", ""))
      mayBeValid.isValid shouldBe true
    }
    "return error for invalid column header size" in {
      val (v,r) = (List("Q1", "Q2", "Q3", "Q4"), List("CarryOver", "Exit"))
      val mayBeValid = validateColumnHeaders(v, RulesFileMetaData(v.size + 1, "", ""))
      mayBeValid.isValid shouldBe false
      mayBeValid.leftMap { errors =>
        errors should have size 1
        errors(0).code shouldBe ErrorCodes.INVALID_HEADER_SIZE_IN_RULES_FILE
      }
    }
    "return error for rule row size mismatch" in {
      val (v,r) = (List("Low", "Medium", "", "High"), List("In IR35"))
      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(41, "path", ""), 3)
      mayBeValid.isValid shouldBe false
      mayBeValid.leftMap { errors =>
        errors should have size 2
        errors.map(_.message) contains theSameElementsAs(List(
          "row size is 5, expected greater than 41 in row 3 in file path",
          "missing carry over in row 3 in file path"))
      }
    }
    "correctly validate valid rule row" in {
      val (v,r) = (List("Yes", "No", "Yes", ""), List("Low", "false"))
      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(v.size, "", ""), 3)
      mayBeValid.isValid shouldBe true
    }
//    "return error for invalid rule text" in {
//      val (v,r) = (List("Yes", "Bob", "Yes", ""), List("Low", "false"))
//      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(v.size, "path", ""), 4)
//      mayBeValid.isValid shouldBe false
//      mayBeValid.leftMap { errors =>
//        errors(0).code shouldBe ErrorCodes.INVALID_VALUE_IN_RULES_FILE
//        errors(0).message shouldBe "invalid value in row 4 in file path"
//      }
//    }
    "return error for invalid carry over text" in {
      val (v,r) = (List("Yes", "No", "Yes", ""), List("whatever", "true"))
      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(v.size, "path", ""), 2)
      mayBeValid.isValid shouldBe false
      mayBeValid.leftMap { errors =>
        errors(0).code shouldBe ErrorCodes.INVALID_CARRY_OVER_VALUE_IN_RULES_FILE
        errors(0).message shouldBe "invalid carry over value whatever in row 2 in file path"
      }
    }
    "return error when carry over is missing completely" in {
      val (v,r) = (List("Yes", "No", "Yes", ""), List())
      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(v.size, "path", ""), 2)
      mayBeValid.isValid shouldBe false
      mayBeValid.leftMap { errors =>
        errors(0).code shouldBe ErrorCodes.INVALID_ROW_SIZE_IN_RULES_FILE
        errors(0).message shouldBe "row size is 4, expected greater than 4 in row 2 in file path"
      }
    }
    "return no error if only the carry over value is provided and exit value and fact name values are missing" in {
      val (v,r) = (List("Yes", "No", "Yes", ""), List("medium"))
      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(v.size, "", ""), 2)
      mayBeValid.isValid shouldBe true
    }
    "return no error if carry over is fully provided and there are extra cells" in {
      val (v,r) = (List("Yes", "No", "Yes", ""), List("medium", "false", "factName", "extra"))
      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(v.size, "", ""), 2)
      mayBeValid.isValid shouldBe true
    }
    "return error for invalid exit text" in {
      val (v,r) = (List("Yes", "No", "Yes", ""), List("low", "wrong!!!"))
      val mayBeValid = validateLine(v ::: r, RulesFileMetaData(v.size, "path", ""), 2)
      mayBeValid.isValid shouldBe false
      mayBeValid.leftMap { errors =>
        errors(0).code shouldBe ErrorCodes.INVALID_EXIT_VALUE_IN_RULES_FILE
        errors(0).message shouldBe "invalid exit value in row 2 in file path"
      }
    }
  }
}
