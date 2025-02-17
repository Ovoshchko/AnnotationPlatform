FROM arm64v8/eclipse-temurin:17-jdk AS build

# Set the working directory
WORKDIR /app

# Copy only the Gradle wrapper files and the build.gradle file first
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Download dependencies (this layer will be cached)
RUN ./gradlew dependencies --no-daemon

# Now copy the rest of the application code
COPY . .

# Build the application, skipping tests
RUN ./gradlew build -x test

FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
