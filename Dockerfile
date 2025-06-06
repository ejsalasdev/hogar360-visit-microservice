# Build stage
FROM eclipse-temurin:17.0.15_6-jdk-alpine AS builder

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradle/ gradle/
COPY gradlew build.gradle settings.gradle ./

# Make gradlew executable
RUN chmod +x ./gradlew

# Copy source code
COPY src/ src/

# Build the application
RUN ./gradlew build

# Runtime stage
FROM eclipse-temurin:17.0.15_6-jre-alpine

# Create a non-root user
RUN addgroup --system spring && adduser --system spring --ingroup spring

# Set working directory
WORKDIR /app

# Copy the JAR file from build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership of the app directory
RUN chown spring:spring app.jar

# Switch to non-root user
USER spring

# Expose port
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
