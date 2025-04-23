# Use Java 17
FROM eclipse-temurin:17-jdk

# Copy project files
COPY . .

# Build the JAR (Maven)
RUN ./mvnw clean package

# Run the JAR (replace with your exact JAR name)
CMD ["java", "-jar", "target/EmailSender-0.0.1-SNAPSHOT.jar"]