/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object TransferRights extends Enumeration with EnumFormats {

  val rightsTransferredToClient: TransferRights.Value = Value("rightsTransferredToClient")
  val ableToTransferRights: TransferRights.Value = Value("ableToTransferRights")
  val retainOwnershipRights: TransferRights.Value = Value("retainOwnershipRights")
  val noRightsArise: TransferRights.Value = Value("noRightsArise")

  implicit val format: Format[TransferRights.Value] = enumFormat(TransferRights)
}
