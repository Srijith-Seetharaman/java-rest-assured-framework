package com.example.api.tests;

import java.util.ArrayList;
import java.util.List;

import com.example.api.base.BaseTest;
import com.example.api.clients.LoginClient;
import com.example.api.clients.OrderClient;
import com.example.api.clients.ProductClient;
import com.example.api.models.request.CreateOrderRequestPayload;
import com.example.api.models.request.OrderDetail;
import com.example.api.models.request.UserLoginRequestPayload;
import com.example.api.models.response.*;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest extends BaseTest {

    public SampleTest() throws FileNotFoundException {
    }

    @Test
    public static void sampleEndToEndTest() {

        // Login to get token
        UserLoginRequestPayload userLoginCredentials = new UserLoginRequestPayload().createValidCredentials();
        UserLoginResponsePayload response = LoginClient.login(userLoginCredentials);
        assertThat(response.getMessage()).contains("Login Successfully");
        String loginToken = response.getToken();
        String userId = response.getUserId();

        // Create product
        AddProductResponsePayload addProductResponsePayload = ProductClient.createProduct(loginToken, userId);
        assertThat(addProductResponsePayload.getMessage()).contains("Product Added Successfully");
        String productId = addProductResponsePayload.getProductId();

        // Create order
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCountry("India");
        orderDetail.setProductOrderedId(productId);

        List<OrderDetail> orderDetails = new ArrayList<>() {
            {
                add(orderDetail);
            }
        };

        CreateOrderRequestPayload createOrderRequest = new CreateOrderRequestPayload();
        createOrderRequest.setOrders(orderDetails);

        CreateOrderResponsePayload createOrderResponse = OrderClient.createOrder(loginToken, createOrderRequest);
        assertThat(createOrderResponse.getMessage()).isEqualTo("Order Placed Successfully");
        assertThat(createOrderResponse.getProductOrderId().getFirst()).isEqualTo(productId);
        String orderId = createOrderResponse.getOrders().getFirst();

        // Get Order Details
        GetOrderDetailsResponsePayload getOrderDetailsResponse = OrderClient.getOrderDetails(loginToken, orderId);
        assertThat(getOrderDetailsResponse.getMessage()).isEqualTo("Orders fetched for customer Successfully");
        assertThat(getOrderDetailsResponse.getData().getCountry()).isEqualTo("India");
        assertThat(getOrderDetailsResponse.getData().getProductName()).isEqualTo("qwerty");

        // Delete Order
        DeleteOrderResponsePayload deleteOrderResponse = OrderClient.deleteOrder(loginToken, orderId);
        assertThat(deleteOrderResponse.getMessage()).isEqualTo("Orders Deleted Successfully");

        // Delete product
        DeleteProductResponsePayload deleteProductResponse = ProductClient.deleteProduct(loginToken, productId);
        assertThat(deleteProductResponse.getMessage()).isEqualTo("Product Deleted Successfully");

    }
}