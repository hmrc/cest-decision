/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.ruleSets

import play.api.libs.json._
import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

import scala.io.Source

trait BaseRules {

  val ruleSet: Seq[RuleSet]

  def parseRules(section: String, version: DecisionServiceVersion.Value): Seq[RuleSet] = {

    lazy val csv = getClass.getResourceAsStream(s"/tables/$version/$section.csv")
    lazy val file = Source.fromInputStream(csv)

    lazy val csvRules: List[String] = file.getLines.toList
    lazy val headers: List[String] = csvRules.head.split(",").toList

    val resultSet = csvRules.tail.map { row =>

      val columns = row.split(",")
      val answers = columns.dropRight(1)
      val result = columns.last

      val rules: Map[String, JsValue] = headers.zip(answers).filterNot(_._2.isEmpty).map {
        case (key, "true") => key -> JsBoolean(true)
        case (key, "false") => key -> JsBoolean(false)
        case (key, value) => key -> JsString(value)
      }.toMap

      RuleSet(JsObject(rules), result)
    }

    file.close()
    resultSet
  }
}
