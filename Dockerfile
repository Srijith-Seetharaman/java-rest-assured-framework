# Use an official Maven parent image
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to leverage Docker's layer caching
COPY pom.xml .

# Download all dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application's source code
COPY src ./src

# Run the tests, defaulting to the 'qa' environment
CMD ["mvn", "test", "-Denv=qa"]
