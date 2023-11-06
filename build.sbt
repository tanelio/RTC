ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.h2database" % "h2" % "2.1.214",
  "org.slf4j" % "slf4j-api" %  "2.0.5",
  "org.slf4j" % "slf4j-simple" % "2.0.5",
  "com.typesafe.akka" %% "akka-remote" % "2.8.0",
  "com.typesafe.akka" %% "akka-actor" % "2.8.0",
  "com.typesafe.akka" %% "akka-cluster" % "2.8.0",
  "com.google.guava" % "guava" % "23.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "org.scala-lang" % "scala-compiler" % "2.13.10"
)

libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.1.0"

libraryDependencies +=  "com.mohiva" %% "play-html-compressor" % "0.7.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
  .settings(
    name := "RTC"
  )
