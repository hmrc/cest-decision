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

package uk.gov.hmrc.decisionservice.model.rules

import uk.gov.hmrc.play.test.UnitSpec

class CarryOverSpec extends UnitSpec {

  "carry over object" should {
    "produce correct string when converted to string" in {
      NotValidUseCase.toString shouldBe "NotValidUseCase"
      EmptyCarryOver.toString shouldBe "EmptyCarryOver"
      >>>("abc").toString shouldBe ">>>(abc)"
      >>>("").toString shouldBe ">>>()"
      >>>("abc",false).toString shouldBe ">>>(abc)"
      >>>("abc",true).toString shouldBe ">>>(abc,true)"
      >>>("abc", false, Some("factName")).toString shouldBe ">>>(abc,factName)"
      >>>("abc", true, Some("factName")).toString shouldBe ">>>(abc,true,factName)"
    }
    "produce correct equivalence results" in {
      >>>.equivalent((>>>(""),>>>(""))) shouldBe true
      >>>.equivalent((>>>(""),>>>("a"))) shouldBe false
      >>>.equivalent((>>>("a"),>>>(""))) shouldBe true
    }
    "produce correct empty position sets" in {
      >>>.emptyPositions(Seq(>>>(""),>>>("a"),>>>(""),>>>("a"))) should contain theSameElementsAs(Seq(0,2))
      >>>.emptyPositions(Seq(>>>("a"),>>>(""),>>>(""),>>>(""))) should contain theSameElementsAs(Seq(1,2,3))
      >>>.emptyPositions(Seq(>>>("a"),>>>("",true,Some("abc")),>>>(""),>>>("",true))) should contain theSameElementsAs(Seq(1,2,3))
    }
  }

}
