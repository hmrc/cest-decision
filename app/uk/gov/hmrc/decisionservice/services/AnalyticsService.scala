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

import javax.inject.Inject

import org.joda.time.DateTime
import play.api.{Configuration, Logger}
import uk.gov.hmrc.decisionservice.model.analytics.AnalyticsSearch
import uk.gov.hmrc.decisionservice.repository.InterviewRepository

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class AnalyticsService @Inject() (repo: InterviewRepository,
                                  configuration:Configuration) {

  private val CURRENT_DATE = DateTime.now()
  private val INSIDE_IR35 = "Inside IR35"
  private val OUTSIDE_IR35 = "Outside IR35"
  private val UNKNOWN = "Unknown"

  private val gatherAnalytics: Boolean = configuration.underlying.getBoolean("analytics.gatherAnalytics")

  if (gatherAnalytics) {
    val months = List.range(0, configuration.underlying.getInt("analytics.reportingPeriod"))

    months.foreach(mon => {
      val currentMonth = reportingDates(mon)
      repo.count(AnalyticsSearch(currentMonth._1, currentMonth._2, INSIDE_IR35)).map { count =>
        Logger.warn(s"number of interviews during ${currentMonth._3} $INSIDE_IR35 is $count ")
      }
      repo.count(AnalyticsSearch(currentMonth._1, currentMonth._2, OUTSIDE_IR35)).map { count =>
        Logger.warn(s"number of interviews during ${currentMonth._3} $OUTSIDE_IR35 is $count ")
      }
      repo.count(AnalyticsSearch(currentMonth._1, currentMonth._2, UNKNOWN)).map { count =>
        Logger.warn(s"number of interviews during ${currentMonth._3} $UNKNOWN is $count ")
      }
    })
  }

  def reportingDates(offset: Int): (DateTime, DateTime, String) = {
    val offsetDate: DateTime = CURRENT_DATE.minusMonths(offset)
    val currentYear: Int = offsetDate.getYear
    val currentMonth: Int = offsetDate.getMonthOfYear
    val lastDayOfMonth: Int = offsetDate.dayOfMonth().getMaximumValue
    val lastDateOfMonth: DateTime = new DateTime(currentYear, currentMonth, lastDayOfMonth, 23, 59, 59)
    val firstDateOfMonth: DateTime = new DateTime(currentYear, currentMonth, 1, 0, 0, 0)

    (firstDateOfMonth, lastDateOfMonth, s"${offsetDate.monthOfYear().getAsText} - ${currentYear}")
  }

}