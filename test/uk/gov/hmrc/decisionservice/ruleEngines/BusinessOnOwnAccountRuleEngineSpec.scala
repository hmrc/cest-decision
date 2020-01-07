/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import uk.gov.hmrc.decisionservice.models.BusinessOnOwnAccount
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.BusinessOnOwnAccountRules
import uk.gov.hmrc.play.test.UnitSpec

class BusinessOnOwnAccountRuleEngineSpec extends UnitSpec {

  object TestBusinessOnOwnAccountRuleEngine extends BusinessOnOwnAccountRuleEngine

  "BusinessOnOwnAccountDecisionService" when {

    "decide is called with a BoOA section that triggers rules" should {

      DecisionServiceVersion.values.filterNot(_ == DecisionServiceVersion.v1_5_0).foreach { version =>

        s"for rule engine version $version" should {

          s"return the correct expected result" in {

            BusinessOnOwnAccountRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestBusinessOnOwnAccountRuleEngine.decide(Some(ruleSet.rules.as[BusinessOnOwnAccount]))(version)
              val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

              await(actualAnswer) shouldBe Some(expectedAnswer)
            }
          }
        }
      }
    }

    "decide is called with no input values supplied" should {

      "return a default score of medium" in {

        val actualResult = TestBusinessOnOwnAccountRuleEngine.decide(Some(BusinessOnOwnAccount(None, None, None, None, None)))(DecisionServiceVersion.v2_4)
        val expectedResult = Some(WeightedAnswerEnum.MEDIUM)
        
        await(actualResult) shouldBe expectedResult
      }
    }
  }
}
