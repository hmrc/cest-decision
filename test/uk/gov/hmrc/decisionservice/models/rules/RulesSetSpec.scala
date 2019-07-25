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

package uk.gov.hmrc.decisionservice.models.rules

import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum
import uk.gov.hmrc.play.test.UnitSpec

class RulesSetSpec extends UnitSpec {

  "RulesSet" when {

    val rulesSetModel = RulesSet(
      Some(Seq(Json.obj())),
      Some(Seq(Json.obj())),
      Some(Seq(Json.obj())),
      Some(Seq(Json.obj())),
      Some(Seq(Json.obj())),
      Some(Seq(Json.obj()))
    )

    val rulesSetJson = Json.obj(
      "INIR35" -> Json.arr(Json.obj()),
      "OUTOFIR35" -> Json.arr(Json.obj()),
      "HIGH" -> Json.arr(Json.obj()),
      "MEDIUM" -> Json.arr(Json.obj()),
      "LOW" -> Json.arr(Json.obj()),
      "Unknown" -> Json.arr(Json.obj())
    )

    "rulesInOrder is called" when {

      "all options are given" should {

        "return a Seq of RulesSetWithResult" in {

          val expectedResult = Seq(
            RulesSetWithResult(WeightedAnswerEnum.INSIDE_IR35, Seq(Json.obj())),
            RulesSetWithResult(WeightedAnswerEnum.OUTSIDE_IR35, Seq(Json.obj())),
            RulesSetWithResult(WeightedAnswerEnum.HIGH, Seq(Json.obj())),
            RulesSetWithResult(WeightedAnswerEnum.MEDIUM, Seq(Json.obj())),
            RulesSetWithResult(WeightedAnswerEnum.LOW, Seq(Json.obj())),
            RulesSetWithResult(WeightedAnswerEnum.UNKNOWN, Seq(Json.obj()))
          )

          val actualResult = rulesSetModel.rulesInOrder

          actualResult shouldBe expectedResult
        }
      }

      "no options are given" should {

        "return an empty Seq" in {

          val expectedResult = Seq()

          val actualResult = RulesSet(None, None, None, None, None, None).rulesInOrder

          actualResult shouldBe expectedResult
        }
      }
    }

    "written to Json" should {

      "return the correct Json" in {

        val expectedResult = rulesSetJson

        val actualResult = Json.toJson(rulesSetModel)

        actualResult shouldBe expectedResult
      }
    }

    "read from Json" should {

      "return the RulesSet model" in {

        val expectedResult = rulesSetModel

        val actualResult = rulesSetJson.as[RulesSet]

        actualResult shouldBe expectedResult
      }
    }
  }
}
