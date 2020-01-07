/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.FinancialRisk
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.FinancialRiskRules
import uk.gov.hmrc.play.test.UnitSpec

class FinancialRiskRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  object TestFinancialRiskRuleEngine extends FinancialRiskRuleEngine

  "FinancialRiskDecisionService" when {

    "decide is called with a FinancialRisk section with triggered rules" should {

      DecisionServiceVersion.values.foreach { version =>

        s"for rule engine version $version" should {

          s"return the correct expected result" in {

            FinancialRiskRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestFinancialRiskRuleEngine.decide(Some(ruleSet.rules.as[FinancialRisk]))(version)
              val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

              await(actualAnswer) shouldBe Some(expectedAnswer)
            }
          }
        }
      }
    }

    "decide is called with a FinancialRisk section with None for every value" should {

      "return a WeightedAnswer" in {

        val actualAnswer = TestFinancialRiskRuleEngine.decide(Some(FinancialRisk(None, None, None, None, None, None, None)))(DecisionServiceVersion.v1_5_0)

        await(actualAnswer) shouldBe None

      }
    }
  }
}
