FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Copy the built JAR file from the previous stage to the container
COPY --from=build /usr/src/app/target/UserService-0.0.1-SNAPSHOT.jar /usr/app/UserService-0.0.1-SNAPSHOT.jar
EXPOSE 8080
# Set the command to run the application
CMD ["java", "-jar", "/usr/app/UserService-0.0.1-SNAPSHOT.jar"]