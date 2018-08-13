/*
 * Copyright 2018 HM Revenue & Customs
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

class MatrixCsvSpec extends UnitSpec with WithFakeApplication with DecisionControllerFinalCsvSpec {

  override def bindModules = Seq(new PlayModule)

  val TEST_CASE_INSIDE_IR35_VERSION110_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/matrix/scenario-decision-insideIr35.csv"
  val TEST_CASE_INSIDE_IR35_VERSION110_FINAL_2 = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/matrix/scenario-decision-insideIr35_2.csv"
  val TEST_CASE_UNKNOWN_VERSION110_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION110_FINAL}/matrix/scenario-decision-unknown.csv"

  val TEST_CASE_INSIDE_IR35_VERSION111_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/matrix/scenario-decision-insideIr35.csv"
  val TEST_CASE_INSIDE_IR35_VERSION111_FINAL_2 = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/matrix/scenario-decision-insideIr35_2.csv"
  val TEST_CASE_UNKNOWN_VERSION111_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION111_FINAL}/matrix/scenario-decision-unknown.csv"

  val TEST_CASE_INSIDE_IR35_VERSION120_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/matrix/scenario-decision-insideIr35.csv"
  val TEST_CASE_INSIDE_IR35_VERSION120_FINAL_2 = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/matrix/scenario-decision-insideIr35_2.csv"
  val TEST_CASE_UNKNOWN_VERSION120_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION120_FINAL}/matrix/scenario-decision-unknown.csv"

  val TEST_CASE_INSIDE_IR35_VERSION130_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/matrix/scenario-decision-insideIr35.csv"
  val TEST_CASE_INSIDE_IR35_VERSION130_FINAL_2 = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/matrix/scenario-decision-insideIr35_2.csv"
  val TEST_CASE_UNKNOWN_VERSION130_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION130_FINAL}/matrix/scenario-decision-unknown.csv"

  val TEST_CASE_INSIDE_IR35_VERSION140_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/matrix/scenario-decision-insideIr35.csv"
  val TEST_CASE_INSIDE_IR35_VERSION140_FINAL_2 = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/matrix/scenario-decision-insideIr35_2.csv"
  val TEST_CASE_INSIDE_IR35_VERSION140_FINAL_EXIT_CLUSTER =
    s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/matrix/scenario-decision-insideIr35_exitFromExitCluster.csv"
  val TEST_CASE_UNKNOWN_VERSION140_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION140_FINAL}/matrix/scenario-decision-unknown.csv"

  val TEST_CASE_INSIDE_IR35_VERSION150_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/matrix/scenario-decision-insideIr35.csv"
  val TEST_CASE_INSIDE_IR35_VERSION150_FINAL_2 = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/matrix/scenario-decision-insideIr35_2.csv"
  val TEST_CASE_INSIDE_IR35_VERSION150_FINAL_EXIT_CLUSTER =
    s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/matrix/scenario-decision-insideIr35_exitFromExitCluster.csv"
  val TEST_CASE_UNKNOWN_VERSION150_FINAL = s"/test-scenarios/${DecisionServiceVersions.VERSION150_FINAL}/matrix/scenario-decision-unknown.csv"

  "POST /decide" should {

    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION110_FINAL, DecisionServiceVersions.VERSION110_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION110_FINAL} - 2" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION110_FINAL_2, DecisionServiceVersions.VERSION110_FINAL)
    }
    s"return 200 and correct response with the unknown decision for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_UNKNOWN_VERSION110_FINAL, DecisionServiceVersions.VERSION110_FINAL)
    }

    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION111_FINAL, DecisionServiceVersions.VERSION111_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION111_FINAL} - 2" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION111_FINAL_2, DecisionServiceVersions.VERSION111_FINAL)
    }
    s"return 200 and correct response with the unknown decision for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_UNKNOWN_VERSION111_FINAL, DecisionServiceVersions.VERSION111_FINAL)
    }

    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION120_FINAL, DecisionServiceVersions.VERSION120_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION120_FINAL} - 2" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION120_FINAL_2, DecisionServiceVersions.VERSION120_FINAL)
    }
    s"return 200 and correct response with the unknown decision for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_UNKNOWN_VERSION120_FINAL, DecisionServiceVersions.VERSION120_FINAL)
    }

    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION130_FINAL, DecisionServiceVersions.VERSION130_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION130_FINAL} - 2" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION130_FINAL_2, DecisionServiceVersions.VERSION130_FINAL)
    }
    s"return 200 and correct response with the unknown decision for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_UNKNOWN_VERSION130_FINAL, DecisionServiceVersions.VERSION130_FINAL)
    }

    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION140_FINAL, DecisionServiceVersions.VERSION140_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION140_FINAL} - 2" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION140_FINAL_2, DecisionServiceVersions.VERSION140_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION140_FINAL} - exit from Exit Cluster" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION140_FINAL_EXIT_CLUSTER, DecisionServiceVersions.VERSION140_FINAL)
    }
    s"return 200 and correct response with the unknown decision for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_UNKNOWN_VERSION140_FINAL, DecisionServiceVersions.VERSION140_FINAL)
    }

    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION150_FINAL, DecisionServiceVersions.VERSION150_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION150_FINAL} - 2" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION150_FINAL_2, DecisionServiceVersions.VERSION150_FINAL)
    }
    s"return 200 and correct response with the inside IR35 decision for version ${DecisionServiceVersions.VERSION150_FINAL} - exit from Exit Cluster" in {
      createRequestSendVerifyDecision(TEST_CASE_INSIDE_IR35_VERSION150_FINAL_EXIT_CLUSTER, DecisionServiceVersions.VERSION150_FINAL)
    }
    s"return 200 and correct response with the unknown decision for version ${DecisionServiceVersions.VERSION150_FINAL}" in {
      createRequestSendVerifyDecision(TEST_CASE_UNKNOWN_VERSION150_FINAL, DecisionServiceVersions.VERSION150_FINAL)
    }
  }
}
