/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, ExitEnum}
import uk.gov.hmrc.decisionservice.ruleSets.EarlyExitRules
import uk.gov.hmrc.play.test.UnitSpec

class ExitRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  object TestExitRuleEngine extends ExitRuleEngine

  "ExitDecisionService" when {

    "decide is called with a Exit section with triggered rules" should {

      DecisionServiceVersion.values.foreach { version =>

        s"for rule engine version $version" should {

          s"return the correct expected result" in {

            EarlyExitRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestExitRuleEngine.decide(Some(ruleSet.rules.as[Exit]))(version)
              val expectedAnswer = ExitEnum.withName(ruleSet.result)

              await(actualAnswer) shouldBe Some(expectedAnswer)
            }
          }
        }
      }
    }

    "decide is called with a Exit section with None for every value" should {

      "return a WeightedAnswer" in {

        val actualAnswer = TestExitRuleEngine.decide(Some(Exit(None)))(DecisionServiceVersion.v1_5_0)

        await(actualAnswer) shouldBe None

      }
    }
  }
}
