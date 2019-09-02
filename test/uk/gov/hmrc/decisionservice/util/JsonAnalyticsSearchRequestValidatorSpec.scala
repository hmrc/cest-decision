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

package uk.gov.hmrc.decisionservice.util

import cats.syntax.either._
import uk.gov.hmrc.decisionservice.models.analytics.AnalyticsVersion
import uk.gov.hmrc.play.test.UnitSpec


class JsonAnalyticsSearchRequestValidatorSpec extends UnitSpec {

  val jsonRequestValidator = JsonAnalyticsSearchRequestValidatorFactory(AnalyticsVersion.VERSION150_FINAL).get

  val  valid = """
   |{
   |  "version": "8.7.39",
   |  "start": "2017-12-2 11:00:19",
   |  "end": "2017-12-5 12:22:50"
   |}
   """.stripMargin

  val  invalidDateFormat = """
   |{
   |  "version": "8.7.39",
   |  "start": "201437-4512-2 11:00:19",
   |  "end": "2017-12-555 12:2d2:50"
   |}
   """.stripMargin

  val  missingRequiredFields = """
   |{
   |  "version": "8.7.39",
   |  "start": "2017-12-2 11:00:19"
   |}
   """.stripMargin

  val  invalidSection = """
  |{
  |  "version": "8.7.39",
  |  "start": "2017-12-2 11:00:19",
  |  "end": "2017-12-5 12:22:50",
  |  "someOtherSection": "nostrud elit id ut"
  |}
   """.stripMargin

  "json validator" should {

    "return true for valid json" in {
      validateWithInfo(valid, jsonRequestValidator) shouldBe true
    }

    "return false for invalid date format" in {
      verify(invalidDateFormat, "201437-4512-2 11:00:19")
    }

    "return false for invalid section" in {
      verify(invalidSection, "someOtherSection")
    }

    "return false for missing required fields" in {
      verify(missingRequiredFields, "missing required properties", "end")
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
