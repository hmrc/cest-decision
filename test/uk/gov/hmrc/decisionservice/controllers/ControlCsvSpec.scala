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

///*
// * Copyright 2019 HM Revenue & Customs
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package uk.gov.hmrc.decisionservice.controllers
//
//import com.kenshoo.play.metrics.PlayModule
//import uk.gov.hmrc.decisionservice.DecisionServiceVersions
//import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}
//
//class ControlCsvSpec extends UnitSpec with WithFakeApplication with DecisionControllerClusterCsvSpec {
//  val clusterName = "control"
//  override def bindModules = Seq(new PlayModule)
//
//  val CONTROL_SCENARIOS_VERSION110_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/control/scenarios.csv"
//  val CONTROL_SCENARIOS_VERSION110_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/control/scenario_0.csv"
//
//  val CONTROL_SCENARIOS_VERSION111_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/control/scenarios.csv"
//  val CONTROL_SCENARIOS_VERSION111_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/control/scenario_0.csv"
//
//  val CONTROL_SCENARIOS_VERSION120_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/control/scenarios.csv"
//  val CONTROL_SCENARIOS_VERSION120_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/control/scenario_0.csv"
//
//  val CONTROL_SCENARIOS_VERSION130_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/control/scenarios.csv"
//  val CONTROL_SCENARIOS_VERSION130_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/control/scenario_0.csv"
//
//  val CONTROL_SCENARIOS_VERSION140_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/control/scenarios.csv"
//  val CONTROL_SCENARIOS_VERSION140_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/control/scenario_0.csv"
//
//  val CONTROL_SCENARIOS_VERSION150_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/control/scenarios.csv"
//  val CONTROL_SCENARIOS_VERSION150_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/control/scenario_0.csv"
//
//  "POST /decide" should {
//
//    s"return 200 and correct response control scenario 0 for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
//      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION110_SCENARIO_0, DecisionServiceVersions.VERSION110_FINAL)
//    }
//    s"return 200 and correct response control scenarios for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION110_FINAL, DecisionServiceVersions.VERSION110_FINAL)
//    }
//
//    s"return 200 and correct response control scenario 0 for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
//      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION111_SCENARIO_0, DecisionServiceVersions.VERSION111_FINAL)
//    }
//    s"return 200 and correct response control scenarios for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION111_FINAL, DecisionServiceVersions.VERSION111_FINAL)
//    }
//
//    s"return 200 and correct response control scenario 0 for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
//      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION120_SCENARIO_0, DecisionServiceVersions.VERSION120_FINAL)
//    }
//    s"return 200 and correct response control scenarios for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION120_FINAL, DecisionServiceVersions.VERSION120_FINAL)
//    }
//
//    s"return 200 and correct response control scenario 0 for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
//      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION130_SCENARIO_0, DecisionServiceVersions.VERSION130_FINAL)
//    }
//    s"return 200 and correct response control scenarios for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION130_FINAL, DecisionServiceVersions.VERSION130_FINAL)
//    }
//
//    s"return 200 and correct response control scenario 0 for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION140_SCENARIO_0, DecisionServiceVersions.VERSION140_FINAL)
//    }
//    s"return 200 and correct response control scenarios for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION140_FINAL, DecisionServiceVersions.VERSION140_FINAL)
//    }
//
//    s"return 200 and correct response control scenario 0 for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createRequestSendVerifyDecision(CONTROL_SCENARIOS_VERSION150_SCENARIO_0, DecisionServiceVersions.VERSION150_FINAL)
//    }
//    s"return 200 and correct response control scenarios for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(CONTROL_SCENARIOS_VERSION150_FINAL, DecisionServiceVersions.VERSION150_FINAL)
//    }
//  }
//}
