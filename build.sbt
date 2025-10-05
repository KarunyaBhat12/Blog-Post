name := "blogapp"

organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.16"

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "5.1.0",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
)

libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "always"
