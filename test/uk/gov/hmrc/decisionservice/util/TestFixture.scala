/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.util

import play.api.mvc._
import play.api.test.Helpers
import com.codahale.metrics.SharedMetricRegistries

trait TestFixture {

  SharedMetricRegistries.clear()

  def stubMessagesControllerComponents(): MessagesControllerComponents = {
    val stub = Helpers.stubControllerComponents()
    new DefaultMessagesControllerComponents(
      new DefaultMessagesActionBuilderImpl(Helpers.stubBodyParser(AnyContentAsEmpty), stub.messagesApi)(stub.executionContext),
      DefaultActionBuilder(stub.actionBuilder.parser)(stub.executionContext), stub.parsers,
      stub.messagesApi, stub.langs, stub.fileMimeTypes, stub.executionContext
    )
  }

}
