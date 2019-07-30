package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ContractProprietyRights extends Enumeration with EnumFormats {

  val rightsTransferredToClient: ContractProprietyRights.Value = Value("rightsTransferredToClient")
  val ableToTransferRights: ContractProprietyRights.Value = Value("ableToTransferRights")
  val retainOwnershipRights: ContractProprietyRights.Value = Value("retainOwnershipRights")
  val noRightsArise: ContractProprietyRights.Value = Value("noRightsArise")

  implicit val format: Format[ContractProprietyRights.Value] = enumFormat(ContractProprietyRights)
}
