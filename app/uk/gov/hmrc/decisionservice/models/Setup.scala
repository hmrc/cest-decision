/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums.{EndUserRole, ProvideServices}

case class Setup(endUserRole: Option[EndUserRole.Value], hasContractStarted: Option[Boolean], provideServices: Option[ProvideServices.Value])

object Setup {
  implicit val format: Format[Setup] = Json.format[Setup]
}
