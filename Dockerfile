FROM eclipse-temurin:11-jre-jammy

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xmx256m", "-jar","/jag-coa-application.jar"]
