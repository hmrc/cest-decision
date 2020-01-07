/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.config.ruleSets

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.JsObject
import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.util.TestFixture
import uk.gov.hmrc.play.test.UnitSpec

trait BaseRuleSpec extends UnitSpec with TestFixture {

  def getRules[E <: Enumeration](weighting: E#Value)(implicit ruleSet: Seq[RuleSet]) =
    ruleSet.filter(_.result == weighting.toString).map(_.rules).toList

  def checkRules(expected: List[JsObject], actual: List[JsObject]): Unit = {
    expected.foreach { rule =>
      s"contain the rule: $rule" in {
        assert(actual.contains(rule))
      }
    }

    "Contain the correct number of rules" in {
      expected.length shouldBe actual.length
    }
  }
}
