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

import java.io.File
import java.net.URI

import play.api.libs.json.{JsArray, JsObject, JsString, Json}

import scala.io.Source

object JsonFileReader {

  lazy val controlFile = Json.parse(Source.fromFile("conf/jsonRules/1.5.0/control.scala.json").mkString).as[JsArray].value.map { json =>
    json.as[JsObject]
  }.toList

  lazy val exitFile = Json.parse(Source.fromFile("conf/jsonRules/1.5.0/exit.scala.json").mkString).as[JsArray].value.map { json =>
    json.as[JsObject]
  }.toList

  lazy val financialRiskFile = Json.parse(Source.fromFile("conf/jsonRules/1.5.0/financialRisk.scala.json").mkString).as[JsArray].value.map { json =>
    json.as[JsObject]
  }.toList

  lazy val partAndParcelFile = Json.parse(Source.fromFile("conf/jsonRules/1.5.0/partAndParcel.scala.json").mkString).as[JsArray].value.map { json =>
    json.as[JsObject]
  }.toList

  lazy val personalServiceFile = Json.parse(Source.fromFile("conf/jsonRules/1.5.0/personalService.scala.json").mkString).as[JsArray].value.map { json =>
    json.as[JsObject]
  }.toList

  lazy val matrixOfMatricesFile = Json.parse(Source.fromFile("conf/jsonRules/1.5.0/matrixOfMatrices.scala.json").mkString).as[JsArray].value.map { json =>
    json.as[JsObject]
  }.toList

}
