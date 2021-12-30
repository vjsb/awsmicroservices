FROM openjdk:latest

ADD target/userserviceapp-1.0-SNAPSHOT.jar userserviceapp.jar

ENTRYPOINT ["java","-jar","/userserviceapp.jar"]

EXPOSE 8081