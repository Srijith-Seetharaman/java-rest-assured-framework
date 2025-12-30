package com.example.api.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderDetailsResponsePayload {
    private GetOrderDetailsData data;
    private String message;
}
