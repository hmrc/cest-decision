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

package uk.gov.hmrc.decisionservice.transformer

import uk.gov.hmrc.decisionservice.models.analytics.{FinancialRisk, Interview, InterviewSearchResponse}

object InterviewTransformer {

  def toResponse(interviews: List[Interview]) : List[InterviewSearchResponse]= {
    for(interview <- interviews)
      yield {
      InterviewSearchResponse(interview.compressedInterview, interview.route, interview.decision, interview.count.getOrElse(""),
        interview.setup.endUserRole, interview.setup.hasContractStarted, interview.setup.provideServices,
        interview.exit.officeHolder,
        interview.personalService.fold("")(_.workerSentActualSubstitute.getOrElse("")),
        interview.personalService.fold("")(_.workerPayActualSubstitute.getOrElse("")),
        interview.personalService.fold("")(_.possibleSubstituteRejection.getOrElse("")),
        interview.personalService.fold("")(_.possibleSubstituteWorkerPay.getOrElse("")),
        interview.personalService.fold("")(_.wouldWorkerPayHelper.getOrElse("")),
        interview.control.fold("")(_.engagerMovingWorker.getOrElse("")),
        interview.control.fold("")(_.workerDecidingHowWorkIsDone.getOrElse("")),
        interview.control.fold("")(_.workHasToBeDone.getOrElse("")),
        interview.control.fold("")(_.workerDecideWhere.getOrElse("")),
        haveToPayButCannotClaim(interview.financialRisk),
        interview.financialRisk.fold("")(_.workerMainIncome.getOrElse("")),
        interview.financialRisk.fold("")(_.paidForSubstandardWork.getOrElse("")),
        interview.partAndParcel.fold("")(_.workerReceivesBenefits.getOrElse("")),
        interview.partAndParcel.fold("")(_.workerAsLineManager.getOrElse("")),
        interview.partAndParcel.fold("")(_.contactWithEngagerCustomer.getOrElse("")),
        interview.partAndParcel.fold("")(_.workerRepresentsEngagerBusiness.getOrElse(""))
      )
    }
  }

  private def haveToPayButCannotClaim(financialRisk: Option[FinancialRisk]): String = {
    var haveToPayButCannotClaim : List[String]= List()

    financialRisk.fold("|"){ f =>
      if (f.workerProvidedMaterials.isDefined) haveToPayButCannotClaim = "financialRisk.workerProvidedMaterials" :: haveToPayButCannotClaim
      if (f.workerProvidedEquipment.isDefined) haveToPayButCannotClaim = "financialRisk.workerProvidedEquipment" :: haveToPayButCannotClaim
      if (f.workerUsedVehicle.isDefined) haveToPayButCannotClaim = "financialRisk.workerUsedVehicle" :: haveToPayButCannotClaim
      if (f.workerHadOtherExpenses.isDefined) haveToPayButCannotClaim = "financialRisk.workerHadOtherExpenses" :: haveToPayButCannotClaim
      if (f.expensesAreNotRelevantForRole.isDefined) haveToPayButCannotClaim = "financialRisk.expensesAreNotRelevantForRole" :: haveToPayButCannotClaim

      "|" + haveToPayButCannotClaim.mkString("|")
    }

  }

}
