package com.example.api.tests;

import com.example.api.endpoints.Endpoints;
import com.example.api.models.request.UserLoginRequestPayload;
import com.example.api.models.response.UserLoginResponsePayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SampleTest {

    @Test
    public static void sampleEndToEndTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/api/ecom";

        UserLoginRequestPayload userLoginCredentials = new UserLoginRequestPayload().createValidCredentials();

        UserLoginResponsePayload response = given().body(userLoginCredentials).contentType(ContentType.JSON).log().all()
                .when().post(Endpoints.LOGIN.getPath())
                .then().log().all().statusCode(200).extract().response().as(UserLoginResponsePayload.class);

        Assert.assertEquals(response.getMessage(), "Login Successfully");

    }
}
