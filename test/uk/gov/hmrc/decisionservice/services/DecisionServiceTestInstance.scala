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

package uk.gov.hmrc.decisionservice.services

import uk.gov.hmrc.decisionservice.ruleengine.RulesFileMetaData

object DecisionServiceTestInstance110final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  val csvSectionMetadata = List(
    (4, "/tables/1.1.0-final/control.csv", "control"),
    (7, "/tables/1.1.0-final/financial-risk.csv", "financialRisk"),
    (4, "/tables/1.1.0-final/part-and-parcel.csv", "partAndParcel"),
    (5, "/tables/1.1.0-final/personal-service.csv", "personalService"),
    (4, "/tables/1.1.0-final/matrix-of-matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}

object DecisionServiceTestInstance111final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  val version = "1.1.1-final"
  val csvSectionMetadata = List(
    (4, s"/tables/$version/control.csv", "control"),
    (7, s"/tables/$version/financial-risk.csv", "financialRisk"),
    (4, s"/tables/$version/part-and-parcel.csv", "partAndParcel"),
    (5, s"/tables/$version/personal-service.csv", "personalService"),
    (4, s"/tables/$version/matrix-of-matrices.csv", "matrix")
  ).collect { case (q, f, n) => RulesFileMetaData(q, f, n) }
}

object DecisionServiceTestInstance120final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  val version = "1.2.0-final"
  val csvSectionMetadata = List(
    (4, s"/tables/$version/control.csv", "control"),
    (7, s"/tables/$version/financial-risk.csv", "financialRisk"),
    (4, s"/tables/$version/part-and-parcel.csv", "partAndParcel"),
    (5, s"/tables/$version/personal-service.csv", "personalService"),
    (4, s"/tables/$version/matrix-of-matrices.csv", "matrix")
  ).collect { case (q, f, n) => RulesFileMetaData(q, f, n) }
}

object DecisionServiceTestInstance130final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  val version = "1.3.0-final"
  val csvSectionMetadata = List(
    (4, s"/tables/$version/control.csv", "control"),
    (7, s"/tables/$version/financial-risk.csv", "financialRisk"),
    (4, s"/tables/$version/part-and-parcel.csv", "partAndParcel"),
    (5, s"/tables/$version/personal-service.csv", "personalService"),
    (4, s"/tables/$version/matrix-of-matrices.csv", "matrix")
  ).collect { case (q, f, n) => RulesFileMetaData(q, f, n) }
}

object DecisionServiceTestInstance140final extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  val version = "1.4.0-final"
  val csvSectionMetadata = List(
    (3, s"/tables/$version/setup.csv", "setup"),
    (1, s"/tables/$version/exit.csv", "exit"),
    (4, s"/tables/$version/control.csv", "control"),
    (7, s"/tables/$version/financial-risk.csv", "financialRisk"),
    (4, s"/tables/$version/part-and-parcel.csv", "partAndParcel"),
    (5, s"/tables/$version/personal-service.csv", "personalService"),
    (4, s"/tables/$version/matrix-of-matrices.csv", "matrix")
  ).collect { case (q, f, n) => RulesFileMetaData(q, f, n) }
}
