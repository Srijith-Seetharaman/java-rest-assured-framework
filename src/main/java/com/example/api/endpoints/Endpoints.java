package com.example.api.endpoints;

public enum Endpoints {

    LOGIN("/auth/login");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
