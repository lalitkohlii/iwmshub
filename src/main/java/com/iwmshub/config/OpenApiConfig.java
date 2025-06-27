package com.iwmshub.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
    info = @Info(
        title = "IWMS-Hub API",
        version = "1.0.0",
        description = "Integrated Workplace Management System Hub API",
        contact = @Contact(
            name = "IWMS Hub Support",
            email = "support@iwmshub.com"
        ),
        license = @License(
            name = "MIT",
            url = "https://opensource.org/licenses/MIT"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080/api", description = "Development Server"),
        @Server(url = "https://your-domain.com/api", description = "Production Server")
    }
)
public class OpenApiConfig extends Application {
}
