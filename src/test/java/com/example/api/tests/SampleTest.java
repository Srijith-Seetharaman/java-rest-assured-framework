package com.example.api.tests;

import com.example.api.base.BaseTest;
import com.example.api.endpoints.Endpoints;
import com.example.api.models.request.UserLoginRequestPayload;
import com.example.api.models.response.UserLoginResponsePayload;
import com.example.api.clients.RestClient;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest extends BaseTest {

    @Test
    public static void sampleEndToEndTest() {

        UserLoginRequestPayload userLoginCredentials = new UserLoginRequestPayload().createValidCredentials();

        RestClient restClient = new RestClient();

        UserLoginResponsePayload response = restClient.postAndParse(
                Endpoints.LOGIN.getPath(),
                userLoginCredentials,
                UserLoginResponsePayload.class
        );

        assertThat(response.getMessage()).isEqualTo("Login Successfully");

    }
}
