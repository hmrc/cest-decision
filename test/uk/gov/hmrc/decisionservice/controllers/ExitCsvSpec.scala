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
//class ExitCsvSpec extends UnitSpec with WithFakeApplication with DecisionControllerClusterCsvSpec {
//  val clusterName = "exit"
//  override def bindModules = Seq(new PlayModule)
//
//  val EXIT_SCENARIOS_VERSION140_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/${clusterName}/scenarios.csv"
//  val EXIT_SCENARIOS_VERSION140_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/${clusterName}/scenario_0.csv"
//  val EXIT_SCENARIOS_VERSION140_SCENARIO_1 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/${clusterName}/scenario_1.csv"
//
//  val EXIT_SCENARIOS_VERSION150_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/${clusterName}/scenarios.csv"
//  val EXIT_SCENARIOS_VERSION150_SCENARIO_0 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/${clusterName}/scenario_0.csv"
//  val EXIT_SCENARIOS_VERSION150_SCENARIO_1 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/${clusterName}/scenario_1.csv"
//
//  "POST /decide" should {
//
//    s"return 200 and correct response exit scenario 0 for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createRequestSendVerifyDecision(EXIT_SCENARIOS_VERSION140_SCENARIO_0, DecisionServiceVersions.VERSION140_FINAL)
//    }
//
//    s"return 200 and correct response exit scenario 1 for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createRequestSendVerifyDecision(EXIT_SCENARIOS_VERSION140_SCENARIO_1, DecisionServiceVersions.VERSION140_FINAL)
//    }
//    s"return 200 and correct response exit scenarios for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(EXIT_SCENARIOS_VERSION140_FINAL, DecisionServiceVersions.VERSION140_FINAL)
//    }
//
//    s"return 200 and correct response exit scenario 0 for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createRequestSendVerifyDecision(EXIT_SCENARIOS_VERSION150_SCENARIO_0, DecisionServiceVersions.VERSION150_FINAL)
//    }
//
//    s"return 200 and correct response exit scenario 1 for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createRequestSendVerifyDecision(EXIT_SCENARIOS_VERSION150_SCENARIO_1, DecisionServiceVersions.VERSION150_FINAL)
//    }
//    s"return 200 and correct response exit scenarios for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
//      createMultipleRequestsSendVerifyDecision(EXIT_SCENARIOS_VERSION150_FINAL, DecisionServiceVersions.VERSION150_FINAL)
//    }
//  }
//}
