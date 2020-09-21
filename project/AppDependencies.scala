import sbt._
import play.core.PlayVersion
import play.sbt.PlayImport._

object AppDependencies {
  
  private val pegdownVersion = "1.6.0"
  private val scalaTestPlayPlusVersion = "4.0.3"

  val compile = Seq(
    "uk.gov.hmrc" %% "http-caching-client" % "9.1.0-play-27",
    "uk.gov.hmrc" %% "bootstrap-backend-play-27" % "2.25.0",
    "com.typesafe.play" %% "play-json-joda" % "2.7.4",
    ws,
    compilerPlugin("com.github.ghik" % "silencer-plugin" % "1.7.1" cross CrossVersion.full),
    "com.github.ghik" % "silencer-lib" % "1.7.1" % Provided cross CrossVersion.full
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "org.pegdown" % "pegdown" % pegdownVersion % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "org.mockito" % "mockito-core" % "3.3.3" % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayPlusVersion % scope,
        "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % scope
      )
    }.test
  }

  object IntegrationTest {
    def apply() = new TestDependencies {

      override lazy val scope: String = "it"

      override lazy val test = Seq(
        "org.pegdown" % "pegdown" % pegdownVersion % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayPlusVersion % scope,
        "com.github.tomakehurst" % "wiremock-standalone" % "2.27.2" % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}
