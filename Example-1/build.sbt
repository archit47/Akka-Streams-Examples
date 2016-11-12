name := "Example-1"

version := "1.0"

scalaVersion := "2.11.8"


val akkaV       = "2.4.11"

val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV withSources() withJavadoc(),
  "com.typesafe.akka" %% "akka-stream" % akkaV withSources() withJavadoc(),
  "com.typesafe.akka" %% "akka-agent" % akkaV withSources() withJavadoc()
)


libraryDependencies ++= akkaDependencies