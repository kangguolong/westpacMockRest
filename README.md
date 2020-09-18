## Westpac Rest Demo

[![CircleCI](https://circleci.com/gh/kangguolong/westpacMockRest.svg?style=svg)](https://circleci.com/gh/kangguolong/westpacMockRest)

This repository provides the modern practice of building a web application using Spring framework (Springboot).

### Frameworks involved

* Spring Framework 5 (Springboot 2)
* Spring Webflux
* WebClient API
* Junit 5 (Mockito integrated)
* MockWebServer for testing WebClient
* WebTestClient for testing Reactive program (Rest API)
* CircleCI (CI)
* Spring openAPI-webflux integration (Swagger)
* Docker (Dockerfile)

### Requirements to run the project

* Java 8

### Run steps (without a container)

* Download the source code
* Execute $: **mvn clean install**
* Find the fat jar under the generated target folder
* Execute $: **java -jar rest-service-0.0.1-SNAPSHOT.jar**

### Run steps (in a container)

* Download the source code and open the shell under the project root dir
* Build a docker image $: **docker build -t westpac/westpac-rest-mock .**
* Run in a container $: **docker run -dp 8080:8080 --name westpac_rest westpac/westpac-rest-mock**

### Visit the API Doc when project starts

* **http://localhost:8080/swagger-ui.html**
