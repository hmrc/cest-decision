/*
 * Copyright 2017 HM Revenue & Customs
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

import cats.Semigroup
import cats.data.Validated
import uk.gov.hmrc.decisionservice.Validation
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes._
import uk.gov.hmrc.decisionservice.model.rules._
import uk.gov.hmrc.decisionservice.model.{DecisionServiceError, RulesFileError}
import uk.gov.hmrc.decisionservice.ruleengine._
import uk.gov.hmrc.decisionservice.ruleengine.MatchingFunctions._
import uk.gov.hmrc.decisionservice.ruleengine.FactValidatingFunctions._

object ErrorListSemigroup extends Semigroup[List[DecisionServiceError]] {
  override def combine(x: List[DecisionServiceError], y: List[DecisionServiceError]): List[DecisionServiceError] = x ::: y
}

trait DecisionService {
  implicit val errorListSemigroup = Semigroup(ErrorListSemigroup)
  implicit val sectionRuleSetSemigroup = Semigroup(SectionRuleSet("", List(), List()))

  val ruleEngine:RuleEngine = RuleEngineInstance

  lazy val extraRules:List[SectionRuleSet] = List()

  val maybeSectionRules:Validation[List[SectionRuleSet]]

  val csvSectionMetadata:List[RulesFileMetaData]

  def loadSectionRules():Validation[List[SectionRuleSet]] = {
    val maybeRules = extraRules.map(Validated.valid(_)) ::: csvSectionMetadata.map(RulesLoaderInstance.load(_))
    val combined = if (maybeRules.isEmpty)
      Validated.invalid(List(RulesFileError(MISSING_RULE_FILES, "missing rule files")))
    else
      maybeRules.reduceLeft(_ combine _)
    combined match {
      case Validated.Valid(_) => Validated.valid(maybeRules.collect {case Validated.Valid(a) => a})
      case Validated.Invalid(e) => Validated.invalid(e)
    }
  }

  def ==>:(facts:Facts):Validation[RuleEngineDecision] = {
    maybeSectionRules match {
      case Validated.Valid(sectionRules) =>
        ruleEngine.processRules(Rules(sectionRules),facts)
      case Validated.Invalid(e) => Validated.invalid(e)
    }
  }
}

object DecisionServiceInstance110Final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  lazy val csvSectionMetadata = List(
    (4, "/tables/1.1.0-final/control.csv", "control"),
    (7, "/tables/1.1.0-final/financial-risk.csv", "financialRisk"),
    (4, "/tables/1.1.0-final/part-and-parcel.csv", "partAndParcel"),
    (5, "/tables/1.1.0-final/personal-service.csv", "personalService"),
    (4, "/tables/1.1.0-final/matrix-of-matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}

object DecisionServiceInstance111Final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  lazy val csvSectionMetadata = List(
    (4, "/tables/1.1.1-final/control.csv", "control"),
    (7, "/tables/1.1.1-final/financial-risk.csv", "financialRisk"),
    (4, "/tables/1.1.1-final/part-and-parcel.csv", "partAndParcel"),
    (5, "/tables/1.1.1-final/personal-service.csv", "personalService"),
    (4, "/tables/1.1.1-final/matrix-of-matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}

object DecisionServiceInstance120Final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  lazy val csvSectionMetadata = List(
    (4, "/tables/1.2.0-final/control.csv", "control"),
    (7, "/tables/1.2.0-final/financial-risk.csv", "financialRisk"),
    (4, "/tables/1.2.0-final/part-and-parcel.csv", "partAndParcel"),
    (5, "/tables/1.2.0-final/personal-service.csv", "personalService"),
    (4, "/tables/1.2.0-final/matrix-of-matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}

object DecisionServiceInstance130Final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  lazy val csvSectionMetadata = List(
    (4, "/tables/1.3.0-final/control.csv", "control"),
    (7, "/tables/1.3.0-final/financial-risk.csv", "financialRisk"),
    (4, "/tables/1.3.0-final/part-and-parcel.csv", "partAndParcel"),
    (5, "/tables/1.3.0-final/personal-service.csv", "personalService"),
    (4, "/tables/1.3.0-final/matrix-of-matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}

object DecisionServiceInstance140Final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  lazy val csvSectionMetadata = List(
    (3, "/tables/1.4.0-final/setup.csv", "setup"),
    (1, "/tables/1.4.0-final/exit.csv", "exit"),
    (4, "/tables/1.4.0-final/control.csv", "control"),
    (7, "/tables/1.4.0-final/financial-risk.csv", "financialRisk"),
    (4, "/tables/1.4.0-final/part-and-parcel.csv", "partAndParcel"),
    (5, "/tables/1.4.0-final/personal-service.csv", "personalService"),
    (4, "/tables/1.4.0-final/matrix-of-matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}

object DecisionServiceInstance150Final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  lazy val csvSectionMetadata = List(
    (3, "/tables/1.5.0-final/setup.csv", "setup"),
    (1, "/tables/1.5.0-final/exit.csv", "exit"),
    (4, "/tables/1.5.0-final/control.csv", "control"),
    (7, "/tables/1.5.0-final/financial-risk.csv", "financialRisk"),
    (4, "/tables/1.5.0-final/part-and-parcel.csv", "partAndParcel"),
    (5, "/tables/1.5.0-final/personal-service.csv", "personalService"),
    (4, "/tables/1.5.0-final/matrix-of-matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}
