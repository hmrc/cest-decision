/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.config.ruleSets.v1_5_0

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.BaseRuleSpec
import uk.gov.hmrc.decisionservice.models.enums.Section._
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum._
import uk.gov.hmrc.decisionservice.ruleSets.MatrixOfMatricesRules_v150

class MatrixOfMatricesRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite {

  implicit def enumTuple[E <: Enumeration, B <: Enumeration](x: (E#Value, B#Value)): (String, Json.JsValueWrapper) = (x._1.toString, x._2)

  val ruleSet = MatrixOfMatricesRules_v150.ruleSet

  "For all the expected InIR35 rules" should {

    val actual = ruleSet.filter(_.result == "INIR35").map(_.rules).toList

    val expected = List(
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      )
    )

    checkRules(expected, actual)
  }

  "For all the expected Indeterminate rules" should {

    val actual = ruleSet.filter(_.result == "UNKNOWN").map(_.rules).toList

    val expected = List(
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      )
    )

    checkRules(expected, actual)
  }
}
