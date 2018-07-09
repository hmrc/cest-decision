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

package uk.gov.hmrc.decisionservice.testutilspec

import uk.gov.hmrc.decisionservice.DecisionServiceVersions
import uk.gov.hmrc.decisionservice.testutil.RequestAndDecision
import uk.gov.hmrc.play.test.UnitSpec


class RequestAndDecisionSpec extends UnitSpec {
  val FLATTENED_TEST_CASES = "/test-scenarios/test-scenario-reader/request-and-decision.csv"
  val FLATTENED_TEST_CASES_TRANSPOSED = "/test-scenarios/test-scenario-reader/request-and-decision-transposed.csv"

  "test scenario reader" should {
    "read valid flattened test case file" in {
      val testCasesTry = RequestAndDecision.readFlattened(FLATTENED_TEST_CASES, DecisionServiceVersions.VERSION110_FINAL)
      testCasesTry.isSuccess shouldBe true
      val testCases = testCasesTry.get
      testCases should have size 5
      testCases.foreach{
        _.request.interview.size shouldBe 3
      }
    }
    "read valid flattened transposed test case file" in {
      val testCasesTry = RequestAndDecision.readFlattenedTransposed(FLATTENED_TEST_CASES_TRANSPOSED, DecisionServiceVersions.VERSION110_FINAL)
      testCasesTry.isSuccess shouldBe true
      val testCase = testCasesTry.get
      testCase.request.interview should have size 3
      testCase.expectedDecision shouldBe "expected_decision"
    }
  }

}
