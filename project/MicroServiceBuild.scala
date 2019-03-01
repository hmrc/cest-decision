import sbt._

object MicroServiceBuild extends Build with MicroService {

  val appName = "off-payroll-decision"

  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {
  import play.core.PlayVersion
  import play.sbt.PlayImport._


  private val hmrcTestVersion = "3.5.0-play-25"
  private val scalaTestVersion = "3.0.3"
  private val pegdownVersion = "1.6.0"

  val compile = Seq(
    "uk.gov.hmrc" %% "simple-reactivemongo" % "7.12.0-play-25",
    "uk.gov.hmrc" %% "http-caching-client" % "8.1.0",
    "org.reactivemongo" %% "reactivemongo-akkastream" % "0.12.0",
    "ai.x" %% "play-json-extensions" % "0.8.0",
    "uk.gov.hmrc" %% "bootstrap-play-25" % "4.9.0",
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
        "uk.gov.hmrc" %% "play-reactivemongo" % "6.2.0" % scope,
        "uk.gov.hmrc" %% "http-caching-client" % "8.0.0" % scope,
        "org.mockito" % "mockito-core" % "2.23.0" % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % scope
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
        "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}

