FROM openjdk:8

EXPOSE 9090

ADD target/devops-api-gateway.jar devops-api-gateway.jar

ENTRYPOINT["java","-jar","devops-api-gateway.jar"]