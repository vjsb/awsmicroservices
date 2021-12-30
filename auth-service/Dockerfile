FROM openjdk:latest

ADD target/authserviceapp-1.0-SNAPSHOT.jar authserviceapp.jar

ENTRYPOINT ["java","-jar","/authserviceapp.jar"]

EXPOSE 8080