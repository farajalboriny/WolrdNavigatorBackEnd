
FROM java:8
EXPOSE 8080
ADD target/WorldNavigator-1.0-SNAPSHOT.jar WorldNavigator-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","WorldNavigator-1.0-SNAPSHOT.jar"]
