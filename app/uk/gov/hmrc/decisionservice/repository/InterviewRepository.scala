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

package uk.gov.hmrc.decisionservice.repository

import javax.inject.{Inject, Singleton}

import org.joda.time.DateTime
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.commands.bson.BSONCountCommand.{Count, CountResult}
import reactivemongo.bson.{BSONDateTime, BSONDocument, BSONHandler}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import uk.gov.hmrc.decisionservice.model.analytics._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by work on 20/06/2017.
  */
@Singleton
class InterviewRepository @Inject()(val mongo: ReactiveMongoApi)(implicit ec:ExecutionContext) {

  implicit val sFormat = Json.format[Setup]
  implicit val eFormat = Json.format[Exit]
  implicit val psFormat = Json.format[PersonalService]
  implicit val cFormat = Json.format[Control]
  implicit val frFormat = Json.format[FinancialRisk]
  implicit val ppFormat = Json.format[PartAndParcel]
  implicit val iFormat = Json.format[Interview]

  implicit val isFormat = Json.format[InterviewSearch]

  val repository: Future[JSONCollection] =
    mongo.database.map(_.collection[JSONCollection]("Off-Payroll-Interview"))

  def save(i:Interview) : Future[WriteResult] = repository.flatMap(_.insert(i))

  def get(search: InterviewSearch): Future[List[Interview]] = {
    val query = BSONDocument("completed" ->
      BSONDocument("$gte" -> search.start.getMillis, "$lt" -> search.end.getMillis))
    repository.flatMap(_.find(query).cursor[Interview].collect[List]())
  }

  def count(search: AnalyticsSearch): Future[Int] = {
    val query = Json.obj("decision" -> search.decision,  "completed" ->
      Json.obj("$gte" -> search.start.getMillis, "$lt" -> search.end.getMillis))
    repository.flatMap(_.count(Some(query)))
  }

  implicit object BSONDateTimeHandler extends BSONHandler[BSONDateTime, DateTime] {
    def read(time: BSONDateTime) = new DateTime(time.value)
    def write(time: DateTime) = BSONDateTime(time.getMillis)
  }

}