# Project-Matcher @ Spring I/O 2023
## About
Code for the session "Empower your Spring-Applications with Python-Features on GraalVM" at [Spring I/O 2023](https://2023.springio.net/sessions/empower-your-spring-applications-with-python-features-on-graalvm/).

## Abstract
GraalVM is mainly known for its native image-compiler. But it provides a second feature: Running languages like Python and JavaScript side-by-side with JVM-languages in a single program. So we can mix Java and Python in our Spring Boot-application like we’re mixing Java and Kotlin! In this session we will have a deepdive into GraalVM to understand how those different languages are working together. I will demonstrate how to implement a service using Data Science-packages in Python and inject it as Bean in Spring Boot. We will also discover how to set up a suitable Dockerfile and write test for our code. Finally I will discuss possible use cases for so called “polyglot” applications and next steps in their development.

## Start Project
``` docker-compose up ```

## Endpoint
POST - http://localhost:8080/employee/recommendation
Request-Body:
```json
{
    "skills": [
        "Spring Boot",
        "GraalVM"
    ]
}
```