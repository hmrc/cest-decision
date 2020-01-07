/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.BusinessOnOwnAccount
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.BusinessOnOwnAccountRules

import scala.concurrent.Future

class BusinessOnOwnAccountRuleEngine @Inject()() extends RuleEngine {

  def decide(businessOnOwnAccount: Option[BusinessOnOwnAccount])(implicit version: DecisionServiceVersion.Value): Future[Option[WeightedAnswerEnum.Value]] =
    Future.successful(
      if(version == DecisionServiceVersion.v1_5_0) {
        None
      } else {

        val rules = BusinessOnOwnAccountRules(version)

        businessOnOwnAccount flatMap {
          case BusinessOnOwnAccount(None, None, None, None, None) => Some(WeightedAnswerEnum.MEDIUM)
          case section => {
            val result = checkRules(section, rules.ruleSet)
            Some(WeightedAnswerEnum.withName(result))
          }
        }
      }
    )
}