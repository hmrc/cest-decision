import sbt._

object MicroServiceBuild extends Build with MicroService {

  val appName = "cest-decision"

  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {
  import play.core.PlayVersion
  import play.sbt.PlayImport._


  private val hmrcTestVersion = "3.6.0-play-26"
  private val scalaTestVersion = "3.0.7"
  private val pegdownVersion = "1.6.0"
  private val scalaTestPlayPlusVersion = "3.1.2"

  val compile = Seq(
    "uk.gov.hmrc" %% "simple-reactivemongo" % "7.16.0-play-26",
    "uk.gov.hmrc" %% "http-caching-client" % "8.2.0",
    "org.reactivemongo" %% "reactivemongo-akkastream" % "0.16.5",
    "ai.x" %% "play-json-extensions" % "0.30.1",
    "uk.gov.hmrc" %% "bootstrap-play-26" % "0.38.0",
    "uk.gov.hmrc" %% "domain" % "5.3.0",
    "org.typelevel" %% "cats" % "0.9.0",
    "com.github.fge" % "json-schema-validator" % "2.2.6",
    ws
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
        "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
        "org.pegdown" % "pegdown" % pegdownVersion % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "uk.gov.hmrc" %% "play-reactivemongo" % "6.5.0" % scope,
        "uk.gov.hmrc" %% "http-caching-client" % "8.2.0" % scope,
        "org.mockito" % "mockito-core" % "2.26.0" % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayPlusVersion % scope
      )
    }.test
  }

  object IntegrationTest {
    def apply() = new TestDependencies {

      override lazy val scope: String = "it"

      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
        "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
        "org.pegdown" % "pegdown" % pegdownVersion % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayPlusVersion % scope,
        "com.github.tomakehurst" % "wiremock-standalone" % "2.22.0" % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}

