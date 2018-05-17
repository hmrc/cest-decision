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

package uk.gov.hmrc.decisionservice.model.api

object ErrorCodes {
  val REQUEST_FORMAT: Int = 4001
  val DECISION_ENGINE: Int = 5001
  val INVALID_VALUE_IN_RULES_FILE: Int = 5003
  val MISSING_CARRY_OVER_IN_RULES_FILE: Int = 5004
  val INVALID_CARRY_OVER_VALUE_IN_RULES_FILE: Int = 5005
  val INVALID_EXIT_VALUE_IN_RULES_FILE: Int = 5006
  val INVALID_ROW_SIZE_IN_RULES_FILE: Int = 5007
  val UNABLE_TO_READ_OR_PARSE_RULES_FILE: Int = 5008
  val EMPTY_RULES_FILE: Int = 5009
  val RESULT_MISSING_IN_RULES_FILE: Int = 5010
  val TOO_MANY_COLUMNS_IN_RULES_FILE: Int = 5011
  val UNABLE_TO_CREATE_RULE: Int = 5012
  val INVALID_HEADER_SIZE_IN_RULES_FILE: Int = 5013
  val MISSING_RULE_FILES: Int = 5014
  val INVALID_VERSION:Int = 5015
  val INCORRECT_FACT: Int = 6006
  val FACT_WITH_TOO_MANY_EMPTY_VALUES: Int = 6007
}
