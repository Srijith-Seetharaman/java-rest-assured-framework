package com.example.api.specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    public static RequestSpecification requestSpecification() {
        RequestSpecBuilder requestSpecification = new RequestSpecBuilder();
        requestSpecification.setContentType(ContentType.JSON);
        requestSpecification.setAccept(ContentType.JSON);
        return requestSpecification.build();
    }

    public static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .spec(requestSpecification());
    }
}
