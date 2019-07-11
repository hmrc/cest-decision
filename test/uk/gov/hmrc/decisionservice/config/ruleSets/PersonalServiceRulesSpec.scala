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
import uk.gov.hmrc.decisionservice.config.ruleSets.js.PersonalServiceRules
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class PersonalServiceRulesSpec extends UnitSpec with TestFixture {

  val testControlRules: JavaScript = PersonalServiceRules()

  val json = Json.parse(testControlRules.body)

  "Contain all the expected OUT rules" in {

    val actual = (json \ "OutOfIR35").as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "workerSentActualSubstitute": "yesClientAgreed",
        |      "workerPayActualSubstitute": true
        |    },
        |    {
        |      "possibleSubstituteRejection": "wouldNotReject",
        |      "possibleSubstituteWorkerPay": true
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected HIGH rules" in {

    val actual = (json \ "HIGH").as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "possibleSubstituteRejection": "wouldReject"
        |    },
        |    {
        |      "workerSentActualSubstitute": "notAgreedWithClient",
        |      "wouldWorkerPayHelper": false
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected MEDIUM rules" in {

    val actual = (json \ "MEDIUM").as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "possibleSubstituteRejection": "wouldNotReject",
        |      "possibleSubstituteWorkerPay": false
        |    },
        |    {
        |      "workerSentActualSubstitute": "notAgreedWithClient",
        |      "wouldWorkerPayHelper": true
        |    },
        |    {
        |      "workerSentActualSubstitute": "yesClientAgreed",
        |      "workerPayActualSubstitute": false
        |    },
        |    {
        |      "workerSentActualSubstitute": "noSubstitutionHappened",
        |      "possibleSubstituteRejection": "wouldReject",
        |      "wouldWorkerPayHelper": true
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }



}
