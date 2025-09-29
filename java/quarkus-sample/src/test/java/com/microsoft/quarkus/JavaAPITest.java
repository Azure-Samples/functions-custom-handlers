package com.microsoft.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class JavaAPITest {

    @Test
    public void testHelloEndpoint() {
/*
        given()
          .when().post("/QueueTrigger")
          .then()
             .statusCode(200);
*/
    }

}
