package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object MultipleEngagements extends Enumeration with EnumFormats {

  val providedServicesToOtherEngagers: MultipleEngagements.Value = Value("providedServicesToOtherEngagers")
  val onlyContractForPeriod: MultipleEngagements.Value = Value("onlyContractForPeriod")
  val noKnowledgeOfExternalActivity: MultipleEngagements.Value = Value("noKnowledgeOfExternalActivity")

  implicit val format: Format[MultipleEngagements.Value] = enumFormat(MultipleEngagements)
}