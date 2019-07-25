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

package uk.gov.hmrc.decisionservice.util

import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.RuleSet
import uk.gov.hmrc.decisionservice.models.Control.{engagerMovingWorker, whenWorkHasToBeDone, workerDecideWhere, workerDecidingHowWorkIsDone}
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.FinancialRisk.{paidForSubstandardWork, workerMainIncome, workerUsedVehicle, _}
import uk.gov.hmrc.decisionservice.models.PartAndParcel._
import uk.gov.hmrc.decisionservice.models.PersonalService.{workerPayActualSubstitute, workerSentActualSubstitute, wouldWorkerPayHelper}
import uk.gov.hmrc.decisionservice.models.enums.HowWorkerIsPaid._
import uk.gov.hmrc.decisionservice.models.enums.PutRightAtOwnCost._
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.play.test.UnitSpec

class RuleCheckerSpec extends UnitSpec {

  "CheckRules" should {

    "check the Control section is checked properly" should {

      val controlRuleSet = new ControlRulesSet
      val ruleChecker = new RuleChecker {
        override def ruleSet: Seq[RuleSet] = controlRuleSet.ruleSet
      }

      "check an OUT rule is matched" in {

        val input = Json.obj(
          engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
          workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
          whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
          workerDecideWhere -> ChooseWhereWork.workerChooses
        )

        ruleChecker.checkRules(input) shouldBe "OUTOFIR35"
      }

      "check a HIGH rule is matched" in {

        val input = Json.obj(
          engagerMovingWorker -> MoveWorker.canMoveWorkerWithPermission,
          workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
          whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
          workerDecideWhere -> ChooseWhereWork.workerCannotChoose
        )

        ruleChecker.checkRules(input) shouldBe "HIGH"
      }

      "check a MEDIUM rule is matched" in {

        val input = Json.obj(
          engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
          workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
          whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
          workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
        )

        ruleChecker.checkRules(input) shouldBe "MEDIUM"
      }

      "check undetermined occurs" in {

        val input = Json.obj(
          engagerMovingWorker -> MoveWorker.cannotMoveWorkerWithoutNewAgreement,
          workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
          whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
          workerDecideWhere -> "jeff"
        )

        ruleChecker.checkRules(input) shouldBe WeightedAnswerEnum.NOT_VALID_USE_CASE.toString
      }
    }

    "check the Early Exit section is checked properly" should {

      val earlyExitRuleSet = new ExitRulesSet
      val ruleChecker = new RuleChecker {
        override def ruleSet: Seq[RuleSet] = earlyExitRuleSet.ruleSet
      }

      "check an IN rule is matched" in {
        val input = Json.obj(
          Exit.officeHolder -> true
        )

        ruleChecker.checkRules(input) shouldBe "INIR35"
      }
    }

    "check the FinancialRisk section is checked properly" should {

      val financialRiskSet = new FinancialRiskRulesSet
      val ruleChecker = new RuleChecker {
        override def ruleSet: Seq[RuleSet] = financialRiskSet.ruleSet
      }

      "check an OUT rule is matched" in {

        val input = Json.obj(
          workerUsedVehicle -> true,
          workerMainIncome -> hourlyDailyOrWeekly,
          paidForSubstandardWork -> outsideOfHoursNoCharge
        )

        ruleChecker.checkRules(input) shouldBe "OUTOFIR35"
      }

      "check a MEDIUM rule is matched" in {

        val input = Json.obj(

            workerProvidedMaterials -> false,
            workerProvidedEquipment -> false,
          workerUsedVehicle -> true,
          workerMainIncome -> hourlyDailyOrWeekly,
          paidForSubstandardWork -> outsideOfHoursNoCosts
        )

        ruleChecker.checkRules(input) shouldBe "MEDIUM"
      }

      "check a LOW rule is matched" in {

        val input = Json.obj(

            workerProvidedMaterials -> false,
            workerProvidedEquipment -> false,
          expensesAreNotRelevantForRole -> true,
          workerMainIncome -> fixedPrice,
          paidForSubstandardWork -> asPartOfUsualRateInWorkingHours
        )

        ruleChecker.checkRules(input) shouldBe "LOW"
      }

      "check undetermined occurs" in {

        val input = Json.obj(
          workerUsedVehicle -> false,
          workerMainIncome -> hourlyDailyOrWeekly,
          paidForSubstandardWork -> outsideOfHoursNoCharge
        )

        ruleChecker.checkRules(input) shouldBe WeightedAnswerEnum.NOT_VALID_USE_CASE.toString
      }
    }

    "check the PartAndParcel section is checked properly" should {

      val partAndParcelSet = new PartAndParcelRulesSet
      val ruleChecker = new RuleChecker {
        override def ruleSet: Seq[RuleSet] = partAndParcelSet.ruleSet
      }

      "check an HIGH rule is matched" in {

        val input = Json.obj(
          workerReceivesBenefits -> true
        )

        ruleChecker.checkRules(input) shouldBe "HIGH"
      }

      "check a MEDIUM rule is matched" in {

        val input = Json.obj(
          workerReceivesBenefits -> false,
          workerAsLineManager -> false,
          contactWithEngagerCustomer -> true,
          workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
        )

        ruleChecker.checkRules(input) shouldBe "MEDIUM"
      }

      "check a LOW rule is matched" in {

        val input = Json.obj(
          workerReceivesBenefits -> false,
          workerAsLineManager -> false,
          contactWithEngagerCustomer -> true,
          workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
        )

        ruleChecker.checkRules(input) shouldBe "LOW"
      }

      "check undetermined occurs" in {

        val input = Json.obj(
          workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
        )

        ruleChecker.checkRules(input) shouldBe WeightedAnswerEnum.NOT_VALID_USE_CASE.toString
      }
    }

    "check the PersonalService section is checked properly" should {

      val personalServiceSet = new PersonalServiceRulesSet
      val ruleChecker = new RuleChecker {
        override def ruleSet: Seq[RuleSet] = personalServiceSet.ruleSet
      }

      "check an OUT rule is matched" in {

        val input = Json.obj(
          workerSentActualSubstitute -> ArrangedSubstitute.yesClientAgreed,
          workerPayActualSubstitute -> true
        )

        ruleChecker.checkRules(input) shouldBe "OUTOFIR35"
      }

      "check a HIGH rule is matched" in {

        val input = Json.obj(
          workerSentActualSubstitute -> ArrangedSubstitute.notAgreedWithClient,
          wouldWorkerPayHelper -> false
        )

        ruleChecker.checkRules(input) shouldBe "HIGH"
      }

      "check a MEDIUM rule is matched" in {
        val input = Json.obj(
          workerSentActualSubstitute -> ArrangedSubstitute.yesClientAgreed,
          workerPayActualSubstitute -> false,
          wouldWorkerPayHelper -> true
        )

        ruleChecker.checkRules(input) shouldBe "MEDIUM"
      }

      "check undetermined occurs" in {

        val input = Json.obj(
          workerSentActualSubstitute -> ArrangedSubstitute.notAgreedWithClient
        )

        ruleChecker.checkRules(input) shouldBe WeightedAnswerEnum.NOT_VALID_USE_CASE.toString
      }
    }


  }
}
