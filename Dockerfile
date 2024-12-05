
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

COPY --from=build /app/src/main/resources/application.yml /app/resources/application.yml

ENTRYPOINT ["java", "-jar", "app.jar"]