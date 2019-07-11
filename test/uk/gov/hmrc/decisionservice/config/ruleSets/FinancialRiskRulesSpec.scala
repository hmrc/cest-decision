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

package uk.gov.hmrc.decisionservice.config.ruleSets

import play.api.libs.json.{JsValue, Json}
import play.twirl.api.JavaScript
import uk.gov.hmrc.decisionservice.config.ruleSets.js.FinancialRiskRules
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

class FinancialRiskRulesSpec extends UnitSpec with TestFixture {

  val testControlRules: JavaScript = FinancialRiskRules()

  val json = Json.parse(testControlRules.body)

  "Contain all the expected OUT rules" in {

    val actual = (json \ WeightedAnswerEnum.OUTSIDE_IR35).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "workerProvidedMaterials": true
        |    },
        |    {
        |      "workerProvidedEquipment": true
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerHadOtherExpenses": true
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected MEDIUM rules" in {

    val actual = (json \ WeightedAnswerEnum.MEDIUM).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "outsideOfHoursNoCharge"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerUsedVehicle": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }

  "Contain all the expected LOW rules" in {

    val actual = (json \ WeightedAnswerEnum.LOW).as[List[JsValue]]

    val expected = Json.parse(
      """
        |[
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "workerHadOtherExpenses": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeProfitOrLosses",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeFixed",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "noObligationToCorrect"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "cannotBeCorrected"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCommission",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomePieceRate",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "asPartOfUsualRateInWorkingHours"
        |    },
        |    {
        |      "expensesAreNotRelevantForRole": true,
        |      "workerMainIncome": "incomeCalendarPeriods",
        |      "paidForSubstandardWork": "outsideOfHoursNoCosts"
        |    }
        |  ]
      """.stripMargin).as[List[JsValue]]

    actual shouldBe expected

  }



}
