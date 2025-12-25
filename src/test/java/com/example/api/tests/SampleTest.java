package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SampleTest {

    @Test
    public static void sampleEndToEndTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/api/ecom";

        given().body("""
                        {
                            "userEmail": "srijith.seetha@gmail.com",
                            "userPassword": "8R2diOE7h3rqME"
                        }""").contentType(ContentType.JSON).log().all()
                .when().post("auth/login")
                .then().log().all().statusCode(401);
    }
}
