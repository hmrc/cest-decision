/*
 * Copyright 2018 HM Revenue & Customs
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
import cats.implicits._
import uk.gov.hmrc.decisionservice.Validation
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes._
import uk.gov.hmrc.decisionservice.model.{DecisionServiceError, RulesFileError}

sealed trait RulesFileLineValidator {

  val allowedCarryOverValues: List[String]
  val allowedValues: List[String]
  val allowedDecisionValues: List[String]
  val allowedExitValues: List[String]

  def validateValue(value: String, errorMessage: String): Validation[Unit] = {
    val option = Some(()) // allowedValues.find(_ == value.trim.toLowerCase).map(_ => ())
    val error:DecisionServiceError = RulesFileError(INVALID_VALUE_IN_RULES_FILE, errorMessage)
    Validated.fromOption[List[DecisionServiceError], Unit](option, List(error))
  }

  def validateResultCells(resultCells: List[String], rulesFileMetaData: RulesFileMetaData, row:Int): Validation[Unit] =
    resultCells match {
      case Nil =>
        Validated.invalid(List(RulesFileError(MISSING_CARRY_OVER_IN_RULES_FILE,
          s"missing carry over in row $row in file ${rulesFileMetaData.path}")))
      case x::xs if !allowedCarryOverValues.contains(x.trim.toLowerCase) && !x.isEmpty =>
        Validated.invalid(List(RulesFileError(INVALID_CARRY_OVER_VALUE_IN_RULES_FILE,
          s"invalid carry over value $x in row $row in file ${rulesFileMetaData.path}")))
      case x::Nil => Validated.valid(())
      case x::exit::xs if !allowedExitValues.contains(exit.trim.toLowerCase()) =>
        Validated.invalid(List(RulesFileError(INVALID_EXIT_VALUE_IN_RULES_FILE,
          s"invalid exit value in row $row in file ${rulesFileMetaData.path}")))
      case _ => Validated.valid(())
    }

  def validateRowSize(row:List[String], rulesFileMetaData: RulesFileMetaData, rowNumber:Int) : Validation[Unit] =
    if (row.size > rulesFileMetaData.valueCols) Validated.valid(())
    else Validated.invalid(List(RulesFileError(INVALID_ROW_SIZE_IN_RULES_FILE,
      s"row size is ${row.size}, expected greater than ${rulesFileMetaData.valueCols} in row $rowNumber in file ${rulesFileMetaData.path}")))

  def validateColumnHeaders(row: List[String], rulesFileMetaData: RulesFileMetaData): Validation[Unit] =
    if (row.size >= rulesFileMetaData.valueCols) Validated.valid(())
    else Validated.invalid(List(RulesFileError(INVALID_HEADER_SIZE_IN_RULES_FILE,
      s"column header size is ${row.size}, should be ${rulesFileMetaData.valueCols} in file ${rulesFileMetaData.path}")))

  def validateLine(row:List[String], rulesFileMetaData: RulesFileMetaData, rowNumber:Int): Validation[Unit] = {
    val rowSizeValidation = validateRowSize(row, rulesFileMetaData, rowNumber)
    val (valueCells, resultCells) = row.splitAt(rulesFileMetaData.valueCols)
    val valueCellsValidations = valueCells.map(cell => validateValue(cell.trim,
      s"invalid value in row $rowNumber in file ${rulesFileMetaData.path}"))
    val resultCellsValidation = validateResultCells(resultCells, rulesFileMetaData, rowNumber)
    valueCellsValidations.foldLeft(rowSizeValidation)(_ combine _).combine(resultCellsValidation)
  }

}

object RulesFileLineValidatorInstance extends RulesFileLineValidator {
  val allowedDecisionValues = List("inir35", "outofir35", "unknown")
  val allowedCarryOverValues = List("low", "medium", "high", "continue") ::: allowedDecisionValues
  val allowedValues = List("yes", "no", "") ::: allowedCarryOverValues
  val allowedExitValues = List("true", "false", "")
}
