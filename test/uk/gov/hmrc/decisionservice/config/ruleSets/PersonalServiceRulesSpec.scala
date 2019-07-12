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
import uk.gov.hmrc.decisionservice.models.PersonalService._
import uk.gov.hmrc.decisionservice.models.enums.{ArrangedSubstitute, RejectSubstitute, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class PersonalServiceRulesSpec extends UnitSpec with TestFixture {

  val json = PersonalServiceRules.ruleSet

  "Contain all the expected OUT rules" in {

    val actual = (json \ WeightedAnswerEnum.OUTSIDE_IR35).get

    val expected = Json.arr(
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.yesClientAgreed,
        workerPayActualSubstitute -> true
      ),
      Json.obj(
        possibleSubstituteRejection -> RejectSubstitute.wouldNotReject,
        possibleSubstituteWorkerPay -> true
      )
    )

    actual shouldBe expected

  }

  "Contain all the expected HIGH rules" in {

    val actual = (json \ WeightedAnswerEnum.HIGH).get

    val expected = Json.arr(
      Json.obj(
        possibleSubstituteRejection -> RejectSubstitute.wouldReject
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.notAgreedWithClient,
        wouldWorkerPayHelper -> false
      )
    )

    actual shouldBe expected

  }

  "Contain all the expected MEDIUM rules" in {

    val actual = (json \ WeightedAnswerEnum.MEDIUM).get

    val expected = Json.arr(
      Json.obj(
        possibleSubstituteRejection -> RejectSubstitute.wouldNotReject,
        possibleSubstituteWorkerPay -> false
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.notAgreedWithClient,
        wouldWorkerPayHelper -> true
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.yesClientAgreed,
        workerPayActualSubstitute -> false
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.noSubstitutionHappened,
        possibleSubstituteRejection -> RejectSubstitute.wouldReject,
        wouldWorkerPayHelper -> true
      )
    )

    actual shouldBe expected

  }
}
