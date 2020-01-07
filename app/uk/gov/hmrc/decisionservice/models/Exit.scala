/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}

case class Exit(officeHolder: Option[Boolean])

object Exit {
  implicit val format: Format[Exit] = Json.format[Exit]

  val officeHolder = "officeHolder"
}
