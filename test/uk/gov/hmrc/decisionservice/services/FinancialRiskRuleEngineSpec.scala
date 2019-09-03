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

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.FinancialRisk
import uk.gov.hmrc.decisionservice.models.enums.{PaidForSubstandardWork, WeightedAnswerEnum, WorkerMainIncome}
import uk.gov.hmrc.decisionservice.ruleEngines.FinancialRiskRuleEngine
import uk.gov.hmrc.decisionservice.ruleSets.FinancialRiskRules
import uk.gov.hmrc.play.test.UnitSpec

class FinancialRiskRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  lazy val financialRiskRules = app.injector.instanceOf[FinancialRiskRules]

  object TestFinancialRiskRuleEngine extends FinancialRiskRuleEngine(financialRiskRules)

  "FinancialRiskDecisionService" when {

    "decide is called with a ContFinancialRiskrol section with every value provided" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = WeightedAnswerEnum.OUTSIDE_IR35
        val actualAnswer = TestFinancialRiskRuleEngine.decide(Some(FinancialRisk(
          workerProvidedMaterials = Some(true),
          workerProvidedEquipment = Some(true),
          workerUsedVehicle = Some(true),
          workerHadOtherExpenses = Some(true),
          expensesAreNotRelevantForRole = Some(true),
          workerMainIncome = Some(WorkerMainIncome.incomeCalendarPeriods),
          paidForSubstandardWork = Some(PaidForSubstandardWork.asPartOfUsualRateInWorkingHours)
        )))

        await(actualAnswer) shouldBe Some(expectedAnswer)
      }
    }

    "decide is called with a FinancialRisk section with triggered rules" should {

      financialRiskRules.ruleSet.zipWithIndex.foreach { item =>

        val (ruleSet, index) = item

        s"return an answer for scenario ${index + 1}" in {

          val actualAnswer = TestFinancialRiskRuleEngine.decide(Some(ruleSet.rules.as[FinancialRisk]))
          val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

          await(actualAnswer) shouldBe Some(expectedAnswer)
        }
      }
    }

    "decide is called with a FinancialRisk section with None for every value" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = None
        val actualAnswer = TestFinancialRiskRuleEngine.decide(Some(FinancialRisk(None, None, None, None, None, None, None)))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }
}
