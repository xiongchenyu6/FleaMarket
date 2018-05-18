name := """FleaMarket"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)
scalaVersion := "2.12.5"
crossScalaVersions := Seq("2.11.12", "2.12.5")

libraryDependencies ++= Seq(
  guice,
evolutions,
  jdbc,
"com.h2database" % "h2" % "1.4.197"
)
