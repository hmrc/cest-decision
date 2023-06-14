import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

val appName = "cest-decision"

val appDependencies: Seq[ModuleID] = AppDependencies()

lazy val plugins : Seq[Plugins] = Seq(
  play.sbt.PlayScala,
  SbtDistributablesPlugin)

lazy val playSettings : Seq[Setting[_]] = Seq.empty

lazy val scoverageSettings = {
  import scoverage._
  Seq(
    // Semicolon-separated list of regexs matching classes to exclude
    ScoverageKeys.coverageExcludedPackages := "<empty>;Reverse.*;config.*;.*(AuthService|BuildInfo|Routes).*",
    ScoverageKeys.coverageMinimumStmtTotal := 80,
    ScoverageKeys.coverageFailOnMinimum := false,
    ScoverageKeys.coverageHighlighting := true,
    Test / parallelExecution := false
  )
}

lazy val microservice = Project(appName, file("."))
  .enablePlugins(Seq(play.sbt.PlayScala) ++ plugins: _*)
  .settings(playSettings ++ scoverageSettings : _*)
  .settings(majorVersion := 1)
  .settings(scalaSettings: _*)
  .settings(defaultSettings(): _*)
  .settings(SbtAutoBuildPlugin.forceLicenceHeader := true)
  .configs(IntegrationTest)
  .settings(
    scalacOptions ++= Seq("-feature"),
    addTestReportOption(IntegrationTest, "int-test-reports"),
    inConfig(IntegrationTest)(Defaults.itSettings),
    scalaVersion := "2.13.8",
    scalacOptions += "-Wconf:src=routes/.*:s",
    scalacOptions += "-Wconf:cat=unused-imports&src=html/.*:s",
    targetJvm := "jvm-1.8",
    libraryDependencies ++= appDependencies,
    Test / parallelExecution := false,
    Test / fork := true,
    retrieveManaged := true,
    routesGenerator := InjectedRoutesGenerator,
    IntegrationTest / Keys.fork :=  false,
    IntegrationTest / unmanagedSourceDirectories := (IntegrationTest / baseDirectory)(base => Seq(base / "it")).value,
    IntegrationTest / parallelExecution := false
)

  .settings(
      resolvers += Resolver.typesafeRepo("releases"),
      resolvers += Resolver.jcenterRepo
  )
  .disablePlugins(JUnitXmlReportPlugin)