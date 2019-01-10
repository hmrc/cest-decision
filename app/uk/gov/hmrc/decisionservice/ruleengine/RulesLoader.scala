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

package uk.gov.hmrc.decisionservice.ruleengine

import cats.data.Validated
import uk.gov.hmrc.decisionservice.model._
import uk.gov.hmrc.decisionservice.model.rules._

import scala.util.{Failure, Success, Try}
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes._
import cats.implicits._
import uk.gov.hmrc.decisionservice.Validation
import uk.gov.hmrc.decisionservice.util.FileTokenizer.tokenize

trait RulesLoader {

  val rulesFileLineValidator:RulesFileLineValidator

  def load(implicit rulesFileMetaData: RulesFileMetaData):Validation[SectionRuleSet] =
    tokenize(rulesFileMetaData.path) match {
      case Success(tokens) =>
        parseRules(tokens)
      case Failure(e) =>
        Validated.invalid(List(RulesFileError(UNABLE_TO_READ_OR_PARSE_RULES_FILE, e.getMessage)))
    }

  private def parseRules(tokens: List[List[String]])(implicit rulesFileMetaData: RulesFileMetaData): Validation[SectionRuleSet] = tokens match {
    case Nil => Validated.invalid(List(RulesFileError(EMPTY_RULES_FILE, s"empty rules file ${rulesFileMetaData.path}")))
    case (headings :: rest) =>
      val errorsInHeadings = rulesFileLineValidator.validateColumnHeaders(headings, rulesFileMetaData)
      val errorsInRules = rest.zipWithIndex.map(validateLine _)
      val combinedValidation = errorsInRules.foldLeft(errorsInHeadings)(_ combine _)
      combinedValidation match {
        case Validated.Valid(_) => createRuleSet(rulesFileMetaData, rest, headings)
        case Validated.Invalid(a) => Validated.invalid(a)
      }
  }

  private def validateLine(tokensWithIndex:(List[String],Int))(implicit rulesFileMetaData: RulesFileMetaData):Validation[Unit] = {
    tokensWithIndex match {
      case (t, l) if t.slice(rulesFileMetaData.valueCols, rulesFileMetaData.numCols).isEmpty =>
        Validated.invalid(List(RulesFileError(RESULT_MISSING_IN_RULES_FILE,
          s"in line ${l + 2} all result tokens are empty in file ${rulesFileMetaData.path}")))
      case (t, l) if t.size > rulesFileMetaData.numCols =>
        Validated.invalid(List(RulesFileError(TOO_MANY_COLUMNS_IN_RULES_FILE,
          s"in line ${l + 2} number of columns is ${t.size}, should be no more than ${rulesFileMetaData.numCols} in file ${rulesFileMetaData.path}")))
      case (t, l) =>
        rulesFileLineValidator.validateLine(t, rulesFileMetaData, l + 2)
      case _ =>
        Validated.valid(())
    }
  }

  def createRule(tokens:List[String], rulesFileMetaData: RulesFileMetaData):SectionRule = {
    val result = >>>(tokens.drop(rulesFileMetaData.valueCols))
    val values = tokens.take(rulesFileMetaData.valueCols)
    SectionRule(values.map(>>>(_)), result)
  }

  def createRuleSet(rulesFileMetaData:RulesFileMetaData, ruleTokens:List[List[String]], headings:List[String]):Validation[SectionRuleSet] = {
    Try {
      val rules = ruleTokens.map(createRule(_, rulesFileMetaData))
      SectionRuleSet(rulesFileMetaData.name, headings.take(rulesFileMetaData.valueCols), rules)
    }
    match {
      case Success(sectionRuleSet) => Validated.valid(sectionRuleSet)
      case Failure(e) => Validated.invalid(List(RulesFileError(UNABLE_TO_CREATE_RULE, e.getMessage)))
    }
  }

  def createErrorMessage(tokens:List[List[String]]):String = tokens.map(a => s"$a").mkString(" ")
}

object RulesLoaderInstance extends RulesLoader {
  val rulesFileLineValidator = RulesFileLineValidatorInstance
}
