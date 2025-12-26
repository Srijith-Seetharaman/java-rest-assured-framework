package com.example.api.tests;

import com.example.api.base.BaseTest;
import com.example.api.clients.LoginClient;
import com.example.api.clients.ProductClient;
import com.example.api.models.request.UserLoginRequestPayload;
import com.example.api.models.response.AddProductResponsePayload;
import com.example.api.models.response.UserLoginResponsePayload;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest extends BaseTest {

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
        System.out.println(productId);

    }
}