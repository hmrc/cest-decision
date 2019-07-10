package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class FinancialRisk(workerProvidedMaterials: Option[String], workerProvidedEquipment: Option[String],
                         workerUsedVehicle: Option[String], workerHadOtherExpenses: Option[String],
                         expensesAreNotRelevantForRole: Option[String],
                         workerMainIncome: Option[String], paidForSubstandardWork: Option[String]) extends Section

object FinancialRisk {
  implicit val format: Format[FinancialRisk] = Json.format[FinancialRisk]
}
