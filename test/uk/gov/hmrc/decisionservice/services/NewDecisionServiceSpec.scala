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

import org.mockito.ArgumentMatchers
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.models.enums.{ExitEnum, ResultEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleEngines._
import uk.gov.hmrc.play.test.UnitSpec

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class NewDecisionServiceSpec extends UnitSpec {

  private trait Setup extends MockitoSugar {
    val exit = mock[ExitRuleEngine]
    val control = mock[ControlRuleEngine]
    val personalService = mock[PersonalServiceRuleEngine]
    val financialRisk = mock[FinancialRiskRuleEngine]
    val partAndParcel = mock[PartAndParcelRuleEngine]
    val result = mock[ResultRuleEngine]

    val target = new NewDecisionService(control,exit,financialRisk,personalService,partAndParcel,result)
  }

  "DecisionService" when {

    "provided a decision request" should {

      "calculate the result" in new Setup {

        val request = DecisionRequest(
          "1.0.0-beta", "coral", Interview(
            setup = Some(Setup(None, None, None)),
            exit = Some(Exit(None)),
            personalService = Some(PersonalService(None, None, None, None, None)),
            control = Some(Control(None, None, None, None)),
            financialRisk = Some(FinancialRisk(None, None, None, None, None, None, None)),
            partAndParcel = Some(PartAndParcel(None, None, None, None)),
            businessOnOwnAccount = Some(BusinessOnOwnAccount(None, None, None, None, None))

          )
        )

        when(exit.decide(ArgumentMatchers.any())).thenReturn(Future.successful(Some(ExitEnum.CONTINUE)))
        when(control.decide(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(personalService.decide(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(financialRisk.decide(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(partAndParcel.decide(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(result.decide(ArgumentMatchers.any())).thenReturn(Future.successful(ResultEnum.INSIDE_IR35))

        await(target.calculateResult(request)) shouldBe DecisionResponse(
          "1.0.0-beta", "coral", Score(
            None,Some(ExitEnum.CONTINUE),Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH)
          ), ResultEnum.INSIDE_IR35
        )
      }
    }
  }
}
