/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class Interview(setup: Option[Setup] = None,
                     exit: Option[Exit] = None,
                     personalService: Option[PersonalService] = None,
                     control: Option[Control] = None,
                     financialRisk: Option[FinancialRisk] = None,
                     partAndParcel: Option[PartAndParcel] = None,
                     businessOnOwnAccount: Option[BusinessOnOwnAccount] = None)

object Interview {
  implicit val format: Format[Interview] = Json.format[Interview]
}