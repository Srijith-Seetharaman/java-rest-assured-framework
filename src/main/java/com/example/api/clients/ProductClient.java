package com.example.api.clients;

import com.example.api.endpoints.Endpoints;
import com.example.api.models.response.AddProductResponsePayload;
import com.example.api.utils.SerialiseUtils;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductClient {

    public static AddProductResponsePayload createProduct(String loginToken, String userId) {

        // src/test/resources/download.png

        RestClient restClient = new RestClient();

        Map<String, String> productDetailsFormParams = new HashMap<>() {
            {
                put("productName", "qwerty");
                put("productAddedBy", userId);
                put("productCategory", "fashion");
                put("productSubCategory", "shirts");
                put("productPrice", "11500");
                put("productDescription", "Addias Originals");
                put("productFor", "women");
            }
        };
        Map<String, String> formParamsAttachment = new HashMap<>(){{
            put("productImage" ,"src/test/resources/download.png");
        }};
        Map<String, String> headers = new HashMap<>() {
            {
                put("Authorization", loginToken);
            }
        };

        Response response = restClient.postWithFormParamsAndFile(Endpoints.ADD_PRODUCT.getPath(), headers, productDetailsFormParams, formParamsAttachment);
        return SerialiseUtils.deserialize(response, AddProductResponsePayload.class);
    }
}
