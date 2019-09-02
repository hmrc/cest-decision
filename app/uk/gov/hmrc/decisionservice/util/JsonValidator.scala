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


import cats.implicits._
import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.core.report.ProcessingReport
import com.github.fge.jsonschema.main.JsonSchemaFactory
import uk.gov.hmrc.decisionservice.models.analytics.AnalyticsVersion
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

import scala.io.Source

trait JsonValidatorTrait {
  val schemaPath:String

  object SuccessfulReport {
    def unapply(processingReport:ProcessingReport) = processingReport.isSuccess
  }

  object ProblemReport {
    def unapply(processingReport:ProcessingReport):Option[String] = Some(reportAsString(processingReport))
  }

  def validate(json : String) : Either[String,Unit] = {
    val jsonSchemaFactory: JsonSchemaFactory = JsonSchemaFactory.byDefault()
    val stream = getClass.getResourceAsStream(schemaPath)
    val content = Source.fromInputStream(stream).mkString
    val jsonSchemaNode = JsonLoader.fromString(content)
    val schema = jsonSchemaFactory.getJsonSchema(jsonSchemaNode)
    val jsonNode = JsonLoader.fromString(json)

    schema.validate(jsonNode) match {
      case SuccessfulReport() => Either.right(())
      case ProblemReport(s) => Either.left(s)
    }
  }

  def reportAsString(report:ProcessingReport) = {
    import scala.collection.JavaConversions._
    report.iterator().map(_.getMessage).mkString("\n")
  }
}

case class JsonSchemaValidator(val schemaPath: String) extends JsonValidatorTrait

object JsonResponseStrictValidatorFactory {
  lazy val jsonResponseValidators = Map(
    DecisionServiceVersion.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION110_FINAL.toString }/off-payroll-response-schema-strict.json"),
    DecisionServiceVersion.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION111_FINAL.toString }/off-payroll-response-schema-strict.json"),
    DecisionServiceVersion.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION120_FINAL.toString }/off-payroll-response-schema-strict.json"),
    DecisionServiceVersion.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION130_FINAL.toString }/off-payroll-response-schema-strict.json"),
    DecisionServiceVersion.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION140_FINAL.toString }/off-payroll-response-schema-strict.json"),
    DecisionServiceVersion.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION150_FINAL.toString }/off-payroll-response-schema-strict.json")
  )
  def apply(version: DecisionServiceVersion.Value):Option[JsonSchemaValidator] = jsonResponseValidators.get(version)
}

object JsonRequestStrictValidatorFactory {
  lazy val jsonRequestValidators = Map(
    DecisionServiceVersion.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION110_FINAL.toString }/off-payroll-request-schema-strict.json"),
    DecisionServiceVersion.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION111_FINAL.toString }/off-payroll-request-schema-strict.json"),
    DecisionServiceVersion.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION120_FINAL.toString }/off-payroll-request-schema-strict.json"),
    DecisionServiceVersion.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION130_FINAL.toString }/off-payroll-request-schema-strict.json"),
    DecisionServiceVersion.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION140_FINAL.toString }/off-payroll-request-schema-strict.json"),
    DecisionServiceVersion.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION150_FINAL.toString }/off-payroll-request-schema-strict.json")
  )
  def apply(version: DecisionServiceVersion.Value):Option[JsonSchemaValidator] = jsonRequestValidators.get(version)
}

object JsonResponseValidatorFactory {
  lazy val jsonResponseValidators = Map(
    DecisionServiceVersion.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION110_FINAL.toString }/off-payroll-response-schema.json"),
    DecisionServiceVersion.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION111_FINAL.toString }/off-payroll-response-schema.json"),
    DecisionServiceVersion.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION120_FINAL.toString }/off-payroll-response-schema.json"),
    DecisionServiceVersion.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION130_FINAL.toString }/off-payroll-response-schema.json"),
    DecisionServiceVersion.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION140_FINAL.toString }/off-payroll-response-schema.json"),
    DecisionServiceVersion.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION150_FINAL.toString }/off-payroll-response-schema.json")
  )
  def apply(version: DecisionServiceVersion.Value):Option[JsonSchemaValidator] = jsonResponseValidators.get(version)
}

object JsonRequestValidatorFactory {
  lazy val jsonRequestValidators = Map(
    DecisionServiceVersion.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION110_FINAL.toString }/off-payroll-request-schema.json"),
    DecisionServiceVersion.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION111_FINAL.toString }/off-payroll-request-schema.json"),
    DecisionServiceVersion.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION120_FINAL.toString }/off-payroll-request-schema.json"),
    DecisionServiceVersion.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION130_FINAL.toString }/off-payroll-request-schema.json"),
    DecisionServiceVersion.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION140_FINAL.toString }/off-payroll-request-schema.json"),
    DecisionServiceVersion.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${DecisionServiceVersion.VERSION150_FINAL.toString }/off-payroll-request-schema.json")
  )
  def apply(version: DecisionServiceVersion.Value):Option[JsonSchemaValidator] = jsonRequestValidators.get(version)
}

object JsonAnalyticsRequestValidatorFactory{
  lazy val jsonRequestValidators = Map(
    AnalyticsVersion.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/off-payroll-analytics-request-schema.json")
  )
  def apply(version: AnalyticsVersion.Value):Option[JsonSchemaValidator] = jsonRequestValidators.get(version)
}

object JsonAnalyticsSearchRequestValidatorFactory{
  lazy val jsonRequestValidators = Map(
    AnalyticsVersion.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/off-payroll-analytics-search-request-schema.json")
  )
  def apply(version: AnalyticsVersion.Value):Option[JsonSchemaValidator] = jsonRequestValidators.get(version)
}
