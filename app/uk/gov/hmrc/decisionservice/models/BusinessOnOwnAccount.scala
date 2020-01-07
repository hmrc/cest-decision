/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums._

case class BusinessOnOwnAccount(exclusiveContract: Option[ExclusiveContract.Value] = None,
                                transferRights: Option[TransferRights.Value] = None,
                                multipleEngagements: Option[MultipleEngagements.Value] = None,
                                significantWorkingTime: Option[SignificantWorkingTime.Value] = None,
                                seriesOfContracts: Option[SeriesOfContracts.Value] = None)

object BusinessOnOwnAccount {
  implicit val format: Format[BusinessOnOwnAccount] = Json.format[BusinessOnOwnAccount]

  val exclusiveContract = "exclusiveContract"
  val transferRights = "transferRights"
  val multipleEngagements = "multipleEngagements"
  val significantWorkingTime = "significantWorkingTime"
  val seriesOfContracts = "seriesOfContracts"
}


