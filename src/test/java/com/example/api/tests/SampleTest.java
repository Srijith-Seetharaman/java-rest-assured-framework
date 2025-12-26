package com.example.api.tests;

import com.example.api.base.BaseTest;
import com.example.api.clients.LoginClient;
import com.example.api.models.request.UserLoginRequestPayload;
import com.example.api.models.response.UserLoginResponsePayload;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest extends BaseTest {

    @Test
    public static void sampleEndToEndTest() {

        UserLoginRequestPayload userLoginCredentials = new UserLoginRequestPayload().createValidCredentials();

        UserLoginResponsePayload response = LoginClient.login(userLoginCredentials);

        assertThat(response.getMessage()).contains("Login Successfully");

    }
}
