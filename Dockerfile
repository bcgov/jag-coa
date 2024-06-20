FROM eclipse-temurin:21-jre-alpine

RUN apk upgrade libexpat

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xmx512m", "-jar","/jag-coa-application.jar"]
