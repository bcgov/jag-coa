FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /workspace

# Copy aggregator pom + module sources from repo path
COPY src/coa-application/pom.xml ./pom.xml
COPY src/coa-application/coa-common-models ./coa-common-models
COPY src/coa-application/jag-coa-application ./jag-coa-application

# Build both modules from root aggregator
RUN apk add --no-cache maven && mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

# fix CVE-2025-65018, CVE-2025-64720, CVE-2025-66293, CVE-2025-59375, CVE-2025-9230
RUN apk update \
    && apk add --upgrade --no-cache libexpat \
    && apk add --upgrade --no-cache libpng \
    && apk add --upgrade --no-cache openssl

COPY --from=build /workspace/jag-coa-application/target/jag-coa-application.jar /jag-coa-application.jar

ENTRYPOINT ["java", "-Xmx512m", "-jar", "/jag-coa-application.jar"]
