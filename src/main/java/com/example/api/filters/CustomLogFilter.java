package com.example.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.*;
import java.util.stream.Collectors;

public class CustomLogFilter implements Filter {

    private static final ThreadLocal<StringBuilder> log = new ThreadLocal<>();

    public static void startTestLog() {
        log.set(new StringBuilder());
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        StringBuilder logBuilder = log.get();
        if (logBuilder == null) {
            startTestLog();
            logBuilder = log.get();
        }

        String requestDetails = captureRequest(requestSpec);
        logBuilder.append(requestDetails).append("\n");

        Response response = ctx.next(requestSpec, responseSpec);

        String responseDetails = captureResponse(response);
        logBuilder.append(responseDetails).append("\n----------------------------------------------------\n\n");

        return response;
    }

    private String captureRequest(FilterableRequestSpecification requestSpec) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------- REQUEST ----------\n");
        sb.append("URI: ").append(requestSpec.getURI()).append("\n");
        sb.append("Method: ").append(requestSpec.getMethod()).append("\n");

        // Mask headers
        Map<String, String> maskedHeaders = new HashMap<>();
        requestSpec.getHeaders().forEach(header -> {
            if (header.getName().equalsIgnoreCase("Authorization")) {
                maskedHeaders.put(header.getName(), "*** MASKED ***");
            } else {
                maskedHeaders.put(header.getName(), header.getValue());
            }
        });
        sb.append("Headers: \n").append(formatMap(maskedHeaders));

        if (requestSpec.getBody() != null) {
            sb.append("\nBody: \n").append(Optional.ofNullable(requestSpec.getBody()));
        }
        return sb.toString();
    }

    private String captureResponse(Response response) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------- RESPONSE ----------\n");
        sb.append("Status Code: ").append(response.getStatusCode()).append("\n");
        sb.append("Headers: \n").append(formatMap(response.getHeaders().asList().stream()
                .collect(Collectors.toMap(h -> h.getName(), h -> h.getValue(), (oldValue, newValue) -> newValue))));

        if (response.getBody() != null && !response.getBody().asString().isEmpty()) {
            sb.append("\nBody: \n").append(response.getBody().asString());
        }
        return sb.toString();
    }

    private String formatMap(Map<String, ?> map) {
        if (map == null || map.isEmpty()) {
            return "  <none>";
        }
        return map.entrySet().stream()
                .map(entry -> "  " + entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

    public static String getLog() {
        if (log.get() == null) {
            return "";
        }
        String capturedLog = log.get().toString();
        log.remove();
        return capturedLog;
    }

    public static boolean hasLog() {
        return log.get() != null && log.get().length() > 0;
    }
}
