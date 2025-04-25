# Use Java 17 base image
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy project files into the container
COPY . .

# Give executable permission to the Maven wrapper script
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the generated JAR file
CMD ["java", "-jar", "target/EmailSender-0.0.1-SNAPSHOT.jar"]
