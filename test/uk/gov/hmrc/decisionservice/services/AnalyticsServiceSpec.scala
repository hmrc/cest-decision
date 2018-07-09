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

package uk.gov.hmrc.decisionservice.services

import org.joda.time.DateTime
import org.mockito.Mockito.{times, verify, when}
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.OneAppPerSuite
import uk.gov.hmrc.play.test.UnitSpec
import play.api.Configuration
import uk.gov.hmrc.decisionservice.model.analytics.AnalyticsSearch
import uk.gov.hmrc.decisionservice.repository.InterviewRepository

import scala.concurrent.Future

class AnalyticsServiceSpec extends UnitSpec with MockitoSugar with OneAppPerSuite{

  private val config = app.injector.instanceOf[Configuration]
  private val INSIDE_IR35 = "Inside IR35"
  private val OUTSIDE_IR35 = "Outside IR35"
  private val UNKNOWN = "Unknown"
  private val CURRENT_DATE = DateTime.now()

  private val currentYear: Int = CURRENT_DATE.getYear
  private val currentMonth: Int = CURRENT_DATE.getMonthOfYear
  private val lastDayOfMonth: Int = CURRENT_DATE.dayOfMonth().getMaximumValue
  private val lastDateOfMonth: DateTime = new DateTime(currentYear, currentMonth, lastDayOfMonth, 23, 59, 59)
  private val firstDateOfMonth: DateTime = new DateTime(currentYear, currentMonth, 1, 0, 0, 0)

  "attempt to gather analytics" when {

    "analytics.enabled set to true AND reportingPeriod set to 2" in {

      val intRepo = mock[InterviewRepository]
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, INSIDE_IR35))) thenReturn Future.successful(2)
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, OUTSIDE_IR35))) thenReturn Future.successful(1)
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, UNKNOWN))) thenReturn Future.successful(1)
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth.minusMonths(1), lastDateOfMonth.minusMonths(1), INSIDE_IR35))) thenReturn Future.successful(2)
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth.minusMonths(1), lastDateOfMonth.minusMonths(1), OUTSIDE_IR35))) thenReturn Future.successful(1)
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth.minusMonths(1), lastDateOfMonth.minusMonths(1), UNKNOWN))) thenReturn Future.successful(1)

      new AnalyticsService(intRepo, config ++ Configuration("analytics.gatherAnalytics" -> "true", "analytics.reportingPeriod" -> 2))

      verify(intRepo, times(1)).count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, INSIDE_IR35))
      verify(intRepo, times(1)).count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, OUTSIDE_IR35))
      verify(intRepo, times(1)).count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, UNKNOWN))
      verify(intRepo, times(1)).count(AnalyticsSearch(firstDateOfMonth.minusMonths(1), lastDateOfMonth.minusMonths(1), INSIDE_IR35))
      verify(intRepo, times(1)).count(AnalyticsSearch(firstDateOfMonth.minusMonths(1), lastDateOfMonth.minusMonths(1), OUTSIDE_IR35))
      verify(intRepo, times(1)).count(AnalyticsSearch(firstDateOfMonth.minusMonths(1), lastDateOfMonth.minusMonths(1), UNKNOWN))
    }
  }

  "not attempt to gather analytics" when {

    "analytics.enabled NOT set to true" in {

      val intRepo = mock[InterviewRepository]
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, INSIDE_IR35))) thenReturn Future.successful(1)
      when(intRepo.count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, OUTSIDE_IR35))) thenReturn Future.successful(1)

      new AnalyticsService(intRepo, config ++ Configuration("analytics.gatherAnalytics" -> false, "analytics.reportingPeriod" -> 1))

      verify(intRepo, times(0)).count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, INSIDE_IR35))
      verify(intRepo, times(0)).count(AnalyticsSearch(firstDateOfMonth, lastDateOfMonth, OUTSIDE_IR35))
    }
  }

}
