FROM openjdk:19-alpine

# Install Maven
RUN apk add --no-cache maven

COPY . /backend
WORKDIR /backend

# Build the project using Maven
RUN mvn clean install

# Expose port 9090
EXPOSE 9090

# Start the application
CMD ["java", "-jar", "/root/.m2/repository/mk/finki/ama/Dom2/0.0.1-SNAPSHOT/Dom2-0.0.1-SNAPSHOT.jar"]



