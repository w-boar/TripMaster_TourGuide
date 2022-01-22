FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/tripPricer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} trippricer.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/trippricer.jar"]