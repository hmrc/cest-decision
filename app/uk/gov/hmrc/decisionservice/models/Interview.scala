package uk.gov.hmrc.decisionservice.models

import uk.gov.hmrc.decisionservice.model.analytics._

case class Interview(setup: Setup, exit: Exit, personalService: PersonalService, control: Control, financialRisk: FinancialRisk, partAndParcel: PartAndParcel)
