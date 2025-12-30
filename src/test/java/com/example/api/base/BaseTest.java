package com.example.api.base;

import com.example.api.constants.Constants;
import com.example.api.filters.CustomLogFilter;
import com.example.api.listeners.TestResultListener;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;

@Listeners(TestResultListener.class)
public class BaseTest {

    @BeforeClass
    public void setupTest() {
        RestAssured.baseURI = Constants.baseUrl;
    }

    @org.testng.annotations.BeforeMethod
    public void beforeMethod() {
        CustomLogFilter.startTestLog();
    }

    @BeforeSuite
    public void setupFilters() {
        // Delete the old log file before the test run starts
        new File(Constants.logsSource).delete();

        // Add the custom filter
        RestAssured.filters(new CustomLogFilter());
    }

}
