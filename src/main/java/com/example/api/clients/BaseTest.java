package com.example.api.clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public RequestSpecification requestSpecification() {
        return given().contentType(ContentType.JSON);

    }
}
