/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import play.api.libs.json.{JsObject, Json, Writes}
import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

trait RuleEngine {

  def checkRules[T,A](section: T,
                      ruleSet: Seq[RuleSet],
                      notMatched: A = WeightedAnswerEnum.NOT_VALID_USE_CASE)(implicit writes: Writes[T]): String = {

    val sectionAsJson: JsObject = Json.toJson(section).as[JsObject]

    ruleSet.find(_.rules.fields.forall(sectionAsJson.fields.contains)) match {
      case Some(matchedRule) => matchedRule.result
      case _ => notMatched.toString
    }
  }
}
