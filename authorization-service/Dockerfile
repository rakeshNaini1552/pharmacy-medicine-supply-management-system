FROM amazoncorretto:11-alpine
EXPOSE 8084
ADD target/authorization-service.jar authorization-service.jar 
ENTRYPOINT ["java","-jar","/authorization-service.jar"]