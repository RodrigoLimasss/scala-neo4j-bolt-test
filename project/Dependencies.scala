import sbt._

object Dependencies{

  val commonDependencies : Seq[ModuleID] = Seq(
    "org.neo4j.driver" % "neo4j-java-driver" % "1.0.0"
  )

  val streamingDependencies : Seq[ModuleID] = commonDependencies
}