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

import play.api.Logger
import uk.gov.hmrc.decisionservice.model.rules.{>>>, CarryOver, NotValidUseCase, SectionRule}

object MatchingFunctions {
  def matches(sr: SectionRule, factValues: List[CarryOver]): Option[CarryOver] = {
    factValues.zip(sr.values).filterNot(>>>.equivalent(_)) match {
      case Nil =>
        Logger.info(s"matched:\t${sr.values.map(_.value).mkString("\t,")}")
        Some(sr.result)
      case _ => None
    }
  }
  def businessStructureMatches(sr: SectionRule, factValues: List[CarryOver]): Option[CarryOver] = {
    val result = factValues match {
      case Nil => Some(NotValidUseCase)
      case xs if (xs.forall(_.isEmpty)) => Some(NotValidUseCase)
      case x::xs => x.value match {
        case "zeroToThree" => Some(>>>("low"))
        case "tenPlus" => Some(>>>("high"))
        case _ =>
          val count = xs.count(_.value.toLowerCase == "yes")
          Some(>>>(if (count < 2) "low" else if (count < 4) "medium" else "high"))
      }
    }
    Logger.info(s"pseudo-match result is: ${result.getOrElse("none")}")
    result
  }
}
