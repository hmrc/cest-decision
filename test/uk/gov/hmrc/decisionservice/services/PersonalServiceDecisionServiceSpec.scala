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

package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.models.PersonalService
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum
import uk.gov.hmrc.decisionservice.util.PersonalServiceRulesSet
import uk.gov.hmrc.play.test.UnitSpec

class PersonalServiceDecisionServiceSpec extends UnitSpec{

  object TestControlDecisionService extends PersonalServiceDecisionService(new PersonalServiceRulesSet)

  "PersonalServiceDecisionServiceSpec" when {

    "decide is called with a PersonalService section with every value provided" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = Some(WeightedAnswerEnum.OUTSIDE_IR35)
        val actualAnswer = TestControlDecisionService.decide(PersonalService(
          Some("yesClientAgreed"),
          Some(true),
          Some(true),
          Some(true),
          Some(true)
        ))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }

    "decide is called with a PersonalService section with None for every value" should {

      "return a WeightedAnswer" in {

        val expectedAnswer = None
        val actualAnswer = TestControlDecisionService.decide(PersonalService(None, None, None, None, None))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }

}
