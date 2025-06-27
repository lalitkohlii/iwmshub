package com.iwmshub.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.time.Instant;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "INTERNAL_SERVER_ERROR",
            exception.getMessage(),
            Instant.now().toString()
        );
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }
    
    public static class ErrorResponse {
        public String error;
        public String message;
        public String timestamp;
        
        public ErrorResponse(String error, String message, String timestamp) {
            this.error = error;
            this.message = message;
            this.timestamp = timestamp;
        }
    }
}
