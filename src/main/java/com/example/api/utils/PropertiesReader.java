package com.example.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties = new Properties();
    FileInputStream fis;

    public PropertiesReader(String propertiesFileLocation) throws FileNotFoundException {
        this.fis = new FileInputStream(propertiesFileLocation);
        try {
            properties = new Properties();
            properties.load(this.fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file from ." + propertiesFileLocation);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}