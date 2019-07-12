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

import play.api.libs.json._
import uk.gov.hmrc.decisionservice.models.rules.{RulesSet, RulesSetWithResult}

abstract class RuleChecker {

  def ruleSet: Seq[RulesSetWithResult]

  def checkRules[T](section: T)(implicit writes: Writes[T]): String = {
    val jsObject: JsObject = Json.toJson(section).as[JsObject]
    checkOutcome(jsObject,ruleSet)
  }

//  private def checkOutcome(section: JsObject,rules: Seq[RulesSetWithResult] = ruleSet): String = {
//    if(rules.isEmpty) "undetermined" else {
//      val currentRule = rules.head
//      if(currentRule.rulesSet.map { rule =>
//        println(currentRule.result)
//        println(rule)
//        section.fields.contains(rule.fields)
//      }.exists(res => res)) currentRule.result else checkOutcome(section,rules.tail)
//    }
//  }

  private def checkOutcome(section: JsObject,rules: Seq[RulesSetWithResult]): String = {
    if(rules.isEmpty) "undetermined" else {

      val currentRule = rules.head

      if(currentRule.rulesSet.exists { rule =>
        rule.fields.forall(section.fields.contains)
      }) currentRule.result else checkOutcome(section, rules.tail)
    }
  }




//  private def checkOutRules(implicit section: JsObject) = {
//    ruleSet.OutOfIR35.fold(None: Option[String]){_.flatMap { rules =>
//      rules.fields.map { rule =>
//        if (section.fields.contains(rule)) Some(ruleSet.out) else None
//      }
//    }.headOption.flatten
//    }
//  }
//
//  private def checkHighRules(implicit section: JsObject) = {
//    ruleSet.HIGH.fold(None: Option[String]){_.flatMap { rules =>
//      rules.fields.map { rule =>
//        if (section.fields.contains(rule)) Some(ruleSet.high) else None
//      }
//    }.headOption.flatten
//    }
//  }
//
//  private def checkMediumRules(implicit section: JsObject) = {
//    ruleSet.MEDIUM.fold(None: Option[String]){_.flatMap { rules =>
//      rules.fields.map { rule =>
//        if (section.fields.contains(rule)) Some(ruleSet.medium) else None
//      }
//    }.headOption.flatten
//    }
//  }

}

class ControlRules extends RuleChecker {
  override def ruleSet: Seq[RulesSetWithResult] = JsonFileReader.controlFile.rulesInOrder
}

//class ExitRules extends RuleChecker {
//  override val ruleSet: List[JsObject] = JsonFileReader.exitFile
//}
//
//class PersonalServiceRules extends RuleChecker {
//  override val ruleSet: List[JsObject] = JsonFileReader.personalServiceFile
//}
//
//class PartAndParcelRules extends RuleChecker {
//  override val ruleSet: List[JsObject] = JsonFileReader.partAndParcelFile
//}
//
//class FinancialRiskRules extends RuleChecker {
//  override val ruleSet: List[JsObject] = JsonFileReader.financialRiskFile
//}
//
//class MatrixOfMatricesRules extends RuleChecker {
//  override val ruleSet: List[JsObject] = JsonFileReader.matrixOfMatricesFile
//}
