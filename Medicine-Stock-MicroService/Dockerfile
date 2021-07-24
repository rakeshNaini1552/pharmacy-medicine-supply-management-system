FROM amazoncorretto:11-alpine
EXPOSE 8081
ADD target/medicine-stock-microservice.jar medicine-stock-microservice.jar 
ENTRYPOINT ["java","-jar","/medicine-stock-microservice.jar"]