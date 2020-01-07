/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.Control
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.decisionservice.ruleSets.ControlRules
import uk.gov.hmrc.play.test.UnitSpec

class ControlRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  object TestControlRuleEngine extends ControlRuleEngine

  "ControlDecisionServiceSpec" when {

    "decide is called with a Control section with triggered rules" should {

      DecisionServiceVersion.values.foreach { version =>

        s"for rule engine version $version" should {

          s"return the correct expected result" in {

            ControlRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestControlRuleEngine.decide(Some(ruleSet.rules.as[Control]))(version)
              val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

              await(actualAnswer) shouldBe Some(expectedAnswer)
            }
          }
        }
      }
    }

    "decide is called with a Control section with None for every value" should {

      "return a WeightedAnswer" in {

        val actualAnswer = TestControlRuleEngine.decide(Some(Control(None, None, None, None)))(DecisionServiceVersion.v1_5_0)

        await(actualAnswer) shouldBe None

      }
    }
  }
}
