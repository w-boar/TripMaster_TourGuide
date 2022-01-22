FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/tourGuide-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} tourguide.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/tourguide.jar"]