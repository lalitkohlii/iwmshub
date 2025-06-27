# IWMS-Hub - Code Management Guide

## Project Structure

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

## Best Practices

### 1. **Layered Architecture**
- **Controller Layer**: Handle HTTP requests/responses, validation
- **Service Layer**: Business logic, transaction management
- **Repository Layer**: Data access, database operations
- **Model Layer**: Data representation (Entities & DTOs)

### 2. **Naming Conventions**
- **Controllers**: `*Controller.java` (e.g., `UserController.java`)
- **Services**: `*Service.java` (e.g., `UserService.java`)
- **Repositories**: `*Repository.java` (e.g., `UserRepository.java`)
- **Entities**: Plain names (e.g., `User.java`, `WorkOrder.java`)
- **DTOs**: `*DTO.java` or `*Request.java`/`*Response.java`

### 3. **Configuration Management**
- Use `application.properties` for configuration
- Use profiles for different environments (`%dev`, `%prod`, `%test`)
- Keep sensitive data in environment variables

### 4. **API Design**
- Use RESTful conventions
- Version your APIs (`/api/v1/...`)
- Use appropriate HTTP methods (GET, POST, PUT, DELETE)
- Return consistent response formats

### 5. **Error Handling**
- Use global exception handlers
- Return meaningful error messages
- Include proper HTTP status codes

### 6. **Testing Strategy**
- Unit tests for services and utilities
- Integration tests for controllers
- Use TestContainers for database testing
- Maintain high test coverage

## Development Workflow

### 1. **Running the Application**
```bash
# Development mode (hot reload)
./gradlew quarkusDev

# Access dev UI: http://localhost:8080/q/dev/
# Access Swagger UI: http://localhost:8080/q/swagger-ui/
# Access Health checks: http://localhost:8080/q/health/
```

### 2. **Building for Production**
```bash
# Standard JAR
./gradlew build

# Uber JAR
./gradlew build -Dquarkus.package.jar.type=uber-jar

# Native executable
./gradlew build -Dquarkus.native.enabled=true
```

### 3. **Testing**
```bash
# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests "com.iwmshub.HealthControllerTest"
```

## Adding New Features

### 1. **Creating a New Entity**
1. Create entity class in `model/entity/`
2. Create corresponding DTO in `model/dto/`
3. Create repository in `repository/`
4. Create service in `service/`
5. Create controller in `controller/`
6. Add tests

### 2. **Database Integration** (when needed)
1. Add database dependency to `build.gradle`
2. Configure datasource in `application.properties`
3. Create JPA entities with proper annotations
4. Use Panache repositories for data access

### 3. **Security** (when needed)
1. Add `quarkus-security-jpa` or `quarkus-oidc` dependency
2. Configure security in `application.properties`
3. Add security annotations to endpoints
4. Implement authentication/authorization logic

## Code Quality

### 1. **Static Analysis**
- Consider adding SpotBugs, PMD, or Checkstyle
- Use IDE formatting rules
- Follow Java coding conventions

### 2. **Documentation**
- Use OpenAPI annotations for API documentation
- Write comprehensive README files
- Document complex business logic

### 3. **Logging**
- Use structured logging (JSON format in production)
- Include correlation IDs for request tracking
- Log at appropriate levels (DEBUG, INFO, WARN, ERROR)

## Deployment

### 1. **Docker**
- Use provided Dockerfiles for different scenarios
- Multi-stage builds for optimized images
- Health checks in containers

### 2. **Environment Configuration**
- Use externalized configuration
- Environment-specific property files
- Kubernetes ConfigMaps/Secrets

## Monitoring and Observability

### 1. **Health Checks**
- Implement custom health checks
- Monitor dependencies (database, external services)
- Use Kubernetes readiness/liveness probes

### 2. **Metrics**
- Enable Micrometer metrics
- Monitor business metrics
- Set up alerting

### 3. **Tracing**
- Add distributed tracing for microservices
- Use correlation IDs
- Monitor performance bottlenecks
