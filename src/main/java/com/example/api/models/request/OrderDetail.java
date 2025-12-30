package com.example.api.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
    private String country;
    private String productOrderedId;
}
