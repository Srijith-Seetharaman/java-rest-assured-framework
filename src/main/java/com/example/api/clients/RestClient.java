package com.example.api.clients;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.example.api.specs.RequestSpec.*;
import static com.example.api.specs.ResponseSpec.getResponseSpec;

public class RestClient {

    public Response get(String endpoint) {
        return getRequestSpec().get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        return getRequestSpec().body(body).post(endpoint);
    }

    public Response put(String endpoint, HashMap<Object, Object> body) {
        return getRequestSpec().body(body).put(endpoint);
    }

    public Response patch(String endpoint, HashMap<Object, Object> body) {
        return getRequestSpec().body(body).patch(endpoint);
    }

    public Response delete(String endpoint) {
        return getRequestSpec().delete(endpoint);
    }

    public <T> T postAndParse(String endpoint, Object body, Class<T> OutputClass) {
        return post(endpoint, body).then().spec(getResponseSpec()).extract().response().as(OutputClass);
    }
}
