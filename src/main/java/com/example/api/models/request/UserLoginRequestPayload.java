package com.example.api.models.request;

import com.example.api.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestPayload {

    private String userEmail;
    private String userPassword;

    public UserLoginRequestPayload createValidCredentials() {
        return new UserLoginRequestPayload(Constants.username, Constants.password);
    }
}
