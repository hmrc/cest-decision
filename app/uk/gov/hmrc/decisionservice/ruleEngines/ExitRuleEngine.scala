/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, ExitEnum}
import uk.gov.hmrc.decisionservice.ruleSets.EarlyExitRules

import scala.concurrent.Future

class ExitRuleEngine @Inject()() extends RuleEngine {

  def decide(exit: Option[Exit])(implicit version: DecisionServiceVersion.Value): Future[Option[ExitEnum.Value]] = {

    val rules = EarlyExitRules(version)

    Future.successful(exit flatMap {
      case Exit(None) => None
      case section => {
        val result = checkRules(section, rules.ruleSet)
        Some(ExitEnum.withName(result))
      }
    })
  }
}
