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

import play.api.libs.json.{JsObject, JsString, JsValue, Json}
import uk.gov.hmrc.decisionservice.models.Control

object CheckRules extends RuleChecker {

  private val rule1 = Json.toJson(Control(Some("a"),Some("b"),Some("c"),Some("d"))).as[JsObject]
  private val rule2 = Json.toJson(Control(Some("js"),Some("js"),None,None)).as[JsObject]
  private val rule3 = Json.toJson(Control(None,None,None,None)).as[JsObject]
  private val rule4 = Json.toJson(Control(None,None,None,Some("d"))).as[JsObject]
  private val rule5 = Json.toJson(Control(Some("js"),Some("js"),Some("js"),Some("js"))).as[JsObject]

  val rulesList = List(rule1,rule2,rule3,rule4,rule5)

}
