

FROM openjdk:14.0.1-jdk

WORKDIR /hs




COPY target/WorldNavigator-1.0-SNAPSHOT.jar /hs/WorldNavigator-1.0-SNAPSHOT.jar

CMD java -jar WorldNavigator-1.0-SNAPSHOT.jar
