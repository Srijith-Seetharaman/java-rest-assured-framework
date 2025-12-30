package com.example.api.clients;

import com.example.api.endpoints.Endpoints;
import com.example.api.models.response.AddProductResponsePayload;
import com.example.api.models.response.DeleteProductResponsePayload;
import com.example.api.utils.SerialiseUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public final class ProductClient {

    private ProductClient() {
    }

    public static AddProductResponsePayload createProduct(String loginToken, String userId) {

        Map<String, Object> productDetailsFormParams = new HashMap<>() {
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
        Map<String, String> fileParams = new HashMap<>() {
            {
                put("productImage", "src/test/resources/download.png");
            }
        };

        RequestSpecification spec = RestClient.getAuthenticatedRequestSpec(loginToken);

        Response response = RestClient.postWithFormParamsAndFile(spec, Endpoints.ADD_PRODUCT.getPath(),
                productDetailsFormParams, fileParams);
        return SerialiseUtils.deserialize(response, AddProductResponsePayload.class);
    }

    public static DeleteProductResponsePayload deleteProduct(String loginToken, String productId) {
        RequestSpecification spec = RestClient.getAuthenticatedRequestSpec(loginToken);
        Response response = RestClient.delete(spec, Endpoints.DELETE_PRODUCT.getPath() + productId);
        return SerialiseUtils.deserialize(response, DeleteProductResponsePayload.class);
    }
}
