name := """WorkFlowMonitorService"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += jdbc

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % Test

// https://mvnrepository.com/artifact/org.mindrot/jbcrypt
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-mapreduce-client-core
libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.6.0"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-mapreduce-client-jobclient
libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-jobclient" % "2.6.0" % "provided"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-hdfs
libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.6.0"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.6.0" % "provided"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-yarn-common
libraryDependencies += "org.apache.hadoop" % "hadoop-yarn-common" % "2.6.0"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-yarn-client
libraryDependencies += "org.apache.hadoop" % "hadoop-yarn-client" % "2.6.0"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-yarn-server-resourcemanager
libraryDependencies += "org.apache.hadoop" % "hadoop-yarn-server-resourcemanager" % "2.6.0" % Test
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.6.0"

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
