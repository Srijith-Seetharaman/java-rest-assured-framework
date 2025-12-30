package com.example.api.models.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderResponsePayload {
    private List<String> orders;
    private List<String> productOrderId;
    private String message;
}
