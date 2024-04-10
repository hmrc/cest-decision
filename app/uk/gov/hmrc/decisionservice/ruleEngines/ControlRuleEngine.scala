/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Control
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.ControlRules

import scala.concurrent.Future

class ControlRuleEngine @Inject()() extends RuleEngine {

  def decide(control: Option[Control])(implicit version: DecisionServiceVersion.Value): Future[Option[WeightedAnswerEnum.Value]] = {

    val rules = ControlRules(version)

    Future.successful(control flatMap {
      case Control(None, None, None, None) => None
      case section => {
        val result = checkRules(section, rules.ruleSet)
        Some(WeightedAnswerEnum.withName(result))
      }
    })
  }
}
