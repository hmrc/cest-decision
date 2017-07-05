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

import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection
import uk.gov.hmrc.decisionservice.model.analytics.Interview
import reactivemongo.play.json._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by work on 20/06/2017.
  */
@Singleton
class InterviewRepository @Inject()(val mongo: ReactiveMongoApi)(implicit ec:ExecutionContext) {

  val repository: Future[JSONCollection] =
    mongo.database.map(_.collection[JSONCollection]("Off-Payroll-Interview"))

  def save(i:Interview) : Future[WriteResult] =
    repository.flatMap(_.insert(i))

}
