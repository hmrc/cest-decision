package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ContractPreventServices extends Enumeration with EnumFormats {

  val unableToProvideServices: ContractPreventServices.Value = Value("unableToProvideServices")
  val ableToProvideServicesWithPermision: ContractPreventServices.Value = Value("ableToProvideServicesWithPermision")
  val ableToProvideServices: ContractPreventServices.Value = Value("ableToProvideServices")

  implicit val format: Format[ContractPreventServices.Value] = enumFormat(ContractPreventServices)
}