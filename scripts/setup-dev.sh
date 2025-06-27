#!/bin/bash

# Development setup script for IWMS-Hub

echo "Setting up IWMS-Hub development environment..."

# Check if Java 21 is available
echo "Checking Java version..."
java -version

# Make gradlew executable (if on Unix-like system)
if [ -f "./gradlew" ]; then
    chmod +x ./gradlew
fi

# Clean and build the project
echo "Building the project..."
./gradlew clean build

# Run tests
echo "Running tests..."
./gradlew test

echo "Setup complete!"
echo ""
echo "To start development mode, run:"
echo "  ./gradlew quarkusDev"
echo ""
echo "Available URLs:"
echo "  Application: http://localhost:8080/api"
echo "  Health Check: http://localhost:8080/api/v1/health"
echo "  Dev UI: http://localhost:8080/q/dev/"
echo "  Swagger UI: http://localhost:8080/q/swagger-ui/"
echo "  Quarkus Health: http://localhost:8080/q/health/"
