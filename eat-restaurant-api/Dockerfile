FROM openjdk:14-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} eat-restaurant-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","eat-restaurant-api-0.0.1-SNAPSHOT.jar"]

