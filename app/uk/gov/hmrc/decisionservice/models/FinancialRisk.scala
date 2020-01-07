/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums.{PaidForSubstandardWork, WorkerMainIncome}

case class FinancialRisk(workerProvidedMaterials: Option[Boolean],
                         workerProvidedEquipment: Option[Boolean],
                         workerUsedVehicle: Option[Boolean],
                         workerHadOtherExpenses: Option[Boolean],
                         expensesAreNotRelevantForRole: Option[Boolean],
                         workerMainIncome: Option[WorkerMainIncome.Value],
                         paidForSubstandardWork: Option[PaidForSubstandardWork.Value])

object FinancialRisk {
  implicit val format: Format[FinancialRisk] = Json.format[FinancialRisk]

  val workerProvidedMaterials = "workerProvidedMaterials"
  val workerProvidedEquipment = "workerProvidedEquipment"
  val workerUsedVehicle = "workerUsedVehicle"
  val workerHadOtherExpenses = "workerHadOtherExpenses"
  val expensesAreNotRelevantForRole = "expensesAreNotRelevantForRole"
  val workerMainIncome = "workerMainIncome"
  val paidForSubstandardWork = "paidForSubstandardWork"
}
