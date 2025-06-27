package com.iwmshub.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/health")
public class HealthController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health() {
        return Response.ok()
                .entity("""
                    {
                        "status": "UP",
                        "service": "iwms-hub",
                        "version": "v1",
                        "timestamp": "%s"
                    }
                    """.formatted(java.time.Instant.now()))
                .build();
    }

    @GET
    @Path("/ready")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ready() {
        return Response.ok()
                .entity("""
                    {
                        "status": "READY",
                        "service": "iwms-hub"
                    }
                    """)
                .build();
    }
}
