FROM openjdk:8

EXPOSE 8086

ADD target/devops-auth-service.jar devops-auth-service.jar

ENTRYPOINT["java","-jar","devops-auth-service.jar"]