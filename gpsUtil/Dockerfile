FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/gpsUtil-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gpsutil.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/gpsutil.jar"]