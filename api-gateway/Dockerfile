FROM eclipse-temurin:17-jdk

EXPOSE 9090

ADD target/devops-api-gateway.jar devops-api-gateway.jar

ENTRYPOINT ["java","-jar","devops-api-gateway.jar"]