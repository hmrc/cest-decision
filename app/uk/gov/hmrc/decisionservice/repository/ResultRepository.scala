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
import reactivemongo.api.indexes.{Index, IndexType}
import reactivemongo.bson._
import uk.gov.hmrc.decisionservice.config.AppConfig
import uk.gov.hmrc.decisionservice.models.{AnalyticsSearch, LogResult}
import uk.gov.hmrc.mongo.ReactiveRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ReactiveMongoRepositoryResult(mongo: () => DefaultDB, appConfig: AppConfig)
  extends ReactiveRepository[DatedCacheMap, BSONObjectID]("Different-Result-Records", mongo, DatedCacheMap.formats) {

  val fieldName = "completed"
  val createdIndexName = "differentResultExpiry"
  val expireAfterSeconds = "expireAfterSeconds"
  val timeToLiveInSeconds: Int = appConfig.mongoTtl

  private def createIndex(field: String, indexName: String, ttl: Int): Future[Boolean] = {
    collection.indexesManager.ensure(Index(Seq((field, IndexType.Ascending)), Some(indexName),
      options = BSONDocument(expireAfterSeconds -> ttl))) map {
      result => {
        Logger.debug(s"set [$indexName] with value $ttl -> result : $result")
        result
      }
    } recover {
      case e => Logger.error("Failed to set TTL index", e)
        false
    }
  }

  createIndex(fieldName, createdIndexName, timeToLiveInSeconds)

  def save(i: LogResult) : Future[WriteResult] = collection.insert(i)

  implicit object BSONDateTimeHandler extends BSONHandler[BSONDateTime, DateTime] {
    def read(time: BSONDateTime) = new DateTime(time.value)
    def write(time: DateTime) = BSONDateTime(time.getMillis)
  }
}

@Singleton
class ResultRepository @Inject()(mongo: ReactiveMongoComponent, appConfig: AppConfig) {

  private lazy val resultRepository = new ReactiveMongoRepositoryResult(mongo.mongoConnector.db,appConfig)

  def apply(): ReactiveMongoRepositoryResult = resultRepository
}
