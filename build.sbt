name := """xxx"""

version := "1.44.2"

enablePlugins(PlayScala, BuildInfoPlugin)

watchSources ++= (baseDirectory.value / "public/ui" ** "*").get

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.10"

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)

buildInfoKeys ++= Seq[BuildInfoKey](
  resolvers,
  libraryDependencies in Test,
  BuildInfoKey.map(name) { case (k, v) => "project" + k.capitalize -> v.capitalize },
  BuildInfoKey.action("buildTime") {
    System.currentTimeMillis
  } // re-computed each time at compile
)

val slickV                   = "3.3.2"
val slickPg                  = "0.18.1"
val postgresV                = "42.2.9"
val mariadbV                 = "2.6.0"
val jodaTimeV                = "2.10.5"
val slickJodaMapperVersion   = "2.4.2"
val playJsonJodaV            = "2.8.1"
val playJsonV                = "4.2.0"
val awsV                     = "1.11.711"
val awsSqsV                  = "1.11.375"
val redisScalaV              = "1.9.0"
val catsV                    = "2.0.0"
val alpakkaV                 = "1.1.2"
val scalaLoggingV            = "3.9.2"
val logbackV                 = "1.2.3"
val logbackEncoderV          = "6.3"
val metricsV                 = "4.1.1"
val jmxCollectorV            = "0.12.0"
val prometheusSimpleClientV  = "0.8.0"
val swaggerParserV           = "2.0.17"
val swaggerUiV               = "3.38.0"
val swaggerRequestValidatorV = "2.8.3"
val cassandraDriverV         = "3.6.0"
val guavaV                   = "23.0"
val jsr305V                  = "3.0.2"
val solrjV                   = "6.5.1"
val kinesisV                 = "1.11.817"
val kinesisProducerV         = "0.14.0"
val elastic4sV               = "6.7.6"
val pureCsvV                 = "0.1.1"

libraryDependencies += specs2 % Test

libraryDependencies ++= Seq(
  guice,
  jdbc,
  ws,
  "com.typesafe.slick"         %% "slick-hikaricp"                % slickV,
  "com.github.tminglei"        %% "slick-pg"                      % slickPg,
  "org.postgresql"             % "postgresql"                     % postgresV,
  "org.mariadb.jdbc"           % "mariadb-java-client"            % mariadbV,
  "com.amazonaws"              % "aws-java-sdk-kinesis"           % kinesisV exclude ("commons-logging", "commons-logging"),
  "com.amazonaws"              % "amazon-kinesis-producer"        % kinesisProducerV,
  "joda-time"                  % "joda-time"                      % jodaTimeV,
  "com.github.tototoshi"       %% "slick-joda-mapper"             % slickJodaMapperVersion,
  "com.typesafe.play"          %% "play-json-joda"                % playJsonJodaV,
  "com.pauldijou"              %% "jwt-play-json"                 % playJsonV,
  "com.amazonaws"              % "aws-java-sdk-s3"                % awsV,
  "com.amazonaws"              % "aws-java-sdk-sqs"               % awsSqsV,
  "com.github.etaty"           %% "rediscala"                     % redisScalaV,
  "org.typelevel"              %% "cats-core"                     % catsV,
  "com.lightbend.akka"         %% "akka-stream-alpakka-csv"       % alpakkaV,
  "com.typesafe.scala-logging" %% "scala-logging"                 % scalaLoggingV,
  "ch.qos.logback"             % "logback-classic"                % logbackV,
  "ch.qos.logback"             % "logback-core"                   % logbackV,
  "net.logstash.logback"       % "logstash-logback-encoder"       % logbackEncoderV,
  "nl.grons"                   %% "metrics4-scala"                % metricsV,
  "io.prometheus.jmx"          % "collector"                      % jmxCollectorV,
  "io.prometheus"              % "simpleclient_hotspot"           % prometheusSimpleClientV,
  "io.prometheus"              % "simpleclient_dropwizard"        % prometheusSimpleClientV,
  "io.prometheus"              % "simpleclient_common"            % prometheusSimpleClientV,
  "org.webjars"                % "swagger-ui"                     % swaggerUiV,
  "io.swagger.parser.v3"       % "swagger-parser"                 % swaggerParserV,
  "com.atlassian.oai"          % "swagger-request-validator-core" % swaggerRequestValidatorV % Test,
  "com.datastax.cassandra"     % "cassandra-driver-core"          % cassandraDriverV,
  "com.google.code.findbugs"   % "jsr305"                         % jsr305V,
  "com.google.guava"           % "guava"                          % guavaV,
  "org.apache.solr"            % "solr-solrj"                     % solrjV,
  "com.sksamuel.elastic4s"     %% "elastic4s-core"                % elastic4sV,
  "com.sksamuel.elastic4s"     %% "elastic4s-http"                % elastic4sV,
  "com.github.melrief"         %% "purecsv"                       % pureCsvV
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-Ypartial-unification",
  "-Xfatal-warnings"
)

unmanagedResourceDirectories in Assets += baseDirectory.value / "public-docs"

coverageMinimum := 1
coverageFailOnMinimum := true
coverageExcludedPackages := "controllers.javascript"

addCommandAlias("runC", ";compile;run")