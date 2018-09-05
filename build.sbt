name := """FleaMarket"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)
scalaVersion := "2.12.5"
crossScalaVersions := Seq("2.11.12", "2.12.5")

libraryDependencies ++= Seq(
  guice,
  evolutions,
  jdbc,
  "mysql" % "mysql-connector-java" % "6.0.6",
  "org.webjars" %% "webjars-play" % "2.6.2",
  "com.adrianhurt" %% "play-bootstrap" % "1.2-P26-B4",
  "org.webjars" % "bootstrap" % "4.0.0",
  "org.webjars" % "jspdf" % "1.3.5",
  "org.webjars.npm" % "html2canvas" % "1.0.0-alpha.9"
)
