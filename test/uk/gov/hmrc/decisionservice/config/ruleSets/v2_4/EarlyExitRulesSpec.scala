/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.config.ruleSets.v2_4

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.BaseRuleSpec
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.enums.ExitEnum
import uk.gov.hmrc.decisionservice.ruleSets.EarlyExitRules_v24

class EarlyExitRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite {

  implicit val ruleSet = EarlyExitRules_v24.ruleSet

  "For the IN rules" should {

    val actual = getRules(ExitEnum.INSIDE_IR35)

    val expected = List(
      Json.obj(
        Exit.officeHolder -> true
      )
    )

    checkRules(expected, actual)
  }
}
