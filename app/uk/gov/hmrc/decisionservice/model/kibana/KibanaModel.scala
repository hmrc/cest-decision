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

package uk.gov.hmrc.decisionservice.model.kibana

import play.api.libs.json.{Format, Json}

case class KibanaIndexNested(_index:String, _type:String, _id:Int)

case class KibanaIndex(_id:Int){
  def asLogLine = "{\"index\":" + Json.toJson(KibanaIndexNested("decision", "act", _id)).toString + "}"
}

case class KibanaRow(row:Map[String,String]){
  def asLogLine = Json.toJson(row).toString.replaceAllLiterally("{\"row\":{", "{").replaceAllLiterally("}}","}")
}

object KibanaIndexNested {
  implicit val kibanaIndexNestedFormat: Format[KibanaIndexNested] = Json.format[KibanaIndexNested]
}

object KibanaRow {
  implicit val kibanaRowFormat: Format[KibanaRow] = Json.format[KibanaRow]
}
