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

package uk.gov.hmrc.decisionservice.config.ruleSets

import play.api.libs.json.{JsValue, Json}
import play.twirl.api.JavaScript
import uk.gov.hmrc.decisionservice.config.ruleSets.js.MatrixOfMatricesRules
import uk.gov.hmrc.decisionservice.models.enums.ResultEnum
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class MatrixOfMatricesRulesSpec extends UnitSpec with TestFixture {

  val testControlRules: JavaScript = MatrixOfMatricesRules()

  val json = Json.parse(testControlRules.body)

  "Contain all the expected InIR35 rules" in {

    val actual = (json \ ResultEnum.INSIDE_IR35).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "personalService": "Medium",
        |      "control": "Medium",
        |      "financialRisk": "Low",
        |      "partAndParcel": "High"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "Medium",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Medium"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "High",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "High",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Medium"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "High",
        |      "financialRisk": "Low",
        |      "partAndParcel": "High"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "Medium",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "Medium",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Medium"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "Medium",
        |      "financialRisk": "Low",
        |      "partAndParcel": "High"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "High",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "High",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Medium"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "High",
        |      "financialRisk": "Low",
        |      "partAndParcel": "High"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "High",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "High",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Medium"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "High",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "High",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Medium"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "High",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "High"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "Medium",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "High"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "High",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "High"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected Indeterminate rules" in {

    val actual = (json \ ResultEnum.UNKNOWN).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "personalService": "Medium",
        |      "control": "Medium",
        |      "financialRisk": "Low",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "Medium",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "Medium",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Medium"
        |    },
        |    {
        |      "personalService": "Medium",
        |      "control": "Medium",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "High"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "Medium",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Low"
        |    },
        |    {
        |      "personalService": "High",
        |      "control": "Medium",
        |      "financialRisk": "Medium",
        |      "partAndParcel": "Medium"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }
}
