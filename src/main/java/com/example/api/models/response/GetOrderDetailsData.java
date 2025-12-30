package com.example.api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderDetailsData {
    @JsonProperty("_id")
    private String id;
    private String orderById;
    private String orderBy;
    private String productOrderedId;
    private String productName;
    private String country;
    private String productDescription;
    private String productImage;
    private String orderPrice;
    @JsonProperty("__v")
    private int v;
}
