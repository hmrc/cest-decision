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

package uk.gov.hmrc.decisionservice.model.api

import play.api.libs.json.{JsString, JsValue, Json}
import uk.gov.hmrc.play.test.UnitSpec

import scala.io.Source
import scala.reflect.io.File

class DecisionRequestSpec extends UnitSpec {

  val json =
    """
      |{
      |  "version" : "1.0",
      |  "correlationID": "12345",
      |  "interview":{
      |    "personalService":
      |      {
      |        "contractualRightForSubstitute" : "Yes",
      |        "contractrualObligationForSubstitute" : "No",
      |        "possibleSubstituteRejection" : "Yes",
      |        "engagerArrangeWorker" : "Yes",
      |        "contractTermsWorkerPaysSubstitute" : "Yes",
      |        "workerSentActualSubstitiute" : "Yes",
      |        "possibleHelper" : "Yes",
      |        "workerSentActualHelper" : "Yes",
      |        "workerPayActualHelper" : "Yes"
      |      }
      |    }
      |}
      |
    """.stripMargin

  "decision request json" should {
    "be correctly converted to Scala object" in {
      val parsed = Json.parse(json)
      val jsResult = Json.fromJson[DecisionRequest](parsed)
      jsResult.isSuccess shouldBe true
      val obj = jsResult.get
      val maybePersonalServiceMap = obj.interview.get("personalService")
      maybePersonalServiceMap.isDefined shouldBe true
      maybePersonalServiceMap.map { cluster =>
        cluster should have size 9
        val res = List("contractualRightForSubstitute", "contractrualObligationForSubstitute", "possibleSubstituteRejection").flatMap(cluster.get(_))
        res should contain theSameElementsInOrderAs List("Yes", "No", "Yes")
      }
    }
  }

  "decision request Scala object" should {
    "be correctly converted to json object" in {
      val interview = Map(
        "personalService" -> Map(
          "contractualRightForSubstitute" -> "Yes",
          "contractrualObligationForSubstitute" -> "No",
          "possibleSubstituteRejection" -> "Yes"
        ))
      val decisionRequest = DecisionRequest("0.0.1-alpha", "12345", interview)
      val jsValue:JsValue = Json.toJson(decisionRequest)
      val personalService = jsValue \\ "personalService"
      val factsWithContractualRight = jsValue \\ "contractualRightForSubstitute"
      personalService should have size 1
      factsWithContractualRight should have size 1
    }
  }

  "decision response Scala object" should {
    "be correctly converted to json object" in {
      val interview = Map(
        "personalService" -> Map(
          "contractualRightForSubstitute" -> "Yes",
          "contractrualObligationForSubstitute" -> "No",
          "possibleSubstituteRejection" -> "Yes"
        ))
      val decisionResponse = DecisionResponse("0.0.1-alpha", "12345", Score.createRaw(Map("aa" -> "bb")), "result")
      val response = Json.toJson(decisionResponse)
      val version = response \\ "version"
      version should have size 1
      val correlationID = response \\ "correlationID"
      correlationID should have size 1
      val result = response \\ "result"
      result should have size 1
    }
  }

}
