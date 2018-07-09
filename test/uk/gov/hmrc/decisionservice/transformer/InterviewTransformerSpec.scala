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

package uk.gov.hmrc.decisionservice.transformer

import org.joda.time.DateTime
import uk.gov.hmrc.decisionservice.model.analytics._
import uk.gov.hmrc.decisionservice.transformer.InterviewTransformer.toResponse
import uk.gov.hmrc.play.test.UnitSpec

/**
  * Created by work on 29/06/2017.
  */
class InterviewTransformerSpec extends UnitSpec {

  val compressedInterview = "df7826*hW@#$"
  val ir35 = "IR35"
  val out = "OUT"
  val yesClientAgreed = "yesClientAgreed"
  val personDoingWork = "personDoingWork"

  "Interview Transformer " should {

    "return true correct InterviewSearchResponse for a given Interview" in {

      val setup = Setup(personDoingWork,"No","partnership")
      val exit = Exit("No")
      val personalService = PersonalService(Option(yesClientAgreed),Option("No"),Option("wouldReject"),Option("No"),Option("No"))
      val control = Control(Option("canMoveWorkerWithPermission"), Option("workerAgreeWithOthers"), Option("scheduleDecidedForWorker"),
        Option("noScheduleRequiredOnlyDeadlines"))
      val financialRisk = FinancialRisk(Option("Yes"),Option("Yes"),Option.empty,Option.empty,Option.empty,Option.empty,Option.empty)

      val interview = Interview("1.5.0-final", compressedInterview, ir35, out, Option.empty, setup, exit, Option(personalService),
        Option(control), Option(financialRisk), Option.empty, new DateTime())

      val interviewSearchResponses = toResponse(List(interview))

      interviewSearchResponses.isEmpty shouldBe false
      interviewSearchResponses.size shouldBe 1

      val interviewSearchResponse = interviewSearchResponses.head

      interviewSearchResponse.compressedInterview shouldBe compressedInterview
      interviewSearchResponse.route shouldBe ir35
      interviewSearchResponse.decision shouldBe out
      interviewSearchResponse.setupEndUserRole shouldBe personDoingWork
      interviewSearchResponse.exitOfficeHolder shouldBe "No"
      interviewSearchResponse.personalServiceWorkerSentActualSubstitute shouldBe yesClientAgreed
      interviewSearchResponse.financialRiskHaveToPayButCannotClaim
        .contains("financialRisk.workerProvidedMaterials") shouldBe true
      interviewSearchResponse.financialRiskHaveToPayButCannotClaim
        .contains("financialRisk.workerProvidedEquipment") shouldBe true
    }

    "return true correct InterviewSearchResponse for a minimal Interview" in {

      val setup = Setup(personDoingWork,"No","partnership")
      val exit = Exit("Yes")

      val interview = Interview("1.5.0-final", compressedInterview, ir35, out, Option.empty, setup, exit, Option.empty,
        Option.empty, Option.empty, Option.empty, new DateTime())

      val interviewSearchResponses = toResponse(List(interview))

      interviewSearchResponses.isEmpty shouldBe false
      interviewSearchResponses.size shouldBe 1

      val interviewSearchResponse = interviewSearchResponses.head

      interviewSearchResponse.compressedInterview shouldBe compressedInterview
      interviewSearchResponse.route shouldBe ir35
      interviewSearchResponse.decision shouldBe out
      interviewSearchResponse.setupEndUserRole shouldBe personDoingWork
      interviewSearchResponse.exitOfficeHolder shouldBe "Yes"
      interviewSearchResponse.financialRiskHaveToPayButCannotClaim shouldBe "|"
    }
  }


}
