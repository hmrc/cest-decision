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
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum
import uk.gov.hmrc.decisionservice.models.{FinancialRisk, Section}

import scala.concurrent.Future

class FinancialRiskDecisionService @Inject()() {

  def decide(section: Section): Future[Option[WeightedAnswerEnum.Value]] = {

    val financialRisk = section.asInstanceOf[FinancialRisk]

    financialRisk match {
      case FinancialRisk(None, None, None, None, None, None, None) =>

        Future.successful(None)

      case FinancialRisk(workerProvidedMaterials, workerProvidedEquipment, workerUsedVehicle,
      workerHadOtherExpenses, expensesAreNotRelevantForRole, workerMainIncome, paidForSubstandardWork) =>

        //look up rules
        Future.successful(Some(WeightedAnswerEnum.OUTSIDE_IR35))
    }
  }
}