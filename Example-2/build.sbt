name := "Example-2"

version := "1.0"

scalaVersion := "2.11.8"


val akkaVersion = "2.4.11"

val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion withSources() withJavadoc(),
  "com.typesafe.akka" %% "akka-stream" % akkaVersion withSources() withJavadoc(),
  "com.typesafe.akka" %% "akka-agent" % akkaVersion withSources() withJavadoc()
)


libraryDependencies ++= akkaDependencies