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

package uk.gov.hmrc.decisionservice.util


import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.core.report.ProcessingReport
import com.github.fge.jsonschema.main.JsonSchemaFactory
import uk.gov.hmrc.decisionservice.Versions
import cats.implicits._


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
    Versions.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION110_FINAL}/off-payroll-response-schema-strict.json"),
    Versions.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION111_FINAL}/off-payroll-response-schema-strict.json"),
    Versions.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION120_FINAL}/off-payroll-response-schema-strict.json"),
    Versions.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION130_FINAL}/off-payroll-response-schema-strict.json"),
    Versions.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION140_FINAL}/off-payroll-response-schema-strict.json"),
    Versions.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION150_FINAL}/off-payroll-response-schema-strict.json")
  )
  def apply(version:String):Option[JsonSchemaValidator] = jsonResponseValidators.get(version)
}

object JsonRequestStrictValidatorFactory {
  lazy val jsonRequestValidators = Map(
    Versions.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION110_FINAL}/off-payroll-request-schema-strict.json"),
    Versions.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION111_FINAL}/off-payroll-request-schema-strict.json"),
    Versions.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION120_FINAL}/off-payroll-request-schema-strict.json"),
    Versions.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION130_FINAL}/off-payroll-request-schema-strict.json"),
    Versions.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION140_FINAL}/off-payroll-request-schema-strict.json"),
    Versions.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION150_FINAL}/off-payroll-request-schema-strict.json")
  )
  def apply(version:String):Option[JsonSchemaValidator] = jsonRequestValidators.get(version)
}

object JsonResponseValidatorFactory {
  lazy val jsonResponseValidators = Map(
    Versions.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION110_FINAL}/off-payroll-response-schema.json"),
    Versions.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION111_FINAL}/off-payroll-response-schema.json"),
    Versions.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION120_FINAL}/off-payroll-response-schema.json"),
    Versions.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION130_FINAL}/off-payroll-response-schema.json"),
    Versions.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION140_FINAL}/off-payroll-response-schema.json"),
    Versions.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION150_FINAL}/off-payroll-response-schema.json")
  )
  def apply(version:String):Option[JsonSchemaValidator] = jsonResponseValidators.get(version)
}

object JsonRequestValidatorFactory {
  lazy val jsonRequestValidators = Map(
    Versions.VERSION110_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION110_FINAL}/off-payroll-request-schema.json"),
    Versions.VERSION111_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION111_FINAL}/off-payroll-request-schema.json"),
    Versions.VERSION120_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION120_FINAL}/off-payroll-request-schema.json"),
    Versions.VERSION130_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION130_FINAL}/off-payroll-request-schema.json"),
    Versions.VERSION140_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION140_FINAL}/off-payroll-request-schema.json"),
    Versions.VERSION150_FINAL -> JsonSchemaValidator(s"/schema/${Versions.VERSION150_FINAL}/off-payroll-request-schema.json")
  )
  def apply(version:String):Option[JsonSchemaValidator] = jsonRequestValidators.get(version)
}
