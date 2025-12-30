package com.example.api.constants;

import com.example.api.utils.PropertiesReader;

import java.io.FileNotFoundException;

public class Constants {

    public static String logsSource;
    public static String baseUrl;
    public static String username;
    public static String password;

    static {
        try {
            PropertiesReader defaultProperties = new PropertiesReader("src/main/resources/default-config.properties");

            String environment = System.getProperty("env");
            PropertiesReader environmentProperties = new PropertiesReader(
                    "src/main/resources/" + environment + "-config.properties");

            // global constants
            logsSource = defaultProperties.getProperty("logsSource");

            // environment constants
            baseUrl = environmentProperties.getProperty("baseUrl");
            username = environmentProperties.getProperty("username");
            password = environmentProperties.getProperty("password");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
