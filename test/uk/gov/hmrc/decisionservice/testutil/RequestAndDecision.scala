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

package uk.gov.hmrc.decisionservice.testutil

import uk.gov.hmrc.decisionservice.DecisionServiceVersions
import uk.gov.hmrc.decisionservice.model.api.DecisionRequest

import scala.util.Try
import uk.gov.hmrc.decisionservice.util.FileTokenizer.{tokenize, tokenizeWithTrailingSeparator}


case class RequestAndDecision(request:DecisionRequest, expectedDecision:String)

object RequestAndDecision {
  val CORRELATION_ID_STRING: String = "test-correlation-id"

  def readFlattened(path:String, version:String):Try[List[RequestAndDecision]] = {
    def create(clusterNames:List[String], tagNames:List[String], answersAndDecision:List[String]):RequestAndDecision = {
      val expectedDecision = answersAndDecision.last
      val answers = answersAndDecision.dropRight(1)
      val interviewRaw = clusterNames.zip(tagNames.zip(answers))
      val interview = interviewRaw.groupBy{case (cl,p) => cl}.map{case (cl,t3) => (cl,t3.map{case (t1,t2) => t2}.toMap)}
      RequestAndDecision(DecisionRequest(version, CORRELATION_ID_STRING, interview), expectedDecision)
    }
    tokenize(path).map { tokens =>
        val clusterNames = tokens(0).dropRight(1)
        val tagNames = tokens(1)
        val answersAndDecision = tokens.drop(2)
        answersAndDecision.map(create(clusterNames, tagNames, _))
    }
  }

  def readFlattenedTransposed(path:String, version:String):Try[RequestAndDecision] = {
    def create(clusterNames:List[String], tagNames:List[String], answers:List[String], expectedDecision:String):RequestAndDecision = {
      val interviewRaw = clusterNames.zip(tagNames.zip(answers))
      val interview = interviewRaw.groupBy{case (cl,p) => cl}.map{case (cl,t3) => (cl,t3.map{case (t1,t2) => t2}.toMap)}
      RequestAndDecision(DecisionRequest(version, CORRELATION_ID_STRING, interview), expectedDecision)
    }
    tokenize(path).map { tokens =>
      val clusterNames = tokens.collect { case a if a.size > 2 => a(0) }
      val tagNames = tokens.collect { case a if a.size > 2 => a(1) }
      val answers = tokens.collect { case a if a.size > 2 => a(2) }
      val expectedDecision = tokens.last.last
      create(clusterNames, tagNames, answers, expectedDecision)
    }
  }

  def readAggregatedTransposed(path:String, version:String):Try[List[RequestAndDecision]] = {
    def create(clusterNames:List[String], tagNames:List[String], answers:List[String], expectedDecision:String):RequestAndDecision = {
      val interviewRaw = clusterNames.zip(tagNames.zip(answers).collect{case (t,a) if !a.isEmpty => (t,a)})
      val interview = interviewRaw.groupBy{case (cl,p) => cl}.map{case (cl,t3) => (cl,t3.map{case (t1,t2) => t2}.toMap)}
      RequestAndDecision(DecisionRequest(version, CORRELATION_ID_STRING, interview), expectedDecision)
    }
    tokenizeWithTrailingSeparator(path).map { tokens =>
      val t = tokens.transpose
      val clusterNames = t.head.dropRight(1)
      val tagNames = t.drop(1).head.dropRight(1)
      val answersAndDecisionRows = t.drop(2)
      answersAndDecisionRows.map{ answersAndDecisionRow =>
        create(clusterNames, tagNames, answersAndDecisionRow.dropRight(1), answersAndDecisionRow.last)
      }
    }
  }

}
