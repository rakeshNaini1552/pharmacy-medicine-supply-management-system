FROM amazoncorretto:11-alpine
EXPOSE 8086
ADD target/portal.jar portal.jar 
ENTRYPOINT ["java","-jar","/portal.jar"]