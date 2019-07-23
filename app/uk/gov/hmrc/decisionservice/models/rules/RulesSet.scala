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

import play.api.libs.json.{Format, JsObject, Json}
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

case class RulesSet(INIR35: Option[Seq[JsObject]], OUTOFIR35: Option[Seq[JsObject]],
                    HIGH: Option[Seq[JsObject]], MEDIUM: Option[Seq[JsObject]], LOW: Option[Seq[JsObject]],
                    Unknown: Option[Seq[JsObject]]) {

  def rulesInOrder: Seq[RulesSetWithResult] = Seq(
    rulesAndOutcome(WeightedAnswerEnum.INSIDE_IR35, INIR35),
    rulesAndOutcome(WeightedAnswerEnum.OUTSIDE_IR35, OUTOFIR35),
    rulesAndOutcome(WeightedAnswerEnum.HIGH, HIGH),
    rulesAndOutcome(WeightedAnswerEnum.MEDIUM, MEDIUM),
    rulesAndOutcome(WeightedAnswerEnum.LOW, LOW),
    rulesAndOutcome(WeightedAnswerEnum.UNKNOWN, Unknown)
  ).flatten

  private def rulesAndOutcome(outcome: String, rules: Option[Seq[JsObject]]) = {
    rules.fold(None: Option[RulesSetWithResult]) { res => Some(RulesSetWithResult(outcome, res)) }
  }
}

object RulesSet {
  implicit val format: Format[RulesSet] = Json.format[RulesSet]
}
