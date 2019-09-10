import sbt._

object MicroServiceBuild extends Build with MicroService {

  val appName = "cest-decision"

  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {
  import play.core.PlayVersion
  import play.sbt.PlayImport._


  private val hmrcTestVersion = "3.9.0-play-26"
  private val scalaTestVersion = "3.0.7"
  private val pegdownVersion = "1.6.0"
  private val scalaTestPlayPlusVersion = "3.1.2"

  val compile = Seq(
    "uk.gov.hmrc" %% "simple-reactivemongo" % "7.20.0-play-26",
    "uk.gov.hmrc" %% "http-caching-client" % "8.5.0-play-26",
    "uk.gov.hmrc" %% "bootstrap-play-26" % "1.0.0",
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
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayPlusVersion % scope,
        "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % scope
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

  def tmpMacWorkaround(): Seq[ModuleID] =
    if (sys.props.get("os.name").exists(_.toLowerCase.contains("mac")))
      Seq("org.reactivemongo" % "reactivemongo-shaded-native" % "0.17.1-osx-x86-64" % "runtime,test,it")
    else Seq()

  def apply() = compile ++ Test() ++ IntegrationTest() ++ tmpMacWorkaround
}

