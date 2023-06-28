import sbt._
import play.sbt.PlayImport._

object AppDependencies {
  
  val compile = Seq(
    "uk.gov.hmrc"       %% "http-caching-client"       % "10.0.0-play-28",
    "uk.gov.hmrc"       %% "bootstrap-backend-play-28" % "7.16.0",
    "com.typesafe.play" %% "play-json-joda"            % "2.9.4",
    ws
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
      "uk.gov.hmrc"            %% "bootstrap-test-play-28"      % "7.16.0"                 % scope,
      "org.mockito"            %  "mockito-core"                % "5.3.1"                  % scope,
      "org.mockito"            %% "mockito-scala-scalatest"     % "1.17.14"                % scope,
      "org.scalamock"          %% "scalamock"                   % "5.2.0"                  % scope
      )
    }.test
  }

  object IntegrationTest {
    def apply() = new TestDependencies {

      override lazy val scope: String = "it"

      override lazy val test = Seq(
      "uk.gov.hmrc"            %% "bootstrap-test-play-28" % "7.16.0"                 % scope,
      "com.github.tomakehurst" %  "wiremock-standalone"    % "2.27.2"                 % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}
