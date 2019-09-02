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


import cats.syntax.either._
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion
import uk.gov.hmrc.play.test.UnitSpec

class JsonSchemaSpec extends UnitSpec {

  " A Json Schema" should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersion.VERSION110_FINAL.toString}" in {
      validateRequestWithSchema(DecisionServiceVersion.VERSION110_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersion.VERSION111_FINAL.toString}" in {
      validateRequestWithSchema(DecisionServiceVersion.VERSION111_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersion.VERSION120_FINAL.toString}" in {
      validateRequestWithSchema(DecisionServiceVersion.VERSION120_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersion.VERSION130_FINAL.toString}" in {
      validateRequestWithSchema(DecisionServiceVersion.VERSION130_FINAL)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${DecisionServiceVersion.VERSION140_FINAL.toString}" in {
      validateRequestWithSchema(DecisionServiceVersion.VERSION140_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersion.VERSION110_FINAL.toString}" in {
      validateRequestWithStrictSchema(DecisionServiceVersion.VERSION110_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersion.VERSION111_FINAL.toString}" in {
      validateRequestWithStrictSchema(DecisionServiceVersion.VERSION111_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersion.VERSION120_FINAL.toString}" in {
      validateRequestWithStrictSchema(DecisionServiceVersion.VERSION120_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersion.VERSION130_FINAL.toString}" in {
      validateRequestWithStrictSchema(DecisionServiceVersion.VERSION130_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${DecisionServiceVersion.VERSION140_FINAL.toString}" in {
      validateRequestWithStrictSchema(DecisionServiceVersion.VERSION140_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersion.VERSION110_FINAL.toString}" in {
      validateResponseWithSchema(DecisionServiceVersion.VERSION110_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersion.VERSION111_FINAL.toString}" in {
      validateResponseWithSchema(DecisionServiceVersion.VERSION111_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersion.VERSION120_FINAL.toString}" in {
      validateResponseWithSchema(DecisionServiceVersion.VERSION120_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersion.VERSION130_FINAL.toString}" in {
      validateResponseWithSchema(DecisionServiceVersion.VERSION130_FINAL)
    }
  }

  it should {
    s"validate a full response with the loose schema for version ${DecisionServiceVersion.VERSION140_FINAL.toString}" in {
      validateResponseWithSchema(DecisionServiceVersion.VERSION140_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersion.VERSION110_FINAL.toString}" in {
      validateResponseWithStrictSchema(DecisionServiceVersion.VERSION110_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersion.VERSION111_FINAL.toString}" in {
      validateResponseWithStrictSchema(DecisionServiceVersion.VERSION111_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersion.VERSION120_FINAL.toString}" in {
      validateResponseWithStrictSchema(DecisionServiceVersion.VERSION120_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersion.VERSION130_FINAL.toString}" in {
      validateResponseWithStrictSchema(DecisionServiceVersion.VERSION130_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${DecisionServiceVersion.VERSION140_FINAL.toString}" in {
      validateResponseWithStrictSchema(DecisionServiceVersion.VERSION140_FINAL)
    }
  }

  private def readExampleRequestJson(version: DecisionServiceVersion.Value): String = {
    val exampleRequestPath = s"/schema/${version}/off-payroll-request-sample.json"
    val tryJson = FileReader.read(exampleRequestPath)
    tryJson.isSuccess shouldBe true
    tryJson.get
  }

  private def readExampleResponseJson(version: DecisionServiceVersion.Value): String = {
    val exampleResponsePath = s"/schema/${version}/off-payroll-response-sample.json"
    val tryJson = FileReader.read(exampleResponsePath)
    tryJson.isSuccess shouldBe true
    tryJson.get
  }

  private def printValidationResult(result: Either[String, Unit]) = {
    result.leftMap(report => {
      info(report)})
    }



  def validateRequestWithSchema(version: DecisionServiceVersion.Value): Unit = validateRequestWithSchema(version, JsonRequestValidatorFactory(version))

  def validateRequestWithStrictSchema(version: DecisionServiceVersion.Value): Unit = validateRequestWithSchema(version, JsonRequestStrictValidatorFactory(version))

  def validateRequestWithSchema(version: DecisionServiceVersion.Value, maybeValidator: Option[JsonSchemaValidator]): Unit = {
    val requestJsonString = readExampleRequestJson(version)
    maybeValidator.isDefined shouldBe true
    maybeValidator.map { validator =>
      val validationResult = validator.validate(requestJsonString)
      printValidationResult(validationResult)
      validationResult.isRight shouldBe true
    }
  }

  def validateResponseWithSchema(version: DecisionServiceVersion.Value): Unit = validateResponseWithSchema(version, JsonResponseValidatorFactory(version))

  def validateResponseWithStrictSchema(version: DecisionServiceVersion.Value): Unit = validateResponseWithSchema(version, JsonResponseStrictValidatorFactory(version))

  def validateResponseWithSchema(version: DecisionServiceVersion.Value, maybeValidator: Option[JsonSchemaValidator]): Unit = {
    val requestJsonString = readExampleResponseJson(version)
    maybeValidator.isDefined shouldBe true
    val validationResult = maybeValidator.get.validate(requestJsonString)
    printValidationResult(validationResult)
    validationResult.isRight shouldBe true
  }

}
