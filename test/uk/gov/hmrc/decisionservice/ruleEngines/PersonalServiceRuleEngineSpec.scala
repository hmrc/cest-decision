/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.decisionservice.models.PersonalService
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PersonalServiceRules
import uk.gov.hmrc.play.test.UnitSpec

class PersonalServiceRuleEngineSpec extends UnitSpec with GuiceOneAppPerSuite {

  object TestControlDecisionServiceRuleEngine extends PersonalServiceRuleEngine

  "PersonalServiceDecisionServiceSpec" when {


    "decide is called with a PersonalService section with out scenarios populated" should {

        DecisionServiceVersion.values.foreach { version =>

          s"for rule engine version $version" should {

              s"return the correct expected response" in {

                PersonalServiceRules(version).ruleSet.foreach { ruleSet =>

                val actualAnswer = TestControlDecisionServiceRuleEngine.decide(Some(ruleSet.rules.as[PersonalService]))(version)
                val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

                await(actualAnswer) shouldBe Some(expectedAnswer)

              }
            }
          }
      }
    }

    "decide is called with a PersonalService section with None for every value" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = None
        val actualAnswer = TestControlDecisionServiceRuleEngine.decide(Some(PersonalService(None, None, None, None, None)))(DecisionServiceVersion.v1_5_0)

        await(actualAnswer) shouldBe expectedAnswer
      }
    }
  }
}
