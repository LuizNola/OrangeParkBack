FROM openjdk:11-jdk

WORKDIR /app

ARG JAR_FILE=target/Estacionamento-API-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]