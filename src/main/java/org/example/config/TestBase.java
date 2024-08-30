package org.example.config;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class TestBase {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        requestSpec = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }
}

