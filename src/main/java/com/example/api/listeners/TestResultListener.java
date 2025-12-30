package com.example.api.listeners;

import com.example.api.constants.Constants;
import com.example.api.filters.CustomLogFilter;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        if (CustomLogFilter.hasLog()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(Constants.logsSource, true))) {
                writer.println("==================== FAILURE LOG ====================");
                writer.println("Test: " + result.getMethod().getMethodName());
                writer.println("Reason: " + result.getThrowable().getMessage());
                writer.println();
                writer.println(CustomLogFilter.getLog());
                writer.println("=====================================================");
                writer.println();
            } catch (IOException e) {
                System.err.println("Failed to write failure log to file: " + e.getMessage());
            }
        }
    }
}
