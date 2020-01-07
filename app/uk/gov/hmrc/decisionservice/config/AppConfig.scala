/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.config

import javax.inject.Inject
import play.api.Mode
import play.api.{Configuration, Environment}
import uk.gov.hmrc.play.bootstrap.config.ServicesConfig

class AppConfig @Inject()(env: Environment, val runModeConfiguration: Configuration, servicesConfig: ServicesConfig) {

  val mode: Mode = env.mode

}
