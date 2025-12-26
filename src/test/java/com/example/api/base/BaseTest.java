package com.example.api.base;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BaseTest {

    protected static PrintStream logFile;

    @BeforeClass
    public static void setupTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/api/ecom";
    }

    @BeforeSuite
    public void setupLogging() throws FileNotFoundException {

        logFile = new PrintStream(new FileOutputStream("target/api-tests.log", true));


        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig()
                        .defaultStream(logFile)
                        .enablePrettyPrinting(false));

        RestAssured.filters(RequestLoggingFilter.logRequestTo(logFile),
                ResponseLoggingFilter.logResponseTo(logFile));
    }

}
