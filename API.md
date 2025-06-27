# API Documentation - IWMS Hub

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
```

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

Test your endpoints using the configured base path:

```java
@Test
void testEndpoint() {
    given()
      .when().get("/api/v1/your-endpoint")
      .then()
         .statusCode(200);
}
```
