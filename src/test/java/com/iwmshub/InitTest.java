package com.iwmshub;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class HealthControllerTest {
    
    @Test
    void testHealthEndpoint() {
        given()
          .when().get("/api/v1/health")
          .then()
             .statusCode(200)
             .body(containsString("UP"))
             .body(containsString("iwms-hub"));
    }
    
    @Test
    void testReadyEndpoint() {
        given()
          .when().get("/api/v1/health/ready")
          .then()
             .statusCode(200)
             .body(containsString("READY"));
    }
}