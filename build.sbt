name := """Locality"""

version := "1.0-SNAPSHOT"

lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
  "org.json" % "json" % "20140107",
  "com.adrianhurt" %% "play-bootstrap3" % "0.4.4-P24",
  "commons-io" % "commons-io" % "2.3",
  "joda-time" % "joda-time" % "2.4",
  "commons-io" % "commons-io" % "2.3",
  "com.google.inject.extensions" % "guice-assistedinject" % "4.0",
  "org.webjars" % "jquery" % "2.1.4", 
  "org.webjars" % "bootstrap" % "3.3.5",
  "commons-collections" % "commons-collections" % "3.2.1",
  "commons-logging" % "commons-logging" % "1.1.1",
  "org.eclipse.mylyn.github" % "org.eclipse.egit.github.core" % "2.1.5",
  "org.eclipse.jgit" % "org.eclipse.jgit" % "3.7.0.201502260915-r",
  "org.dstadler" % "commons-dost" % "1.0.0.9"
  
)

libraryDependencies += "org.apache.poi" % "poi" % "3.9"


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator