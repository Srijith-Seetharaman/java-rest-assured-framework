package com.example.api.clients;

import com.example.api.circuitbreaker.CircuitBreakerManager;
import com.example.api.specs.RequestSpec;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;
import java.util.function.Supplier;

public final class RestClient {

    private RestClient() {
    }

    public static RequestSpecification getBaseRequestSpec() {
        return RequestSpec.getRequestSpec();
    }

    public static RequestSpecification getAuthenticatedRequestSpec(String token) {
        return getBaseRequestSpec().header("Authorization", token);
    }

    private static Response execute(Supplier<Response> responseSupplier) {
        CircuitBreaker circuitBreaker = CircuitBreakerManager.getCircuitBreaker();
        return circuitBreaker.executeSupplier(() -> {
            Response response = responseSupplier.get();
            if (response.getStatusCode() >= 500) {
                throw new RuntimeException("API Server Error: " + response.getStatusCode());
            }
            return response;
        });
    }

    public static Response get(RequestSpecification spec, String endpoint) {
        return execute(() -> spec.get(endpoint));
    }

    public static Response post(RequestSpecification spec, String endpoint, Object body) {
        return execute(() -> spec.body(body).post(endpoint));
    }

    public static Response put(RequestSpecification spec, String endpoint, Object body) {
        return execute(() -> spec.body(body).put(endpoint));
    }

    public static Response patch(RequestSpecification spec, String endpoint, Object body) {
        return execute(() -> spec.body(body).patch(endpoint));
    }

    public static Response delete(RequestSpecification spec, String endpoint) {
        return execute(() -> spec.delete(endpoint));
    }

    public static Response postWithFormParams(RequestSpecification spec, String endpoint,
            Map<String, Object> formParams) {
        return execute(() -> spec
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParams(formParams)
                .post(endpoint));
    }

    public static Response postWithFormParamsAndFile(RequestSpecification spec, String endpoint,
            Map<String, Object> formParams, Map<String, String> fileParams) {
        return execute(() -> {
            RequestSpecification multipartSpec = spec.contentType(ContentType.MULTIPART);

            if (formParams != null) {
                multipartSpec.formParams(formParams);
            }

            if (fileParams != null) {
                for (Map.Entry<String, String> entry : fileParams.entrySet()) {
                    multipartSpec.multiPart(entry.getKey(), new File(entry.getValue()));
                }
            }

            return multipartSpec.post(endpoint);
        });
    }
}