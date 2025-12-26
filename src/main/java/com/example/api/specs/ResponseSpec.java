package com.example.api.specs;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {
    public static ResponseSpecification responseSpecification() {
        ResponseSpecBuilder requestSpecification = new ResponseSpecBuilder();
        requestSpecification.expectStatusCode(200);
        return requestSpecification.build();
    }

    public static ResponseSpecification getResponseSpec() {
        return RestAssured.expect()
                .spec(responseSpecification());
    }
}
