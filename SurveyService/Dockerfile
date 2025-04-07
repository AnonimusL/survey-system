FROM maven:3.8.4-openjdk-17-slim AS build

# Copy the pom.xml and the project files to the container
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Copy the built JAR file from the previous stage to the container
COPY --from=build /usr/src/app/target/SurveyService-0.0.1-SNAPSHOT.jar /usr/app/SurveyService-0.0.1-SNAPSHOT.jar
EXPOSE 8081
# Set the command to run the application
CMD ["java", "-jar", "/usr/app/SurveyService-0.0.1-SNAPSHOT.jar"]