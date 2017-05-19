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
import uk.gov.hmrc.decisionservice.Versions
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

class ControlCsvSpec extends UnitSpec with WithFakeApplication with DecisionControllerClusterCsvSpec {
  val clusterName = "control"
  override def bindModules = Seq(new PlayModule)

  val CONTROL_SCENARIOS_VERSION110_FINAL = s"/test-scenarios/${Versions.VERSION110_FINAL}/control/scenarios.csv"
  val CONTROL_SCENARIOS_VERSION110_SCENARIO_0 = s"/test-scenarios/${Versions.VERSION110_FINAL}/control/scenario_0.csv"

  val CONTROL_SCENARIOS_VERSION111_FINAL = s"/test-scenarios/${Versions.VERSION111_FINAL}/control/scenarios.csv"
  val CONTROL_SCENARIOS_VERSION111_SCENARIO_0 = s"/test-scenarios/${Versions.VERSION111_FINAL}/control/scenario_0.csv"

  val CONTROL_SCENARIOS_VERSION120_FINAL = s"/test-scenarios/${Versions.VERSION120_FINAL}/control/scenarios.csv"
  val CONTROL_SCENARIOS_VERSION120_SCENARIO_0 = s"/test-scenarios/${Versions.VERSION120_FINAL}/control/scenario_0.csv"

  val CONTROL_SCENARIOS_VERSION130_FINAL = s"/test-scenarios/${Versions.VERSION130_FINAL}/control/scenarios.csv"
  val CONTROL_SCENARIOS_VERSION130_SCENARIO_0 = s"/test-scenarios/${Versions.VERSION130_FINAL}/control/scenario_0.csv"

  val CONTROL_SCENARIOS_VERSION140_FINAL = s"/test-scenarios/${Versions.VERSION140_FINAL}/control/scenarios.csv"
  val CONTROL_SCENARIOS_VERSION140_SCENARIO_0 = s"/test-scenarios/${Versions.VERSION140_FINAL}/control/scenario_0.csv"

  "POST /decide" should {

    s"return 200 and correct response control scenario 0 for version ${Versions.VERSION110_FINAL}" in {
      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION110_SCENARIO_0, Versions.VERSION110_FINAL)
    }
    s"return 200 and correct response control scenarios for version ${Versions.VERSION110_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION110_FINAL, Versions.VERSION110_FINAL)
    }

    s"return 200 and correct response control scenario 0 for version ${Versions.VERSION111_FINAL}" in {
      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION111_SCENARIO_0, Versions.VERSION111_FINAL)
    }
    s"return 200 and correct response control scenarios for version ${Versions.VERSION111_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION111_FINAL, Versions.VERSION111_FINAL)
    }

    s"return 200 and correct response control scenario 0 for version ${Versions.VERSION120_FINAL}" in {
      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION120_SCENARIO_0, Versions.VERSION120_FINAL)
    }
    s"return 200 and correct response control scenarios for version ${Versions.VERSION120_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION120_FINAL, Versions.VERSION120_FINAL)
    }

    s"return 200 and correct response control scenario 0 for version ${Versions.VERSION130_FINAL}" in {
      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION130_SCENARIO_0, Versions.VERSION130_FINAL)
    }
    s"return 200 and correct response control scenarios for version ${Versions.VERSION130_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION130_FINAL, Versions.VERSION130_FINAL)
    }

    s"return 200 and correct response control scenario 0 for version ${Versions.VERSION140_FINAL}" in {
      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION140_SCENARIO_0, Versions.VERSION140_FINAL)
    }
    s"return 200 and correct response control scenarios for version ${Versions.VERSION140_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION140_FINAL, Versions.VERSION140_FINAL)
    }
  }
}
