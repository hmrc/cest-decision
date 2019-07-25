/*
 * Copyright 2019 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.services

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.enums.ExitEnum
import uk.gov.hmrc.decisionservice.util.ExitRulesSet

import scala.concurrent.Future

class ExitDecisionService @Inject()(ruleSet: ExitRulesSet) {

  def decide(exit: Option[Exit]): Future[Option[ExitEnum.Value]] = {

    exit.fold[Future[Option[ExitEnum.Value]]](Future.successful(None))(exit => {

      if(exit.officeHolder.contains(false)){

        Future.successful(Some(ExitEnum.CONTINUE))
      } else {
        val result = ruleSet.checkRules(exit)

        Future.successful(Some(ExitEnum.withName(result)))
      }
    })
  }
}
