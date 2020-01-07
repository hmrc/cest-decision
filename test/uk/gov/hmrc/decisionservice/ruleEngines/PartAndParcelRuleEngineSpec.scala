/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.PartAndParcel
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PartAndParcelRules
import uk.gov.hmrc.play.test.UnitSpec

class PartAndParcelRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  object TestPartAndParcelRuleEngine extends PartAndParcelRuleEngine

  "PartAndParcelDecisionServiceSpec" when {

    "decide is called with a PartAndParcel section with triggered rules populated" should {

      DecisionServiceVersion.values.foreach { version =>

        s"for rule engine version $version" should {

            s"return the correct expected result" in {

              PartAndParcelRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestPartAndParcelRuleEngine.decide(Some(ruleSet.rules.as[PartAndParcel]))(version)
              val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

              await(actualAnswer) shouldBe Some(expectedAnswer)

            }
          }
        }
      }
    }

    "decide is called with a PartAndParcel section with None for every value" should {

      "return a WeightedAnswer" in {

        val actualAnswer = TestPartAndParcelRuleEngine.decide(Some(PartAndParcel(None, None, None, None)))(DecisionServiceVersion.v1_5_0)

        await(actualAnswer) shouldBe None
      }
    }
  }
}
