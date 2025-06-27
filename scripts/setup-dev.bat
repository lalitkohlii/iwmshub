@echo off
REM Development setup script for IWMS Hub (Windows)

echo Setting up IWMS Hub development environment...

REM Check if Java 21 is available
echo Checking Java version...
java -version

REM Clean and build the project
echo Building the project...
gradlew.bat clean build

REM Run tests
echo Running tests...
gradlew.bat test

echo Setup complete!
echo.
echo To start development mode, run:
echo   gradlew.bat quarkusDev
echo.
echo Available URLs:
echo   Application: http://localhost:8080/api
echo   Health Check: http://localhost:8080/api/v1/health
echo   Dev UI: http://localhost:8080/q/dev/
echo   Swagger UI: http://localhost:8080/q/swagger-ui/
echo   Quarkus Health: http://localhost:8080/q/health/
