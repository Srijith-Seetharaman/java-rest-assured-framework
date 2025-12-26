package com.example.api.endpoints;

import lombok.Getter;

@Getter
public enum Endpoints {

    LOGIN("/auth/login"),
    ADD_PRODUCT("product/add-product");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

}
