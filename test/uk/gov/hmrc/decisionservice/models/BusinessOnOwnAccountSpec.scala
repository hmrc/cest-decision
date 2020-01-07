/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.play.test.UnitSpec

class BusinessOnOwnAccountSpec extends UnitSpec {

  val businessOnOwnAccountModel = BusinessOnOwnAccount(
    exclusiveContract = Some(ExclusiveContract.ableToProvideServices),
    transferRights = Some(TransferRights.ableToTransferRights),
    multipleEngagements = Some(MultipleEngagements.noKnowledgeOfExternalActivity),
    significantWorkingTime = Some(SignificantWorkingTime.consumesSignificantAmount),
    seriesOfContracts = Some(SeriesOfContracts.contractCouldBeExtended)
  )

  val businessOnOwnAccountJson = Json.obj(
    "exclusiveContract" -> ExclusiveContract.ableToProvideServices,
    "transferRights" -> TransferRights.ableToTransferRights,
    "multipleEngagements" -> MultipleEngagements.noKnowledgeOfExternalActivity,
    "significantWorkingTime" -> SignificantWorkingTime.consumesSignificantAmount,
    "seriesOfContracts" -> SeriesOfContracts.contractCouldBeExtended
  )

  "BusinessOnOwnAccount model" should {

    "return the correct model when read from Json" in {

      val expectedResult = businessOnOwnAccountModel
      val actualResult = businessOnOwnAccountJson.as[BusinessOnOwnAccount]

      actualResult shouldBe expectedResult
    }

    "return the correct Json when written to Json" in {

      val expectedResult = businessOnOwnAccountJson
      val actualResult = Json.toJson(businessOnOwnAccountModel)

      actualResult shouldBe expectedResult
    }
  }
}
