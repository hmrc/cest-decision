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

import play.api.libs.json.{JsArray, JsValue, Json}
import uk.gov.hmrc.decisionservice.models.FinancialRisk._
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum._
import uk.gov.hmrc.decisionservice.models.enums.HowWorkerIsPaid._
import uk.gov.hmrc.decisionservice.models.enums.PutRightAtOwnCost._

object FinancialRiskRules extends BaseRules {

  val out: JsArray = Json.arr(
    Json.obj(
      workerProvidedMaterials -> true
    ),
    Json.obj(
      workerProvidedEquipment -> true
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerHadOtherExpenses -> true
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ))

  val medium: JsArray = Json.arr(
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> outsideOfHoursNoCharge
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerUsedVehicle -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ))

  val low: JsArray = Json.arr(
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      workerHadOtherExpenses -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> profitOrLosses,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> fixedPrice,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> noObligationToCorrect
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> cannotBeCorrected
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> commission,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> pieceRate,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
    ),
    Json.obj(
      expensesAreNotRelevantForRole -> true,
      workerMainIncome -> hourlyDailyOrWeekly,
      paidForSubstandardWork -> outsideOfHoursNoCosts
    )
  )

  override val ruleSet: JsValue =
    Json.obj(
      OUTSIDE_IR35.toString -> out,
      MEDIUM.toString -> medium,
      LOW.toString -> low
    )

}
