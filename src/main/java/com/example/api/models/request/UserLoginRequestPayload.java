package com.example.api.models.request;

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
        return new UserLoginRequestPayload("srijith.seetha@gmail.com", "8R2diOE7h3rqME");
    }
}
