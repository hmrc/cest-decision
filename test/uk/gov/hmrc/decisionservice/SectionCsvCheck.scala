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

package uk.gov.hmrc.decisionservice
import cats.data.{Validated}
import org.scalacheck.{Gen, Prop, Properties}
import uk.gov.hmrc.decisionservice.model.rules.{>>>, CarryOver, SectionRuleSet}
import uk.gov.hmrc.decisionservice.ruleengine.{FactMatcherInstance, RulesFileMetaData, RulesLoaderInstance}
import uk.gov.hmrc.play.test.UnitSpec


trait CsvCheck {

  def show(x:Any) = {}
  def showln() = {}
  def showln(x:Any) = {}

  def prettyPrint(m: Map[String, CarryOver]): Unit = show(m.keySet.toList.sorted.map(a=>s"${a} ${m(a).value}").mkString("\t"))

  def check(l: List[String], ruleSet: SectionRuleSet):Boolean = {
    showln
    val ll = l map (>>>(_))
    val pairs = ruleSet.headings zip ll
    val m = Map(pairs: _*)
    prettyPrint(m)
    val response = FactMatcherInstance.matchFacts(m, ruleSet)
    response match {
      case Validated.Valid(sectionResult) =>
        show(s"\t${sectionResult.value}")
      case Validated.Invalid(e) =>
        show(s"\t${e(0)}")
    }
    true
  }
}

object BusinessStructureCheck extends Properties("business structure check") with CsvCheck with UnitSpec {
  val csvMetadata = RulesFileMetaData(7, "/decisionservicespec/business-structure.csv", "BusinessStructure")
  val gen = for {
    y <- Gen.listOfN(csvMetadata.valueCols, Gen.oneOf[String]("Yes", "No"))
  } yield {
    y
  }
  val maybeRules = RulesLoaderInstance.load(csvMetadata)
  maybeRules.map { ruleSet =>
    property("rule should match all possible facts") =
      Prop.forAll(gen) { l =>
        check(l, ruleSet)
      }
  }
}


object PersonalServiceCheck extends Properties("personal service check") with CsvCheck with UnitSpec {
  val csvMetadata = RulesFileMetaData(9, "/decisionservicespec/personal-service.csv", "PersonalService")
  val gen = for {
    y <- Gen.listOfN(csvMetadata.valueCols, Gen.oneOf[String]("Yes", "No"))
  } yield {
    y
  }
  val maybeRules = RulesLoaderInstance.load(csvMetadata)
  maybeRules.map { ruleSet =>
    property("rule should match all possible facts") =
      Prop.forAll(gen) { l =>
        check(l, ruleSet)
      }
  }
}
