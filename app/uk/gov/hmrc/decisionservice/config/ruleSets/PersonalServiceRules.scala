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

import play.api.libs.json.{JsArray, JsValue, Json}
import uk.gov.hmrc.decisionservice.models.PersonalService._
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum._
import uk.gov.hmrc.decisionservice.models.enums.{ArrangedSubstitute, RejectSubstitute}

object PersonalServiceRules extends BaseRules {

  val out: JsArray = Json.arr(
    Json.obj(
      workerSentActualSubstitute -> ArrangedSubstitute.yesClientAgreed,
      workerPayActualSubstitute -> true
    ),
    Json.obj(
      possibleSubstituteRejection -> RejectSubstitute.wouldNotReject,
      possibleSubstituteWorkerPay -> true
    )
  )

  val high: JsArray = Json.arr(
    Json.obj(
      possibleSubstituteRejection -> RejectSubstitute.wouldReject
    ),
    Json.obj(
      workerSentActualSubstitute -> ArrangedSubstitute.notAgreedWithClient,
      wouldWorkerPayHelper -> false
    )
  )

  val medium: JsArray = Json.arr(
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
      //TODO Do we want to return the highest result regardless of a more thorough answer
      possibleSubstituteRejection -> RejectSubstitute.wouldReject,
      wouldWorkerPayHelper -> true
    )
  )

  override val ruleSet: JsValue =
    Json.obj(
      OUTSIDE_IR35.toString -> out,
      HIGH.toString  -> high,
      MEDIUM.toString  -> medium
    )
}
