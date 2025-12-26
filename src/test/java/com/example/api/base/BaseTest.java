package com.example.api.base;

import com.example.api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class BaseTest {

    protected static PrintStream logFile;

    @BeforeClass
    public void setupTest() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("base.url");
    }

    @BeforeSuite
    public void setupLogging() throws FileNotFoundException {

        logFile = new PrintStream(new FileOutputStream(ConfigReader.getProperty("logsSource"), true));

        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig()
                        .defaultStream(logFile)
                        .enablePrettyPrinting(false));

        RestAssured.filters(RequestLoggingFilter.logRequestTo(logFile),
                ResponseLoggingFilter.logResponseTo(logFile));
    }

}
