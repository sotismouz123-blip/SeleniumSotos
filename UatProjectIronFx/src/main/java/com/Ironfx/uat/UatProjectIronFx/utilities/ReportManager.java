package com.Ironfx.uat.UatProjectIronFx.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport() {
        // Δημιουργία timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        // Προσθήκη στο όνομα του report
        String reportPath = "C:\\Users\\smouzoulas\\Desktop\\nnnn\\UatProjectIronFx\\Reports\\TestReport_" 
                            + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Convenience wrappers
    public static void logInfo(String message) {
        test.info(message);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(String message) {
        test.fail(message);
    }
}
