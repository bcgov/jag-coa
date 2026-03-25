FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copy Maven files and source
COPY src/AutomatedTest/pom.xml .
COPY src/AutomatedTest/src ./src

# Build the JAR
RUN apk add --no-cache maven && mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

RUN apk update && apk add --upgrade --no-cache libexpat # fix CVE-2024-8176

WORKDIR /app
RUN chgrp -R 0 /app && \
    chmod -R g=u /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/coa-test-runner.jar .

ENTRYPOINT ["java","-Xss2048k","-jar","coa-test-runner.jar"]
