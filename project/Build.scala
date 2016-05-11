import sbt.Keys._
import sbt._

object CustomBuild extends Build {

  val NamePrefix = "com.scala.neo4j.bolt.test"

  name := NamePrefix + "."

  lazy val wrapper = Project(
    id = "bolt",
    base = file("bolt")
  ).settings(Common.settings: _*)
    .settings(mainClass in Compile := Some("bolt.Main"))
    .settings(libraryDependencies ++= Dependencies.streamingDependencies)

  fork in run := true
}