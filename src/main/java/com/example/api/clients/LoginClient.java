package com.example.api.clients;

import com.example.api.endpoints.Endpoints;
import com.example.api.models.request.UserLoginRequestPayload;
import com.example.api.models.response.UserLoginResponsePayload;

public class LoginClient {
    public static UserLoginResponsePayload login(UserLoginRequestPayload userLoginCredentials) {
        RestClient restClient = new RestClient();

                Endpoints.LOGIN.getPath(),
        );
    }
}
