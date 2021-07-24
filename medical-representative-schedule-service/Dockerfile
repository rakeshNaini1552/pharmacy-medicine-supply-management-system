FROM amazoncorretto:11-alpine
EXPOSE 8082
ADD target/medical-representative-schedule-service.jar medical-representative-schedule-service.jar 
ENTRYPOINT ["java","-jar","/medical-representative-schedule-service.jar"]