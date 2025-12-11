#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM maven:3.9.11-eclipse-temurin-25-noble AS build

ARG SKIP_TESTS=false
ARG STARTERS_V
#Hardcoded for now
ENV STARTERS_V=v1.0.5

WORKDIR /libs

RUN git clone https://github.com/bcgov/spring-boot-starters.git

WORKDIR /libs/spring-boot-starters

RUN git checkout $STARTERS_V  && \
    mvn install -P all -f src/pom.xml

WORKDIR /

COPY . .

RUN mvn -B clean install \
        -Dmaven.test.skip=${SKIP_TESTS}


#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM openjdk:25-ea-21-jdk-slim

ARG SERVICE_NAME=coa-large-file-service

COPY --from=build ./target/${SERVICE_NAME}-*.jar /app/service.jar

CMD ["java", "-jar", "/app/service.jar"]
#############################################################################################
