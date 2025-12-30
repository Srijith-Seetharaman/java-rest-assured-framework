package com.example.api.utils;

import io.restassured.response.Response;

public class SerialiseUtils {

    public static <T> T deserialize(Response responseBody, Class<T> clazz) {
        return responseBody.as(clazz);
    }
}
