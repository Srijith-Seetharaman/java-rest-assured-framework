package com.example.api.endpoints;

import lombok.Getter;

@Getter
public enum Endpoints {

    LOGIN("/auth/login");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

}
