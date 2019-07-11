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
import uk.gov.hmrc.decisionservice.config.ruleSets.js.EarlyExitRules
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class EarlyExitRulesSpec extends UnitSpec with TestFixture {

  val testControlRules: JavaScript = EarlyExitRules()

  val json = Json.parse(testControlRules.body)

  "Contain the expected IN rule" in {

    val actual = (json \ "InIR35").as[List[JsValue]]

    val expected = Json.parse(
      """
        |[{
        |    "officeHolder": true
        |  }]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

}
