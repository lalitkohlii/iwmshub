package com.iwmshub.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Application configuration class for IWMS Hub
 * Centralizes configuration properties and provides type-safe access
 */
@ApplicationScoped
public class AppConfig {

    @ConfigProperty(name = "quarkus.application.name")
    String applicationName;

    @ConfigProperty(name = "quarkus.application.version")
    String applicationVersion;

    @ConfigProperty(name = "quarkus.http.root-path", defaultValue = "/api")
    String apiRootPath;

    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    int httpPort;

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public String getApiRootPath() {
        return apiRootPath;
    }

    public int getHttpPort() {
        return httpPort;
    }

    /**
     * Returns the full base URL for API endpoints
     * @return Full API base URL (e.g., "http://localhost:8080/api")
     */
    public String getApiBaseUrl() {
        return "http://localhost:" + httpPort + apiRootPath;
    }
}
