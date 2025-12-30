package com.example.api.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequestPayload {
    private List<OrderDetail> orders;
}
