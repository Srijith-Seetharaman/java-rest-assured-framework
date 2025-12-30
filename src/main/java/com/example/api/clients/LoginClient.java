package com.example.api.clients;

import com.example.api.endpoints.Endpoints;
import com.example.api.models.request.UserLoginRequestPayload;
import com.example.api.models.response.UserLoginResponsePayload;
import com.example.api.specs.RequestSpec;
import com.example.api.utils.SerialiseUtils;
import io.restassured.response.Response;

public class LoginClient {
    public static UserLoginResponsePayload login(UserLoginRequestPayload userLoginCredentials) {
        Response response = RestClient.post(
                RequestSpec.getRequestSpec(),
                Endpoints.LOGIN.getPath(),
                userLoginCredentials);
        return SerialiseUtils.deserialize(response, UserLoginResponsePayload.class);
    }
}