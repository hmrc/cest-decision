/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.PartAndParcel
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PartAndParcelRules

import scala.concurrent.Future

class PartAndParcelRuleEngine @Inject()() extends RuleEngine {

  def decide(partAndParcel: Option[PartAndParcel])(implicit version: DecisionServiceVersion.Value): Future[Option[WeightedAnswerEnum.Value]] = {

    val rules = PartAndParcelRules(version)

    Future.successful(partAndParcel flatMap {
      case PartAndParcel(None, None, None, None) => None
      case section => {
        val result = checkRules(section, rules.ruleSet)
        Some(WeightedAnswerEnum.withName(result))
      }
    })
  }
}
