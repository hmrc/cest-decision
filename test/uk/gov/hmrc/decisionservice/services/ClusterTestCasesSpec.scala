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

package uk.gov.hmrc.decisionservice.services

import play.api.Logger
import uk.gov.hmrc.decisionservice.ruleengine.{FactMatcherInstance, RulesFileMetaData, RulesLoaderInstance}
import uk.gov.hmrc.decisionservice.testutil.FactsAndDecision
import uk.gov.hmrc.play.test.UnitSpec

case class ScenarioTestCase(factsPath:String, clusterName:String, rulesPath:String, numOfValueColumns:Int)

class ClusterTestCasesSpec extends UnitSpec {
  private val scenarioTestCases = List(
    ScenarioTestCase("/test-scenarios/cluster-test-cases/part-of-organisation.csv", "part_of_organisation","/tables/part_of_organisation.csv",5),
    ScenarioTestCase("/test-scenarios/cluster-test-cases/financial-risk.csv", "financial_risk","/tables/financial_risk.csv",24),
    ScenarioTestCase("/test-scenarios/cluster-test-cases/control-onlyPassingCases.csv", "control", "/tables/1.0.1-beta/control.csv",13),
    ScenarioTestCase("/test-scenarios/cluster-test-cases/misc.csv", "miscellaneous","/tables/misc.csv",1))

  "test case reader " should {
    "read valid cluster test case file" in {
      for (scenarioTestCase <- scenarioTestCases) {
        Logger.info("================= Running tests for Cluster: " + scenarioTestCase.clusterName + " ===================")
        val tryFactsAndDecision = FactsAndDecision.read(scenarioTestCase.factsPath)
        tryFactsAndDecision.isSuccess shouldBe true
        tryFactsAndDecision.map { _.foreach(runAndVerifyTestCase(_, scenarioTestCase)) }
        Logger.info("================= Finished tests for Cluster: " + scenarioTestCase.clusterName + " ===================")
      }
    }
  }

  def runAndVerifyTestCase(testCase:FactsAndDecision, metaData:ScenarioTestCase):Unit = {
    val maybeRules = RulesLoaderInstance.load(RulesFileMetaData(metaData.numOfValueColumns, metaData.rulesPath, metaData.clusterName))
    maybeRules.isValid shouldBe true
    maybeRules.map { ruleSet =>
      val response = FactMatcherInstance.matchFacts(testCase.request.facts, ruleSet)
      response.isValid shouldBe true
      response.map { _ shouldBe testCase.expectedDecision }
    }
  }
}
