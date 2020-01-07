/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.config.ruleSets.v1_5_0

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.BaseRuleSpec
import uk.gov.hmrc.decisionservice.models.PartAndParcel._
import uk.gov.hmrc.decisionservice.models.enums.{IdentifyToStakeholders, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PartAndParcelRules_v150

class PartParcelRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite {

  implicit val ruleSet = PartAndParcelRules_v150.ruleSet

  "For all the expected HIGH rules" should {

    val actual = getRules(WeightedAnswerEnum.HIGH)

    val expected = List(
      Json.obj(
        workerReceivesBenefits -> false,
        workerAsLineManager -> true
      ),
      Json.obj(
        workerReceivesBenefits -> true
      )
    )

    checkRules(expected, actual)
  }

  "For all the expected MEDIUM rules" should {

    val actual = getRules(WeightedAnswerEnum.MEDIUM)

    val expected = List(
      Json.obj(
        contactWithEngagerCustomer -> true,
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
      ),
      Json.obj(
        contactWithEngagerCustomer -> true,
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
      )
    )

    checkRules(expected, actual)
  }

  "for the expected LOW rules" should {

    val expected = List(
      Json.obj(
        contactWithEngagerCustomer -> false
      ),
      Json.obj(
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
      )
    )

    val actual = ruleSet.filter(_.result == WeightedAnswerEnum.LOW.toString).map(_.rules).toList

    checkRules(expected, actual)
  }
}
