FROM amazoncorretto:8-alpine-jdk
WORKDIR /app
COPY target/tracker.jar app.jar
COPY src/main/resources/application.yaml application.yaml
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]