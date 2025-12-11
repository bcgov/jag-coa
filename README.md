# jag-coa
Github space for the integration API for Court of Appeal application for the webMethods retirement project

[![Lifecycle:Stable](https://img.shields.io/badge/Lifecycle-Stable-97ca00)](https://github.com/bcgov/jag-coa)

## Recommended Tools
* Intellij
* Docker
* Maven
* Java 17/25
* Lombok

## Project Structure

    .
    ├── .github                                 # Gitactions and other github related files
    ├── gitops                                  # openshift templates and pipeline
    ├── src/                                    # application source files
    │   ├── coa-application                     # coa-application root
    │   │   ├── jag-coa-application             # coa api's
    │   │   └── coa-common-models               # coa models
    │   ├── AutomatedTests                      # Automation Test Suite
    │   └── coa-large-file-service              # large file transfer api
    ├── COMPLIANCE.yaml                         #
    ├── LICENSE                                 # Apache License
    └── README.md                               # This file.

## Apps

| Name                         | Description                                  | Doc                                                      |
| ---------------------------- | -------------------------------------------- | -------------------------------------------------------- |
| jag-coa-application          |  server side services                        | [README](src/coa-application/README.md)                  |
| coa-large-file-service-api   | large file transfer service                  | [README](src/coa-large-file-service/README.md)           |
| AutomatedTests               | automation test suite                        | [README](src/AutomatedTests/README.md)                   |

