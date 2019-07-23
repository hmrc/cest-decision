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

import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.models.enums.{ResultEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.models.enums.Section._
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum._
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class MatrixOfMatricesRulesSpec extends UnitSpec with TestFixture with RuleSetHelperMethods {

  val json = MatrixOfMatricesRules.ruleSet

  "Contain all the expected InIR35 rules" in {

    val actual = (json \ WeightedAnswerEnum.INSIDE_IR35).get

    val expected = Json.arr(
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      )
    )

    actual shouldBe expected

  }

  "Contain all the expected Indeterminate rules" in {

    val actual = (json \ ResultEnum.UNKNOWN).get

    val expected = Json.arr(
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      )
    )

    actual shouldBe expected

  }
}
