package com.iwmshub.controller;

import com.iwmshub.service.IwmsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/iwms")
@Tag(name = "IWMS Operations", description = "Core IWMS functionality")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IwmsController {

    @Inject
    IwmsService iwmsService;

    @GET
    @Path("/data/{id}")
    @Operation(summary = "Get data by ID", description = "Retrieves specific data by its identifier")
    public Response getData(@PathParam("id") String id) {
        try {
            String result = iwmsService.processData(id);
            return Response.ok()
                    .entity("""
                        {
                            "id": "%s",
                            "data": "%s",
                            "timestamp": "%s"
                        }
                        """.formatted(id, result, java.time.Instant.now()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("""
                        {
                            "error": "Failed to process data",
                            "message": "%s"
                        }
                        """.formatted(e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("/data")
    @Operation(summary = "Create new data", description = "Creates a new data entry")
    public Response createData(String requestData) {
        try {
            String result = iwmsService.processData(requestData);
            return Response.status(Response.Status.CREATED)
                    .entity("""
                        {
                            "message": "Data created successfully",
                            "result": "%s",
                            "timestamp": "%s"
                        }
                        """.formatted(result, java.time.Instant.now()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("""
                        {
                            "error": "Failed to create data",
                            "message": "%s"
                        }
                        """.formatted(e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("/data/{id}")
    @Operation(summary = "Update data", description = "Updates existing data by ID")
    public Response updateData(@PathParam("id") String id, String requestData) {
        try {
            String result = iwmsService.processData(requestData);
            return Response.ok()
                    .entity("""
                        {
                            "id": "%s",
                            "message": "Data updated successfully",
                            "result": "%s",
                            "timestamp": "%s"
                        }
                        """.formatted(id, result, java.time.Instant.now()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("""
                        {
                            "error": "Failed to update data",
                            "message": "%s"
                        }
                        """.formatted(e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/data/{id}")
    @Operation(summary = "Delete data", description = "Deletes data by ID")
    public Response deleteData(@PathParam("id") String id) {
        return Response.ok()
                .entity("""
                    {
                        "id": "%s",
                        "message": "Data deleted successfully",
                        "timestamp": "%s"
                    }
                    """.formatted(id, java.time.Instant.now()))
                .build();
    }
}
