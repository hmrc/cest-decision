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
import uk.gov.hmrc.decisionservice.config.ruleSets.js.PartAndParcelRules
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class PartParcelRulesSpec extends UnitSpec with TestFixture {

  val testControlRules: JavaScript = PartAndParcelRules()

  val json = Json.parse(testControlRules.body)

  "Contain all the expected HIGH rules" in {

    val actual = (json \ WeightedAnswerEnum.HIGH).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "workerAsLineManager": true
        |    },
        |    {
        |      "workerReceivesBenefits": true
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected MEDIUM rules" in {

    val actual = (json \ WeightedAnswerEnum.MEDIUM).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "contactWithEngagerCustomer": true,
        |      "workerRepresentsEngagerBusiness": "workForEndClient"
        |    },
        |    {
        |      "contactWithEngagerCustomer": true,
        |      "workerRepresentsEngagerBusiness": "workAsBusiness"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected LOW rules" in {

    val actual = (json \ WeightedAnswerEnum.LOW).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "contactWithEngagerCustomer": false
        |    },
        |    {
        |      "workerRepresentsEngagerBusiness": "workAsIndependent"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }



}
