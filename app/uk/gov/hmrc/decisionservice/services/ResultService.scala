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
import uk.gov.hmrc.decisionservice.models.enums.{ExitEnum, ResultEnum, WeightedAnswerEnum}

import scala.concurrent.Future

class ResultService @Inject()() {

  def decide(exit: Option[ExitEnum.Value],
             personalService: Option[WeightedAnswerEnum.Value],
             control: Option[WeightedAnswerEnum.Value],
             financialRisk: Option[WeightedAnswerEnum.Value],
             partAndParcel: Option[WeightedAnswerEnum.Value]
            ): Future[ResultEnum.Value] = {

    Future.successful(ResultEnum.UNKNOWN)
  }

}
