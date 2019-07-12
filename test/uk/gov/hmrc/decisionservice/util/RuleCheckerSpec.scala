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
import play.api.libs.json.{JsObject, JsString, JsValue, Json}
import uk.gov.hmrc.decisionservice.DecisionServiceVersions
import uk.gov.hmrc.decisionservice.models.{Control, PartAndParcel}
import uk.gov.hmrc.decisionservice.testutil.RequestAndDecision
import uk.gov.hmrc.play.test.UnitSpec

class RuleCheckerSpec extends UnitSpec {

  "CheckRules" should {
    "check control rules" in {

      val x = new ControlRules

      val input = Json.toJson(Control(Some("cannotMoveWorkerWithoutNewAgreement"),Some("workerDecidesWithoutInput"),Some("workerDecideSchedule"),Some("workerChooses"))).as[JsObject]


      x.checkRules(input) shouldBe "OutOfIR35"


    }

    "check bad control rules" in {

      val x = new ControlRules

      val input1 = Json.toJson(Control(Some("dd"),Some("e"),Some("workerDecideSchedule"),Some("workerChooses"))).as[JsObject]


      x.checkRules(input1) shouldBe "OutOfIR35"


    }
    }
}
