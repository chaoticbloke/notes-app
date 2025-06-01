# Use official OpenJDK 17 image from Docker Hub as the base image
FROM openjdk:17

# Set working directory inside the container to /app
WORKDIR /app

# Copy the JAR file from your local target directory into the container's /app directory
COPY target/notes.jar notes.jar
# ⬆️ Syntax: COPY <source on host> <destination in container>

# Expose port 8080 so Docker can map it to host (Spring Boot default port)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "notes.jar"]
