name := """book-api"""
organization := "github.guisofiati"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

PlayKeys.devSettings += "play.server.http.port" -> "8080"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test,
  "com.h2database" % "h2" % "1.4.192",
  "org.playframework.anorm" %% "anorm" % "2.7.0"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "github.guisofiati.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "github.guisofiati.binders._"
