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

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class Setup(endUserRole: String, hasContractStarted: String, provideServices: String) extends Section

object Setup {
  implicit val format: Format[Setup] = Json.format[Setup]
}

case class Exit(officeHolder: String) extends Section

object Exit {
  implicit val format: Format[Exit] = Json.format[Exit]
}

case class PersonalService(workerSentActualSubstitute: Option[String], workerPayActualSubstitute: Option[String],
                           possibleSubstituteRejection: Option[String], possibleSubstituteWorkerPay: Option[String],
                           wouldWorkerPayHelper: Option[String]) extends Section

object PersonalService {
  implicit val format: Format[PersonalService] = Json.format[PersonalService]
}

case class Control(engagerMovingWorker: Option[String], workerDecidingHowWorkIsDone: Option[String], workHasToBeDone: Option[String],
                   workerDecideWhere: Option[String]) extends Section

object Control {
  implicit val format: Format[Control] = Json.format[Control]
}

case class FinancialRisk(workerProvidedMaterials: Option[String], workerProvidedEquipment: Option[String],
                         workerUsedVehicle: Option[String], workerHadOtherExpenses: Option[String],
                         expensesAreNotRelevantForRole: Option[String],
                         workerMainIncome: Option[String], paidForSubstandardWork: Option[String]) extends Section

object FinancialRisk {
  implicit val format: Format[FinancialRisk] = Json.format[FinancialRisk]
}

case class PartAndParcel(workerReceivesBenefits: Option[String], workerAsLineManager: Option[String],
                         contactWithEngagerCustomer: Option[String],  workerRepresentsEngagerBusiness: Option[String]) extends Section

object PartAndParcel {
  implicit val format: Format[PartAndParcel] = Json.format[PartAndParcel]
}