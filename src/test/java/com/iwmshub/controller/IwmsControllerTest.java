package com.iwmshub.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class IwmsControllerTest {

    @Test
    void testGetData() {
        given()
          .when().get("/api/v1/iwms/data/test123")
          .then()
             .statusCode(200)
             .body(containsString("test123"))
             .body(containsString("Processed"));
    }

    @Test
    void testCreateData() {
        given()
          .contentType("application/json")
          .body("test data")
          .when().post("/api/v1/iwms/data")
          .then()
             .statusCode(201)
             .body(containsString("created successfully"));
    }

    @Test
    void testUpdateData() {
        given()
          .contentType("application/json")
          .body("updated data")
          .when().put("/api/v1/iwms/data/test123")
          .then()
             .statusCode(200)
             .body(containsString("updated successfully"));
    }

    @Test
    void testDeleteData() {
        given()
          .when().delete("/api/v1/iwms/data/test123")
          .then()
             .statusCode(200)
             .body(containsString("deleted successfully"));
    }
}
