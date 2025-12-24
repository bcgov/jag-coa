FROM eclipse-temurin:17-jre-alpine

# fix CVE-2025-65018, CVE-2025-64720, CVE-2025-66293, CVE-2025-59375, CVE-2025-9230
RUN apk update \
    && apk add --upgrade --no-cache libexpat \
    && apk add --upgrade --no-cache libpng \
    && apk add --upgrade --no-cache openssl

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xmx512m", "-jar","/jag-coa-application.jar"]
