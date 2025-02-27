# Build multi-stage for compiling and executing Spring Boot REST Webservice

# 1st stage : Compiling
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

# 2nd stage : Executing
FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=builder /app/target/sum-it_backend-0.0.1-SNAPSHOT.jar /app/gp3c.jar
EXPOSE 8080
CMD [ "ls /app" ]
ENTRYPOINT [ "java", "-jar", "/app/gp3c.jar" ]