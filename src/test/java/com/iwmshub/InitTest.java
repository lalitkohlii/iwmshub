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
          .when().get("/v1/health")  // root-path /api is automatically added
          .then()
             .statusCode(200)
             .body(containsString("UP"))
             .body(containsString("iwms-hub"));
    }
    
    @Test
    void testReadyEndpoint() {
        given()
          .when().get("/v1/health/ready")  // root-path /api is automatically added
          .then()
             .statusCode(200)
             .body(containsString("READY"));
    }
}