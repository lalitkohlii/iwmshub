# IWMS-Hub

**Integrated Workplace Management System Hub** - A comprehensive REST API built with Quarkus framework for managing workplace operations.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## 🚀 Quick Start

### Prerequisites
- Java 21 or later
- Gradle 8.x (included via wrapper)

### Development Setup

1. **Clone and setup:**
   ```bash
   git clone <repository-url>
   cd iwmshub
   
   # For Windows:
   scripts\setup-dev.bat
   
   # For Unix/Linux/Mac:
   chmod +x scripts/setup-dev.sh
   ./scripts/setup-dev.sh
   ```

2. **Start development mode:**
   ```bash
   # Windows
   gradlew.bat quarkusDev
   
   # Unix/Linux/Mac
   ./gradlew quarkusDev
   ```

3. **Access the application:**
   - **Application**: http://localhost:8080
   - **Dev UI**: http://localhost:8080/q/dev/
   - **Swagger UI**: http://localhost:8080/q/swagger-ui/
   - **Health Check**: http://localhost:8080/q/health/

## 📁 Project Structure

```
src/main/java/com/iwmshub/
├── config/           # Configuration classes and beans
├── controller/       # REST API endpoints
├── service/          # Business logic layer
├── repository/       # Data access layer
├── model/           # Data models
│   ├── entity/      # JPA entities (database models)
│   └── dto/         # Data Transfer Objects
├── exception/       # Custom exceptions and error handling
├── util/           # Utility classes and helpers
└── Application.java # Main application entry point
```

## 🔧 Available Endpoints

### Health & Monitoring
- `GET /api/v1/health` - Application health status
- `GET /api/v1/health/ready` - Readiness check
- `GET /q/health` - Comprehensive health checks
- `GET /q/metrics` - Application metrics

### API Documentation
- `GET /q/swagger-ui` - Interactive API documentation
- `GET /q/openapi` - OpenAPI specification

## 🏗️ Building and Packaging

### Development Build
```bash
gradlew build
```

### Production JAR
```bash
gradlew build -Dquarkus.package.jar.type=uber-jar
```

### Native Executable
```bash
gradlew build -Dquarkus.native.enabled=true
```

### Docker Build
```bash
# JVM mode
docker build -f src/main/docker/Dockerfile.jvm -t iwms-hub:jvm .

# Native mode
docker build -f src/main/docker/Dockerfile.native -t iwms-hub:native .
```

## 🧪 Testing

```bash
# Run all tests
gradlew test

# Run specific test class
gradlew test --tests "com.iwmshub.HealthControllerTest"

# Run tests with coverage
gradlew test jacocoTestReport
```

## ⚙️ Configuration

Configuration is managed through `application.properties` with environment-specific profiles:

- **Development**: `%dev` prefix
- **Production**: `%prod` prefix  
- **Testing**: `%test` prefix

### Key Configuration Areas
- **HTTP/Server**: Port, CORS, host binding
- **Database**: Connection pools, JPA settings
- **Security**: Authentication, authorization
- **Monitoring**: Health checks, metrics
- **API Documentation**: OpenAPI, Swagger UI

## 🐳 Docker Support

Multiple Dockerfiles are provided for different deployment scenarios:

- `Dockerfile.jvm` - Standard JVM-based container
- `Dockerfile.native` - GraalVM native executable
- `Dockerfile.native-micro` - Minimal native container
- `Dockerfile.legacy-jar` - Legacy JAR packaging

## 📚 Documentation

- **[Development Guide](DEVELOPMENT.md)** - Comprehensive development practices
- **[API Documentation](http://localhost:8080/q/swagger-ui/)** - Interactive API docs (when running)

## 🔄 Development Workflow

1. **Feature Development**
   - Create feature branch
   - Follow layered architecture (Controller → Service → Repository)
   - Write tests for new functionality
   - Update API documentation

2. **Code Quality**
   - Use provided EditorConfig for consistent formatting  
   - Follow Java naming conventions
   - Implement proper error handling
   - Add logging with appropriate levels

3. **Testing Strategy**
   - Unit tests for business logic
   - Integration tests for API endpoints
   - Use TestContainers for database testing

## 🚀 Deployment

### Local Development
```bash
gradlew quarkusDev
```

### Production Deployment
```bash
# Build production JAR
gradlew build

# Run with production profile
java -Dquarkus.profile=prod -jar build/quarkus-app/quarkus-run.jar
```

### Container Deployment
```bash
# Build and run container
docker build -f src/main/docker/Dockerfile.jvm -t iwms-hub .
docker run -p 8080:8080 iwms-hub
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🔗 Related Resources

- [Quarkus Documentation](https://quarkus.io/guides/)
- [RESTEasy Guide](https://quarkus.io/guides/resteasy)
- [SmallRye OpenAPI Guide](https://quarkus.io/guides/openapi-swaggerui)
- [Quarkus Testing Guide](https://quarkus.io/guides/getting-started-testing)
