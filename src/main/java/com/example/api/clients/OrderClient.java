package com.example.api.clients;

import com.example.api.endpoints.Endpoints;
import com.example.api.models.response.CreateOrderResponsePayload;
import com.example.api.models.response.DeleteOrderResponsePayload;
import com.example.api.models.response.GetOrderDetailsResponsePayload;
import com.example.api.utils.SerialiseUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class OrderClient {

    private OrderClient() {
    }

    public static CreateOrderResponsePayload createOrder(String loginToken, Object payload) {
        RequestSpecification spec = RestClient.getAuthenticatedRequestSpec(loginToken);
        Response response = RestClient.post(spec, Endpoints.CREATE_ORDER.getPath(), payload);
        return SerialiseUtils.deserialize(response, CreateOrderResponsePayload.class);
    }

    public static GetOrderDetailsResponsePayload getOrderDetails(String loginToken, String orderId) {
        RequestSpecification spec = RestClient.getAuthenticatedRequestSpec(loginToken)
                .queryParam("id", orderId);
        Response response = RestClient.get(spec, Endpoints.GET_ORDER_DETAILS.getPath());
        return SerialiseUtils.deserialize(response, GetOrderDetailsResponsePayload.class);
    }

    public static DeleteOrderResponsePayload deleteOrder(String loginToken, String orderId) {
        RequestSpecification spec = RestClient.getAuthenticatedRequestSpec(loginToken);
        Response response = RestClient.delete(spec, Endpoints.DELETE_ORDER.getPath() + orderId);
        return SerialiseUtils.deserialize(response, DeleteOrderResponsePayload.class);
    }
}
