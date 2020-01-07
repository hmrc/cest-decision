/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object MoveWorker extends Enumeration with EnumFormats {

  val canMoveWorkerWithPermission: MoveWorker.Value = Value("canMoveWorkerWithPermission")
  val canMoveWorkerWithoutPermission: MoveWorker.Value = Value("canMoveWorkerWithoutPermission")
  val cannotMoveWorkerWithoutNewAgreement: MoveWorker.Value = Value("cannotMoveWorkerWithoutNewAgreement")

  implicit val format: Format[MoveWorker.Value] = enumFormat(MoveWorker)
}