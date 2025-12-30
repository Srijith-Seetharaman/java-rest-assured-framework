package com.example.api.endpoints;

import lombok.Getter;

@Getter
public enum Endpoints {

    LOGIN("/auth/login"),
    ADD_PRODUCT("/product/add-product"),
    CREATE_ORDER("/order/create-order"),
    GET_ORDER_DETAILS("/order/get-orders-details"),
    DELETE_ORDER("/order/delete-order/"),
    DELETE_PRODUCT("/product/delete-product/");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

}
