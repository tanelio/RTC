ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  //  "com.typesafe.slick" %% "slick" % "3.2.0-M1",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  //  "org.slf4j" % "slf4j-nop" % "1.6.4",
 //
  //
  // "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.akka" %% "akka-remote" % "2.7.0",
  "com.typesafe.akka" %% "akka-actor" % "2.7.0",
  "com.typesafe.akka" %% "akka-cluster" % "2.7.0",
  "com.google.guava" % "guava" % "23.0",


  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.7.0",
  "org.scala-lang" % "scala-compiler" % "2.13.10"
)



lazy val root = (project in file("."))
  .settings(
    name := "RTC"
  )
