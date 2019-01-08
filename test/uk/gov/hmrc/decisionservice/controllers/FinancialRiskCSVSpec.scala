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

package uk.gov.hmrc.decisionservice.controllers

import com.kenshoo.play.metrics.PlayModule
import uk.gov.hmrc.decisionservice.DecisionServiceVersions
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

/**
  * Created by work on 09/01/2017.
  */
class FinancialRiskCSVSpec extends UnitSpec with WithFakeApplication with DecisionControllerClusterCsvSpec {
  override def bindModules = Seq(new PlayModule)
  val clusterName = "financialRisk"

  val FINANCIAL_RISK_SCENARIO_0_v110 = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/financial-risk/scenario_0.csv"
  val FINANCIAL_RISK_SCENARIOS_v110 = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/financial-risk/scenarios.csv"

  val FINANCIAL_RISK_SCENARIO_0_v111 = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/financial-risk/scenario_0.csv"
  val FINANCIAL_RISK_SCENARIOS_v111 = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/financial-risk/scenarios.csv"

  val FINANCIAL_RISK_SCENARIO_0_v120 = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/financial-risk/scenario_0.csv"
  val FINANCIAL_RISK_SCENARIOS_v120 = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/financial-risk/scenarios.csv"

  val FINANCIAL_RISK_SCENARIO_0_v130 = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/financial-risk/scenario_0.csv"
  val FINANCIAL_RISK_SCENARIOS_v130 = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/financial-risk/scenarios.csv"

  val FINANCIAL_RISK_SCENARIO_0_v140 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/financial-risk/scenario_0.csv"
  val FINANCIAL_RISK_SCENARIOS_v140 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/financial-risk/scenarios.csv"

  val FINANCIAL_RISK_SCENARIO_0_v150 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/financial-risk/scenario_0.csv"
  val FINANCIAL_RISK_SCENARIOS_v150 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/financial-risk/scenarios.csv"

  "POST /decide" should {
    s"return 200 and expected decision for financial risk scenario 0 for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      createRequestSendVerifyDecision(FINANCIAL_RISK_SCENARIO_0_v110, DecisionServiceVersions.VERSION110_FINAL)
    }
    s"return 200 and expected decision for financial risk scenarios for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(FINANCIAL_RISK_SCENARIOS_v110, DecisionServiceVersions.VERSION110_FINAL)
    }

    s"return 200 and expected decision for financial risk scenario 0 for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      createRequestSendVerifyDecision(FINANCIAL_RISK_SCENARIO_0_v111, DecisionServiceVersions.VERSION111_FINAL)
    }
    s"return 200 and expected decision for financial risk scenarios for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(FINANCIAL_RISK_SCENARIOS_v111, DecisionServiceVersions.VERSION111_FINAL)
    }

    s"return 200 and expected decision for financial risk scenario 0 for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      createRequestSendVerifyDecision(FINANCIAL_RISK_SCENARIO_0_v120, DecisionServiceVersions.VERSION120_FINAL)
    }
    s"return 200 and expected decision for financial risk scenarios for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(FINANCIAL_RISK_SCENARIOS_v120, DecisionServiceVersions.VERSION120_FINAL)
    }

    s"return 200 and expected decision for financial risk scenario 0 for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      createRequestSendVerifyDecision(FINANCIAL_RISK_SCENARIO_0_v130, DecisionServiceVersions.VERSION130_FINAL)
    }
    s"return 200 and expected decision for financial risk scenarios for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(FINANCIAL_RISK_SCENARIOS_v130, DecisionServiceVersions.VERSION130_FINAL)
    }

    s"return 200 and expected decision for financial risk scenario 0 for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      createRequestSendVerifyDecision(FINANCIAL_RISK_SCENARIO_0_v140, DecisionServiceVersions.VERSION140_FINAL)
    }
    s"return 200 and expected decision for financial risk scenarios for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(FINANCIAL_RISK_SCENARIOS_v140, DecisionServiceVersions.VERSION140_FINAL)
    }

    s"return 200 and expected decision for financial risk scenario 0 for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
      createRequestSendVerifyDecision(FINANCIAL_RISK_SCENARIO_0_v150, DecisionServiceVersions.VERSION150_FINAL)
    }
    s"return 200 and expected decision for financial risk scenarios for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(FINANCIAL_RISK_SCENARIOS_v150, DecisionServiceVersions.VERSION150_FINAL)
    }
  }
}
