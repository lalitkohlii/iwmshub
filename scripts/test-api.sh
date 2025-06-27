#!/bin/bash

# API Verification Script for IWMS Hub

echo "Testing IWMS Hub API endpoints..."
echo

# Start the application in background (this would need to be done manually)
echo "Please ensure the application is running with: ./gradlew quarkusDev"
echo

# Wait for user confirmation
read -p "Press Enter when the application is running..."

echo "Testing endpoints:"
echo

echo "1. Testing Health endpoint..."
curl -s http://localhost:8080/api/v1/health | jq . 2>/dev/null || curl -s http://localhost:8080/api/v1/health
echo
echo

echo "2. Testing Ready endpoint..."
curl -s http://localhost:8080/api/v1/health/ready | jq . 2>/dev/null || curl -s http://localhost:8080/api/v1/health/ready
echo
echo

echo "3. Testing IWMS Data GET endpoint..."
curl -s http://localhost:8080/api/v1/iwms/data/test123 | jq . 2>/dev/null || curl -s http://localhost:8080/api/v1/iwms/data/test123
echo
echo

echo "4. Testing IWMS Data POST endpoint..."
curl -s -X POST -H "Content-Type: application/json" -d "test data" http://localhost:8080/api/v1/iwms/data | jq . 2>/dev/null || curl -s -X POST -H "Content-Type: application/json" -d "test data" http://localhost:8080/api/v1/iwms/data
echo
echo

echo "5. Testing System endpoints (should be accessible without /api prefix):"
echo

echo "5a. Testing Swagger UI availability..."
curl -s -I http://localhost:8080/q/swagger-ui/ | grep "200 OK"
echo

echo "5b. Testing Dev UI availability..."
curl -s -I http://localhost:8080/q/dev/ | grep "200 OK"
echo

echo "API testing complete!"
echo
echo "All your REST API endpoints now start with /api/"
echo "- Application APIs: http://localhost:8080/api/*"
echo "- System APIs: http://localhost:8080/q/*"
