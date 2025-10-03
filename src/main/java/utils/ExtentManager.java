package utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFolderPath;

    public static ExtentReports getInstance() {
        if (extent == null) {
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            reportFolderPath = System.getProperty("user.dir") + "/test-output/" + timestamp;
            File folder = new File(reportFolderPath);
            if (!folder.exists()) folder.mkdirs();

            String reportPath = reportFolderPath + "/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Shanawaz");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static String getReportFolderPath() {
        if (extent == null) getInstance();
        return reportFolderPath;
    }
}
