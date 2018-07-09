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

package uk.gov.hmrc.decisionservice.util

import cats.syntax.either._
import uk.gov.hmrc.decisionservice.{AnalyticsVersions, DecisionServiceVersions}
import uk.gov.hmrc.play.test.UnitSpec


class JsonAnalyticsRequestValidatorSpec extends UnitSpec {

  val jsonRequestValidator = JsonAnalyticsRequestValidatorFactory(AnalyticsVersions.VERSION150_FINAL).get

  val  valid = """
   |{
   |  "version": "1.5.0-final",
   |  "compressedInterview": "nisi elit cillum dolore",
   |  "route": "incididunt reprehenderit",
   |  "decision": "Excepteur de",
   |  "completed": "2017-06-27 11:02:02",
   |  "setup": {
   |    "endUserRole": "personDoingWork",
   |    "hasContractStarted": "Yes",
   |    "provideServices": "partnership"
   |  },
   |  "exit": {
   |    "officeHolder": "Yes"
   |  },
   |  "partAndParcel": {
   |    "workerRepresentsEngagerBusiness": "workAsBusiness"
   |  }
   |}
   """.stripMargin

  val  invalidDateFormat = """
   |{
   |  "version": "1.5.0-final",
   |  "compressedInterview": "nisi elit cillum dolore",
   |  "route": "incididunt reprehenderit",
   |  "decision": "Excepteur de",
   |  "completed": "27-06-2017 11:02:02",
   |  "setup": {
   |    "endUserRole": "personDoingWork",
   |    "hasContractStarted": "Yes",
   |    "provideServices": "partnership"
   |  },
   |  "exit": {
   |    "officeHolder": "Yes"
   |  },
   |  "partAndParcel": {
   |    "workerRepresentsEngagerBusiness": "workAsBusiness"
   |  }
   |}
   """.stripMargin

  val  invalidAnswerOption = """
   |{
   |  "version": "1.5.0-final",
   |  "compressedInterview": "nisi elit cillum dolore",
   |  "route": "incididunt reprehenderit",
   |  "decision": "Excepteur de",
   |  "completed": "2017-06-27 11:02:02",
   |  "setup": {
   |    "endUserRole": "personDoingWork",
   |    "hasContractStarted": "Yes",
   |    "provideServices": "partnership"
   |  },
   |  "exit": {
   |    "officeHolder": "NotSure"
   |  }
   |}
   """.stripMargin

  val  missingRequiredFields = """
   |{
   |  "version": "1.5.0-final",
   |  "compressedInterview": "laborum ullamco mollit laboris",
   |  "route": "cupidatat",
   |  "decision": "voluptate",
   |  "setup": {
   |    "endUserRole": "placingAgency",
   |    "hasContractStarted": "Yes",
   |    "provideServices": "intermediary"
   |  }
   |}
   """.stripMargin

  val  invalidSection = """
  |{
  |  "version": "1.5.0-final",
  |  "compressedInterview": "minim",
  |  "route": "par",
  |  "completed": "2017-06-27 11:02:02",
  |  "decision": "officia sit",
  |  "setup": {
  |    "endUserRole": "placingAgency",
  |    "hasContractStarted": "Yes",
  |    "provideServices": "partnership"
  |  },
  |  "exit": {
  |    "officeHolder": "Yes"
  |  },
  |  "someOtherSection": {
  |    "workerMainIncome": "incomeCommission",
  |    "paidForSubstandardWork": "noObligationToCorrect"
  |  },
  |  "count": "nostrud elit id ut"
  |}
   """.stripMargin

  "json validator" should {

    "return true for valid json" in {
      validateWithInfo(valid, jsonRequestValidator) shouldBe true
    }

    "return false for invalid answer option" in {
      verify(invalidAnswerOption, "NotSure")
    }

    "return false for invalid date format" in {
      verify(invalidDateFormat, "27-06-2017 11:02:02")
    }

    "return false for invalid section" in {
      verify(invalidSection, "someOtherSection")
    }

    "return false for missing required fields" in {
      verify(missingRequiredFields, "missing required properties", "exit", "completed")
    }
  }

  private def validateWithInfo(json: String, validator: JsonValidatorTrait) = {
    val result = validator.validate(json)
    if (result.isLeft){
      result.leftMap( r => info(r))
    }
    result.isRight
  }

  def verify(s:String, expectedText:String*):Unit = {
    val result = jsonRequestValidator.validate(s)
    result.isRight shouldBe false
    result.leftMap { report => {
      expectedText.foreach { text =>
        info(report)
        report.contains(text) shouldBe true
      }
    }
    }
  }

}
