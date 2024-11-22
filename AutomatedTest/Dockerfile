FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
RUN chgrp -R 0 /app && \
    chmod -R g=u /app

COPY ./target/coa-test-runner.jar .

ENTRYPOINT ["java","-Xss2048k","-jar","coa-test-runner.jar"]
