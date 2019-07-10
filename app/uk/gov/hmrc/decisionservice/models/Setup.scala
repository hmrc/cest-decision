package uk.gov.hmrc.decisionservice.models

case class Setup(endUserRole: String, hasContractStarted: String, provideServices: String) extends Section

case class Exit(officeHolder: String) extends Section

case class PersonalService(workerSentActualSubstitute: Option[String], workerPayActualSubstitute: Option[String],
                           possibleSubstituteRejection: Option[String], possibleSubstituteWorkerPay: Option[String],
                           wouldWorkerPayHelper: Option[String]) extends Section

case class Control(engagerMovingWorker: Option[String], workerDecidingHowWorkIsDone: Option[String], workHasToBeDone: Option[String],
                   workerDecideWhere: Option[String]) extends Section

case class FinancialRisk(workerProvidedMaterials: Option[String], workerProvidedEquipment: Option[String],
                         workerUsedVehicle: Option[String], workerHadOtherExpenses: Option[String],
                         expensesAreNotRelevantForRole: Option[String],
                         workerMainIncome: Option[String], paidForSubstandardWork: Option[String]) extends Section

case class PartAndParcel(workerReceivesBenefits: Option[String], workerAsLineManager: Option[String],
                         contactWithEngagerCustomer: Option[String],  workerRepresentsEngagerBusiness: Option[String]) extends Section
