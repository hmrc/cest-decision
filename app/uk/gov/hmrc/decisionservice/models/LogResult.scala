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

package uk.gov.hmrc.decisionservice.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.joda.time.DateTime
import play.api.libs.json._
import uk.gov.hmrc.mongo.json.ReactiveMongoFormats

case class LogResult(interview: DecisionRequest,
                     decision: String,
                     decisionWithoutBOOA: String,
                     booaWeighting: String,
                     score: Score,
                     scoreWithoutBOOA: Score,
                     completed: DateTime)

object LogResult {

  implicit val dateFormat = ReactiveMongoFormats.dateTimeFormats

  implicit val writes: OWrites[LogResult] = OWrites { model =>
    Json.obj(
      "interview" -> Json.toJson(model.interview),
      "decision" -> model.decision,
      "decisionWithoutBOOA" -> model.decisionWithoutBOOA,
      "booaWeighting" -> model.booaWeighting,
      "score" -> Json.toJson(model.score),
      "scoreWithoutBOOA" -> Json.toJson(model.scoreWithoutBOOA),
      "completed" -> Json.toJson(model.completed)
    )
  }
}
