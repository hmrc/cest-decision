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
import uk.gov.hmrc.decisionservice.models.PartAndParcel._
import uk.gov.hmrc.decisionservice.models.enums.{IdentifyToStakeholders, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class PartParcelRulesSpec extends UnitSpec with TestFixture {

  val json = PartAndParcelRules.ruleSet

  "Contain all the expected HIGH rules" in {

    val actual = (json \ WeightedAnswerEnum.HIGH).get

    val expected = Json.arr(
      Json.obj(
        workerReceivesBenefits -> false,
        workerAsLineManager -> true
      ),
      Json.obj(
        workerReceivesBenefits -> true
      )
    )

    actual shouldBe expected

  }

  "Contain all the expected MEDIUM rules" in {

    val actual = (json \ WeightedAnswerEnum.MEDIUM).get

    val expected = Json.arr(
      Json.obj(
        workerReceivesBenefits -> false,
        workerAsLineManager -> false,
        contactWithEngagerCustomer -> true,
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
      ),
      Json.obj(
        contactWithEngagerCustomer -> true,
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
      )
    )

    actual shouldBe expected

  }

  "Contain all the expected LOW rules" in {

    val actual = (json \ WeightedAnswerEnum.LOW).get

    val expected = Json.arr(
      Json.obj(
        contactWithEngagerCustomer -> false
      ),
      Json.obj(
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
      )
    )

    actual shouldBe expected

  }
}
