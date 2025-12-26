package com.example.api.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.example.api.specs.RequestSpec.*;
import static com.example.api.specs.ResponseSpec.getResponseSpec;

public class RestClient {

    public Response get(String endpoint) {
        return getRequestSpec().get(endpoint);
    }

    public Response post(String endpoint, Map<String, ?> headers, Object body) {
        return getRequestSpec().headers(headers).body(body).post(endpoint);
    }

    public Response post(String endpoint, Object body) {
        Map<String, ?> headers = new HashMap<>();
        return this.post(endpoint, headers, body);
    }

    public Response put(String endpoint, Map<Object, Object> body) {
        return getRequestSpec().body(body).put(endpoint);
    }

    public Response patch(String endpoint, Map<Object, Object> body) {
        return getRequestSpec().body(body).patch(endpoint);
    }

    public Response delete(String endpoint) {
        return getRequestSpec().delete(endpoint);
    }

    public Response postWithFormParams(String endpoint, Map<String, ?> formParams) {
        return getRequestSpec()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParams(formParams)
                .post(endpoint);
    }

    public Response postWithFormParamsAndFile(String endpoint, Map<String, ?> headers, Map<String, ?> formParams, Map<String, String> fileParams) {
        RequestSpecification customRequestSpec = getRequestSpec().contentType(ContentType.MULTIPART).headers(headers).formParams(formParams);
        fileParams.forEach((k, v) -> {
            customRequestSpec.multiPart(k, new File(v));
        });
        return customRequestSpec.post(endpoint);
    }
}
