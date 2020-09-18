FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} westpacMockRest.jar
ENTRYPOINT ["java","-jar","/westpacMockRest.jar"]
