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

import play.api.libs.json._

case class LogInterview(interview: DecisionRequest,
                        decision: String,
                        score: Score,
                        completed: LocalDateTime)

object LogInterview {

  implicit val writesLocalDateTime: Writes[LocalDateTime] = Writes { date =>
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    JsString(date.format(formatter))
  }

  implicit val writes: OWrites[LogInterview] = OWrites { model =>
    Json.obj(
      "version" -> model.interview.version,
      "correlationID" -> model.interview.correlationID,
      "decision" -> model.decision,
      "setup" -> Json.toJson(model.interview.interview.setup),
      "exit" -> Json.toJson(model.interview.interview.exit),
      "personalService" -> Json.toJson(model.interview.interview.personalService),
      "control" -> Json.toJson(model.interview.interview.control),
      "financialRisk" -> Json.toJson(model.interview.interview.financialRisk),
      "partAndParcel" -> Json.toJson(model.interview.interview.partAndParcel),
      "businessOnOwnAccount" -> Json.toJson(model.interview.interview.businessOnOwnAccount),
      "score" -> Json.toJson(model.score),
      "completed" -> Json.toJson(model.completed)
    )
  }
}
