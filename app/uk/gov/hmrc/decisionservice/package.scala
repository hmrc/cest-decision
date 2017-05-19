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

package uk.gov.hmrc

import cats.data.Validated
import uk.gov.hmrc.decisionservice.model.DecisionServiceError

package object decisionservice {

  type Validation[T] = Validated[List[DecisionServiceError],T]

  object Versions {
    val VERSION110_FINAL = "1.1.0-final"
    val VERSION111_FINAL = "1.1.1-final"
    val VERSION120_FINAL = "1.2.0-final"
    val VERSION130_FINAL = "1.3.0-final"
    val VERSION140_FINAL = "1.4.0-final"
  }

}
