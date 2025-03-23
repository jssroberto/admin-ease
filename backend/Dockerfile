FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
VOLUME /root/.m2
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests -T 1C

FROM eclipse-temurin:21.0.6_7-jdk-alpine-3.20
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]