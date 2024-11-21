# jag-coa
Github space for the integration API for Court of Appeal application for the webMethods retirement project

[![Lifecycle:Stable](https://img.shields.io/badge/Lifecycle-Stable-97ca00)](https://github.com/bcgov/jag-coa)
[![Maintainability](https://api.codeclimate.com/v1/badges/a492f352f279a2d1621e/maintainability)](https://codeclimate.com/github/bcgov/jag-coa/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/a492f352f279a2d1621e/test_coverage)](https://codeclimate.com/github/bcgov/jag-coa/test_coverage)

### Recommended Tools
* Intellij
* Docker
* Maven
* Java 21
* Lombok

### Application Endpoints

Local Host: http://127.0.0.1:8080

Actuator Endpoint Local: http://localhost:8080/actuator/health

Code Climate: https://codeclimate.com/github/bcgov/jag-coa

WSDL Endpoint Local:
* localhost:8080/ws/COA.Source.ws.provider:WebCATSDocumentStorageService?WSDL

### Required Environmental Variables

BASIC_AUTH_PASS: The password for the basic authentication. This can be any value for local.

BASIC_AUTH_USER: The username for the basic authentication. This can be any value for local.

ORDS_HOST: The url for ords rest package.

ORDS_USERNAME: ORDS_HOST authentication

ORDS_PASSWORD: ORDS_HOST authentication

OBJ_STORE_APP_ID: parameter required to request document or ticket

OBJ_STORE_PSW: parameter required to request document or ticket

OBJ_STORE_DB_ID: parameter required to request document or ticket

OBJ_STORE_VERSION: parameter required to request document or ticket

OBJ_STORE_TICKET_LIFETIME: parameter required to request document or ticket

OBJ_STORE_USER: parameter required to request document or ticket

### Optional Enviromental Variables
SPLUNK_HTTP_URL: The url for the splunk hec.

SPLUNK_TOKEN: The bearer token to authenticate the application.

SPLUNK_INDEX: The index that the application will push logs to. The index must be created in splunk
before they can be pushed to.

### Building the Application
1) Make sure using java 21 for the project modals and sdk
2) Run ```mvn compile```
3) Make sure ```coa-common-models``` are marked as generated sources roots (xjc)

### Running the application
Option A) Intellij
1) Set env variables.
2) Run the application

Option B) Jar
1) Run ```mvn package```
2) Run ```cd jag-coa-application```
3) Run ```java -jar ./target/jag-coa-application.jar $ENV_VAR$```  (Note that $ENV_VAR$ are environment variables)

Option C) Docker
1) Run ```mvn package```
2) Run ```cd jag-coa-application```
3) Run ```docker build -t coa-application .```
4) Run ```docker run -p 8080:8080 coa-application $ENV_VAR$```  (Note that $ENV_VAR$ are environment variables)

### Pre Commit
1) Do not commit \CRLF use unix line enders
2) Run the linter ```mvn spotless:apply```

### JaCoCo Coverage Report
1) Run ```mvn clean verify```
2) Open ```jag-coa-application/target/site/jacoco/index.html``` in a browser
