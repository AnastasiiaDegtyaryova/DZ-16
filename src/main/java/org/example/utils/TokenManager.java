package org.example.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String token;

    public static String getToken() {
        if (token == null) {
            Response response = given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body("{\"username\" : \"admin\", \"password\" : \"password123\"}")
                    .post("/auth");

            response.then().log().all();

            if (response.getStatusCode() == 200) {
                token = response.jsonPath().getString("token");
            } else {
                throw new RuntimeException("Failed to get token, response code: " + response.getStatusCode());
            }
        }
        return token;
    }
}
