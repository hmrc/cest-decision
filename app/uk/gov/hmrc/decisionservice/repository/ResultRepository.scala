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

package uk.gov.hmrc.decisionservice.repository

import javax.inject.{Inject, Singleton}

import org.joda.time.DateTime
import play.api.Logger
import play.modules.reactivemongo.ReactiveMongoComponent
import reactivemongo.api.DefaultDB
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson._
import uk.gov.hmrc.decisionservice.models.{AnalyticsSearch, LogResult}
import uk.gov.hmrc.mongo.ReactiveRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ReactiveMongoRepositoryResult(mongo: () => DefaultDB)
  extends ReactiveRepository[DatedCacheMap, BSONObjectID]("Different-Result-Records", mongo, DatedCacheMap.formats) {

  def save(i: LogResult) : Future[WriteResult] = collection.insert(i)

  def count(search: AnalyticsSearch): Future[Int] = {
    collection.count().map { numberOfDifferentResults =>
      Logger.info(s"[ResultRepository][Different Results] $numberOfDifferentResults")
      numberOfDifferentResults
    }
  }

  implicit object BSONDateTimeHandler extends BSONHandler[BSONDateTime, DateTime] {
    def read(time: BSONDateTime) = new DateTime(time.value)
    def write(time: DateTime) = BSONDateTime(time.getMillis)
  }
}

@Singleton
class ResultRepository @Inject()(mongo: ReactiveMongoComponent) {

  private lazy val resultRepository = new ReactiveMongoRepositoryResult(mongo.mongoConnector.db)

  def apply(): ReactiveMongoRepositoryResult = resultRepository
}
