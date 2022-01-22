FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/rewardCentral-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} rewardcentral.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/rewardcentral.jar"]