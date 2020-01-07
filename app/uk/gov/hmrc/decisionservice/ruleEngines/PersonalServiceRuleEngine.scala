/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.PersonalService
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PersonalServiceRules

import scala.concurrent.Future

class PersonalServiceRuleEngine @Inject()() extends RuleEngine {

  def decide(personalService: Option[PersonalService])(implicit version: DecisionServiceVersion.Value): Future[Option[WeightedAnswerEnum.Value]] = {

    val rules = PersonalServiceRules(version)

    Future.successful(personalService flatMap {
      case PersonalService(None, None, None, None, None) => None
      case section => {
        val result = checkRules(section, rules.ruleSet)
        Some(WeightedAnswerEnum.withName(result))
      }
    })
  }
}
