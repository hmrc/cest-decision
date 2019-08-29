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


import uk.gov.hmrc.decisionservice.DecisionServiceVersions
import uk.gov.hmrc.play.test.UnitSpec
import cats.syntax.either._

class JsonSchemaSpec extends UnitSpec {

  " A Json Schema" should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      validateRequestWithSchema(DecisionServiceVersions.VERSION110_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      validateRequestWithSchema(DecisionServiceVersions.VERSION111_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      validateRequestWithSchema(DecisionServiceVersions.VERSION120_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      validateRequestWithSchema(DecisionServiceVersions.VERSION130_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      validateRequestWithSchema(DecisionServiceVersions.VERSION140_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      validateRequestWithStrictSchema(DecisionServiceVersions.VERSION110_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      validateRequestWithStrictSchema(DecisionServiceVersions.VERSION111_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      validateRequestWithStrictSchema(DecisionServiceVersions.VERSION120_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      validateRequestWithStrictSchema(DecisionServiceVersions.VERSION130_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      validateRequestWithStrictSchema(DecisionServiceVersions.VERSION140_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      validateResponseWithSchema(DecisionServiceVersions.VERSION110_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      validateResponseWithSchema(DecisionServiceVersions.VERSION111_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      validateResponseWithSchema(DecisionServiceVersions.VERSION120_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      validateResponseWithSchema(DecisionServiceVersions.VERSION130_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      validateResponseWithSchema(DecisionServiceVersions.VERSION140_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersions.VERSION110_FINAL}" in {
      validateResponseWithStrictSchema(DecisionServiceVersions.VERSION110_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersions.VERSION111_FINAL}" in {
      validateResponseWithStrictSchema(DecisionServiceVersions.VERSION111_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersions.VERSION120_FINAL}" in {
      validateResponseWithStrictSchema(DecisionServiceVersions.VERSION120_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersions.VERSION130_FINAL}" in {
      validateResponseWithStrictSchema(DecisionServiceVersions.VERSION130_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersions.VERSION140_FINAL}" in {
      validateResponseWithStrictSchema(DecisionServiceVersions.VERSION140_FINAL)
    }
  }

  private def readExampleRequestJson(version: String): String = {
    val exampleRequestPath = s"/schema/${version}/off-payroll-request-sample.json"
    val tryJson = FileReader.read(exampleRequestPath)
    tryJson.isSuccess shouldBe true
    tryJson.get
  }

  private def readExampleResponseJson(version: String): String = {
    val exampleResponsePath = s"/schema/${version}/off-payroll-response-sample.json"
    val tryJson = FileReader.read(exampleResponsePath)
    tryJson.isSuccess shouldBe true
    tryJson.get
  }

  private def printValidationResult(result: Either[String, Unit]) = {
    result.leftMap(report => {
      info(report)})
    }



  def validateRequestWithSchema(version: String): Unit = validateRequestWithSchema(version, JsonRequestValidatorFactory(version))

  def validateRequestWithStrictSchema(version: String): Unit = validateRequestWithSchema(version, JsonRequestStrictValidatorFactory(version))

  def validateRequestWithSchema(version: String, maybeValidator: Option[JsonSchemaValidator]): Unit = {
    val requestJsonString = readExampleRequestJson(version)
    maybeValidator.isDefined shouldBe true
    maybeValidator.map { validator =>
      val validationResult = validator.validate(requestJsonString)
      printValidationResult(validationResult)
      validationResult.isRight shouldBe true
    }
  }

  def validateResponseWithSchema(version: String): Unit = validateResponseWithSchema(version, JsonResponseValidatorFactory(version))

  def validateResponseWithStrictSchema(version: String): Unit = validateResponseWithSchema(version, JsonResponseStrictValidatorFactory(version))

  def validateResponseWithSchema(version: String, maybeValidator: Option[JsonSchemaValidator]): Unit = {
    val requestJsonString = readExampleResponseJson(version)
    maybeValidator.isDefined shouldBe true
    val validationResult = maybeValidator.get.validate(requestJsonString)
    printValidationResult(validationResult)
    validationResult.isRight shouldBe true
  }

}
