# API Documentation - IWMS-Hub

## Base URL Structure

All REST API endpoints are prefixed with `/api` to maintain clean separation between:
- **Application APIs**: `/api/*` 
- **Quarkus System APIs**: `/q/*` (Dev UI, Health, Metrics, etc.)

### URL Pattern
```
http://localhost:8080/api/{version}/{resource}
```

### Examples
- `http://localhost:8080/api/v1/health` - Application health check
- `http://localhost:8080/api/v1/iwms/data` - IWMS data operations
- `http://localhost:8080/api/v1/users` - User management (future)
- `http://localhost:8080/api/v1/workspaces` - Workspace management (future)

## Available Endpoints

### Health & Monitoring (`/api/v1/health`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/health` | Application health status |
| GET | `/api/v1/health/ready` | Readiness check |

### IWMS Operations (`/api/v1/iwms`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/iwms/data/{id}` | Get data by ID |
| POST | `/api/v1/iwms/data` | Create new data |
| PUT | `/api/v1/iwms/data/{id}` | Update existing data |
| DELETE | `/api/v1/iwms/data/{id}` | Delete data by ID |

## System Endpoints (Quarkus)

These endpoints are NOT prefixed with `/api` and are for system/development use:

| Endpoint | Description |
|----------|-------------|
| `/q/dev/` | Development UI |
| `/q/swagger-ui/` | Interactive API documentation |
| `/q/health/` | Quarkus health checks |
| `/q/metrics/` | Application metrics |
| `/q/openapi` | OpenAPI specification |

## API Response Format

All API responses follow a consistent JSON format:

### Success Response
```json
{
  "id": "123",
  "data": "processed data",
  "timestamp": "2025-06-27T10:30:00Z"
}
```

### Error Response
```json
{
  "error": "Error type",
  "message": "Detailed error message",
  "timestamp": "2025-06-27T10:30:00Z"
}
```

## Request/Response Examples

### Get Data
```bash
curl -X GET http://localhost:8080/api/v1/iwms/data/123
```

Response:
```json
{
  "id": "123",
  "data": "Processed: 123",
  "timestamp": "2025-06-27T10:30:00Z"
}
```

### Create Data
```bash
curl -X POST http://localhost:8080/api/v1/iwms/data \
  -H "Content-Type: application/json" \
  -d "sample data"
```

Response:
```json
{
  "message": "Data created successfully",
  "result": "Processed: sample data",
  "timestamp": "2025-06-27T10:30:00Z"
}
```

## Configuration

The API base path is configured in `application.properties`:

```properties
# Sets all application endpoints to start with /api
quarkus.http.root-path=/api

# Ensures proper URL generation in development (fixes 0.0.0.0 issue)
%dev.quarkus.http.host=localhost

# OpenAPI/Swagger Configuration
quarkus.swagger-ui.always-include=true
quarkus.swagger-ui.path=/q/swagger-ui
quarkus.smallrye-openapi.path=/q/openapi
```

Additionally, server URLs are configured programmatically in `OpenApiConfig.java` to ensure Swagger UI shows correct localhost URLs instead of 0.0.0.0.

## Development URLs

When running in development mode (`gradlew quarkusDev`):

- **Application Base**: http://localhost:8080/api
- **Health Check**: http://localhost:8080/api/v1/health
- **Swagger UI**: http://localhost:8080/q/swagger-ui/
- **Dev UI**: http://localhost:8080/q/dev/

## Adding New Endpoints

When creating new controllers, follow this pattern:

```java
@Path("/v1/your-resource")  // Will become /api/v1/your-resource
@Tag(name = "Your Resource", description = "Description")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class YourController {
    // Your endpoints here
}
```

## Versioning Strategy

- **v1**: Current stable API version
- **v2**: Future API version (when needed)
- Maintain backward compatibility within major versions
- Use semantic versioning for API changes

## Testing

Test your endpoints using the configured base path. Note that in tests, you should use relative paths (without `/api`), as the root path is automatically applied:

```java
@Test
void testEndpoint() {
    given()
      .when().get("/v1/your-endpoint")  // Becomes /api/v1/your-endpoint automatically
      .then()
         .statusCode(200);
}
```

For manual testing with curl, use the full URL:
```bash
curl http://localhost:8080/api/v1/your-endpoint
```

## Troubleshooting

### Swagger UI Showing Wrong URLs

If Swagger UI shows URLs like `http://0.0.0.0:8080/api/...` instead of `http://localhost:8080/api/...`:

1. **Check Development Configuration**: Ensure `%dev.quarkus.http.host=localhost` is set in `application.properties`

2. **Verify OpenAPI Configuration**: The `OpenApiConfig.java` class should define proper server URLs:
   ```java
   @Server(url = "http://localhost:8080/api", description = "Development Server")
   ```

3. **Clear Browser Cache**: Sometimes browsers cache the OpenAPI specification

4. **Restart Development Server**: 
   ```bash
   # Stop current server (Ctrl+C)
   gradlew quarkusDev
   ```

### API Endpoints Not Working

If your API endpoints return 404 errors:

1. **Verify Base Path**: All application endpoints should be accessed with `/api` prefix
2. **Check Controller Paths**: Controllers should use relative paths (e.g., `@Path("/v1/resource")`)
3. **Confirm Server is Running**: Check that `gradlew quarkusDev` is running without errors

### System Endpoints Not Accessible

If Quarkus system endpoints (like `/q/health`) are not working:

1. **Don't Use /api Prefix**: System endpoints should be accessed directly (e.g., `http://localhost:8080/q/health`)
2. **Check Configuration**: Ensure system endpoints are enabled in `application.properties`
