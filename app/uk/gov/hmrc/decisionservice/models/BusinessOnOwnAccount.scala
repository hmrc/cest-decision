/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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


