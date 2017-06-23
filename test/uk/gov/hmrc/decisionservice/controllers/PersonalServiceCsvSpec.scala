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

package uk.gov.hmrc.decisionservice.controllers

import com.kenshoo.play.metrics.PlayModule
import uk.gov.hmrc.decisionservice.DecisionServiceVersions
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

class PersonalServiceCsvSpec extends UnitSpec with WithFakeApplication with DecisionControllerClusterCsvSpec {
  override def bindModules = Seq(new PlayModule)
  val clusterName = "personalService"

  val PERSONAL_SERVICE_SCENARIO_0_v110 = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/personal-service/scenario_0.csv"
  val PERSONAL_SERVICE_SCENARIOS_v110 = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/personal-service/scenarios.csv"

  val PERSONAL_SERVICE_SCENARIO_0_v111 = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/personal-service/scenario_0.csv"
  val PERSONAL_SERVICE_SCENARIOS_v111 = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/personal-service/scenarios.csv"

  val PERSONAL_SERVICE_SCENARIO_0_v120 = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/personal-service/scenario-0.csv"
  val PERSONAL_SERVICE_SCENARIO_1_v120 = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/personal-service/scenario-1.csv"
  val PERSONAL_SERVICE_SCENARIOS_v120 = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/personal-service/scenarios.csv"

  val PERSONAL_SERVICE_SCENARIO_0_v130 = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/personal-service/scenario-0.csv"
  val PERSONAL_SERVICE_SCENARIO_1_v130 = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/personal-service/scenario-1.csv"
  val PERSONAL_SERVICE_SCENARIOS_v130 = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/personal-service/scenarios.csv"

  val PERSONAL_SERVICE_SCENARIO_0_v140 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/personal-service/scenario-0.csv"
  val PERSONAL_SERVICE_SCENARIO_1_v140 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/personal-service/scenario-1.csv"
  val PERSONAL_SERVICE_SCENARIOS_v140 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/personal-service/scenarios.csv"

  val PERSONAL_SERVICE_SCENARIO_0_v150 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/personal-service/scenario-0.csv"
  val PERSONAL_SERVICE_SCENARIO_1_v150 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/personal-service/scenario-1.csv"
  val PERSONAL_SERVICE_SCENARIOS_v150 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/personal-service/scenarios.csv"

  "POST /decide" should {
    s"return 200 and correct response with the expected decision for personal service scenario 0 for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_0_v110, DecisionServiceVersions.VERSION110_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenarios for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(PERSONAL_SERVICE_SCENARIOS_v110, DecisionServiceVersions.VERSION110_FINAL)
    }

    s"return 200 and correct response with the expected decision for personal service scenario 0 for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_0_v111, DecisionServiceVersions.VERSION111_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenarios for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(PERSONAL_SERVICE_SCENARIOS_v111, DecisionServiceVersions.VERSION111_FINAL)
    }

    s"return 200 and correct response with the expected decision for personal service scenario 0 for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_0_v120, DecisionServiceVersions.VERSION120_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenario 1 for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_1_v120, DecisionServiceVersions.VERSION120_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenarios for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(PERSONAL_SERVICE_SCENARIOS_v120, DecisionServiceVersions.VERSION120_FINAL)
    }

    s"return 200 and correct response with the expected decision for personal service scenario 0 for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_0_v130, DecisionServiceVersions.VERSION130_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenario 1 for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_1_v130, DecisionServiceVersions.VERSION130_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenarios for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(PERSONAL_SERVICE_SCENARIOS_v130, DecisionServiceVersions.VERSION130_FINAL)
    }

    s"return 200 and correct response with the expected decision for personal service scenario 0 for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_0_v140, DecisionServiceVersions.VERSION140_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenario 1 for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_1_v140, DecisionServiceVersions.VERSION140_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenarios for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(PERSONAL_SERVICE_SCENARIOS_v140, DecisionServiceVersions.VERSION140_FINAL)
    }

    s"return 200 and correct response with the expected decision for personal service scenario 0 for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_0_v150, DecisionServiceVersions.VERSION150_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenario 1 for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
      createRequestSendVerifyDecision(PERSONAL_SERVICE_SCENARIO_1_v150, DecisionServiceVersions.VERSION150_FINAL)
    }
    s"return 200 and correct response with the expected decision for personal service scenarios for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(PERSONAL_SERVICE_SCENARIOS_v150, DecisionServiceVersions.VERSION150_FINAL)
    }
  }
}
