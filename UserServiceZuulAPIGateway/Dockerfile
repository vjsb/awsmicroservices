FROM openjdk:latest

ADD target/zuulgatewayapp-1.0-SNAPSHOT.jar zuulgatewayapp.jar

ENTRYPOINT ["java","-jar","/zuulgatewayapp.jar"]

EXPOSE 9193