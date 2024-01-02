FROM openjdk:17-alpine
WORKDIR /app
COPY build/libs/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]
