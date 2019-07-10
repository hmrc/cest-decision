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

import play.api.libs.json.{JsObject, Json, Writes}

abstract class RuleChecker {

  val ruleSet: List[JsObject]

  def checkRules[T](section: T)(implicit writes: Writes[T]) =
    ruleSet.map { rule =>
      rule.fields.map(fields =>
        Json.toJson(section).as[JsObject].fields.contains(fields)).exists(i => i)
    }

}

class ControlRules extends RuleChecker {
  override val ruleSet: List[JsObject] = JsonFileReader.controlFile
}

class ExitRules extends RuleChecker {
  override val ruleSet: List[JsObject] = JsonFileReader.exitFile
}

class PersonalServiceRules extends RuleChecker {
  override val ruleSet: List[JsObject] = JsonFileReader.personalServiceFile
}

class PartAndParcelRules extends RuleChecker {
  override val ruleSet: List[JsObject] = JsonFileReader.partAndParcelFile
}

class FinancialRiskRules extends RuleChecker {
  override val ruleSet: List[JsObject] = JsonFileReader.financialRiskFile
}

class MatrixOfMatricesRules extends RuleChecker {
  override val ruleSet: List[JsObject] = JsonFileReader.matrixOfMatricesFile
}
