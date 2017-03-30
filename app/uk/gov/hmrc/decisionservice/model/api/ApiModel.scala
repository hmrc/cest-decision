/*
 * Copyright 2017 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.model.api

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.Versions
import uk.gov.hmrc.decisionservice.model.rules.CarryOver


case class DecisionRequest(version:String, correlationID:String, interview:Map[String,Map[String,String]])

object DecisionRequest {
  implicit val questionSetFormat: Format[DecisionRequest] = Json.format[DecisionRequest]
}

case class Score( score:Map[String,String])

object Score {
  implicit val scoreFormat: Format[Score] = Json.format[Score]
  def elements(version:String):List[String] = {
    val versionToElements = Map(
      Versions.VERSION110_FINAL -> List("control", "financialRisk", "partAndParcel", "personalService"),
      Versions.VERSION111_FINAL -> List("control", "financialRisk", "partAndParcel", "personalService"),
      Versions.VERSION120_FINAL -> List("control", "financialRisk", "partAndParcel", "personalService"),
      Versions.VERSION130_FINAL -> List("control", "financialRisk", "partAndParcel", "personalService")
    )
    versionToElements.getOrElse(version, List())
  }

  def create(facts: Map[String, CarryOver], version:String): Map[String, String] =
    facts.toList.collect { case (a, co) if (elements(version).contains(a)) => (a, formatValue(co.value)) }.toMap

  def createRaw(m: Map[String, String]) = m

  def formatValue(value: String) = value match {
    case v@"NotValidUseCase" => v
    case v => v.toUpperCase
  }
}

case class DecisionResponse(version:String, correlationID:String, score:Map[String,String], result:String)

object DecisionResponse {
  implicit val decisionResponseFormat: Format[DecisionResponse] = Json.format[DecisionResponse]
}

case class ErrorResponse(code:Int, message:String)

object ErrorResponse {
  implicit val errorResponseFormat: Format[ErrorResponse] = Json.format[ErrorResponse]
}
