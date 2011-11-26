import sbt._
import Keys._

object MyProject extends Build {
  val buildOrganization = "fr.hsyl20"
  val buildName         = "sunburn"
  val buildVersion      = "0.1"
  val buildScalaVersion = "2.9.1"

  lazy val project = Project (buildName, file("."), settings = mySettings)

  val mySettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    name         := buildName,
    version      := buildVersion,
    scalaVersion := buildScalaVersion,
    scalacOptions := Seq("-deprecation", "-unchecked"),
    resolvers    := myResolvers,
    libraryDependencies := myDependencies,

    fork := true,

    /* GIT ready shell prompt */
    shellPrompt  := ShellPrompt.buildShellPrompt,

    /* Tasks */
    fullRunTask(TaskKey[Unit]("run-samplers", "Samplers test utility"), Test, "fr.hsyl20.sunburn.SamplerTest"),
    fullRunTask(TaskKey[Unit]("run-simple-scene", "Render a simple scene"), Test, "fr.hsyl20.sunburn.Main")
  )

  val myResolvers = Seq(
    "Sun Maven2 Repo" at "http://download.java.net/maven/2",
    "Oracle Maven2 Repo" at "http://download.oracle.com/maven",
    "Scala-Tools Snapshots" at "http://scala-tools.org/repo-snapshots"
  )

  val myDependencies = Seq (
    "org.scala-lang" % "jline" % buildScalaVersion,
    "org.scala-lang" % "scala-swing" % buildScalaVersion
  )

}

// Shell prompt which show the current project, 
// git branch and build version
object ShellPrompt {
  object devnull extends ProcessLogger {
    def info (s: => String) {}
    def error (s: => String) { }
    def buffer[T] (f: => T): T = f
  }
  
  val current = """\*\s+([\w-]+)""".r
  
  def gitBranches = ("git branch --no-color" lines_! devnull mkString)
  
  val buildShellPrompt = { 
    (state: State) => {
      val currBranch = 
        current findFirstMatchIn gitBranches map (_ group(1)) getOrElse "-"
      val currProject = Project.extract (state).currentProject.id
      "%s:%s:%s> ".format (
        currProject, currBranch, MyProject.buildVersion
      )
    }
  }
}

