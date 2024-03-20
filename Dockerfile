FROM eclipse-temurin:17-jre-alpine

RUN apk upgrade libexpat

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xms512m", "-Xmx1g", "-jar","/jag-coa-application.jar"]
