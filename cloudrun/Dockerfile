FROM openjdk:11-slim as builder
# Set the working directory to /app
WORKDIR /app
# Copy local code to the container image.
COPY . .
# Copy logback setting file.
COPY ./src/main/resources/cloudrun-logback-spring.xml ./src/main/resources/logback-spring.xml
# set the execution flag
RUN chmod +x gradlew
# Download dependencies and build a release artifact.
RUN ./gradlew assemble

# Use OpenJDK for base image.
FROM openjdk:11-jre-slim
COPY --from=builder /app/build/libs/todoapp-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=Asia/Tokyo", "-XX:MaxRAMPercentage=75", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=8080", "-jar", "/app.jar"]