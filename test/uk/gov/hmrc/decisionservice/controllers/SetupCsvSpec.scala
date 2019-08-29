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
//class SetupCsvSpec extends UnitSpec with WithFakeApplication with DecisionControllerClusterCsvSpec {
//  val clusterName = "setup"
//  override def bindModules = Seq(new PlayModule)
//
//  val SETUP_SCENARIOS_VERSION140_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/${clusterName}/scenarios.csv"
//  val SETUP_SCENARIOS_VERSION140_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/${clusterName}/scenario_0.csv"
//  val SETUP_SCENARIOS_VERSION140_SCENARIO_NOT_VALID = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/${clusterName}/scenario_NotValid.csv"
//
//
//  val SETUP_SCENARIOS_VERSION150_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/${clusterName}/scenarios.csv"
//  val SETUP_SCENARIOS_VERSION150_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/${clusterName}/scenario_0.csv"
//  val SETUP_SCENARIOS_VERSION150_SCENARIO_NOT_VALID = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/${clusterName}/scenario_NotValid.csv"
//
//  "POST /decide" should {
//
//    s"return 200 and correct response setup scenario 0 for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createRequestSendVerifyDecision(SETUP_SCENARIOS_VERSION140_SCENARIO_0, DecisionServiceVersions.VERSION140_FINAL)
//    }
//    s"return 200 and correct response setup invalid scenario for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createRequestSendVerifyDecision(SETUP_SCENARIOS_VERSION140_SCENARIO_NOT_VALID, DecisionServiceVersions.VERSION140_FINAL)
//    }
//    s"return 200 and correct response setup scenarios for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(SETUP_SCENARIOS_VERSION140_FINAL, DecisionServiceVersions.VERSION140_FINAL)
//    }
//
//    s"return 200 and correct response setup scenario 0 for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createRequestSendVerifyDecision(SETUP_SCENARIOS_VERSION150_SCENARIO_0, DecisionServiceVersions.VERSION150_FINAL)
//    }
//    s"return 200 and correct response setup invalid scenario for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createRequestSendVerifyDecision(SETUP_SCENARIOS_VERSION150_SCENARIO_NOT_VALID, DecisionServiceVersions.VERSION150_FINAL)
//    }
//    s"return 200 and correct response setup scenarios for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(SETUP_SCENARIOS_VERSION150_FINAL, DecisionServiceVersions.VERSION150_FINAL)
//    }
//  }
//}
