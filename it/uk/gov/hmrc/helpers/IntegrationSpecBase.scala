package uk.gov.hmrc.helpers

import org.scalatest._
import org.scalatest.concurrent.{Eventually, IntegrationPatience, ScalaFutures}
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.modules.reactivemongo.ReactiveMongoComponent
import uk.gov.hmrc.decisionservice.repository.InterviewRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import scala.concurrent.Await

trait IntegrationSpecBase extends WordSpec
  with GivenWhenThen with TestSuite with ScalaFutures with IntegrationPatience with Matchers
  with WiremockHelper
  with GuiceOneServerPerSuite
  with BeforeAndAfterEach with BeforeAndAfterAll with Eventually {

  override implicit lazy val app: Application = new GuiceApplicationBuilder()
    .build()

  lazy val component: ReactiveMongoComponent = app.injector.instanceOf(classOf[ReactiveMongoComponent])

  val mongo = new InterviewRepository(component)

  override def beforeEach(): Unit = {
    resetWiremock()
    Await.result(mongo().drop, 10.seconds)
  }

  override def beforeAll(): Unit = {
    super.beforeAll()
    startWiremock()
  }

  override def afterAll(): Unit = {
    stopWiremock()
    Await.result(mongo().drop, 10.seconds)
    super.afterAll()
  }

}
