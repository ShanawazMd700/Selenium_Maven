package Hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.Status;
import Driver._drivers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("🚀 Launching browser for scenario: " + scenario.getName());

        ExtentTestManager.startTest(scenario.getName(), "Scenario Execution");

        WebDriverManager.chromedriver().setup();

        // ✅ Ensure downloads folder exists before Chrome starts
        File downloads = new File(System.getProperty("user.dir") + File.separator + "downloads");
        if (!downloads.exists()) downloads.mkdirs();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-debugging-port=9222");

        // ✅ Stable download configuration for CI
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloads.getAbsolutePath());
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        _drivers.setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                ExtentTestManager.getTest().log(Status.FAIL, "❌ Scenario Failed: " + scenario.getName());
                ExtentTestManager.getTest().addScreenCaptureFromPath(takeScreenshot(scenario));
            } else {
                ExtentTestManager.getTest().log(Status.PASS, "✅ Scenario Passed: " + scenario.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (driver != null) driver.quit();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String stepText = scenario.getName();
        if (scenario.isFailed()) {
            ExtentTestManager.getTest().fail("❌ Step Failed: " + stepText);
        } else {
            ExtentTestManager.getTest().pass("✅ Step Passed: " + stepText);
        }
    }

    private String takeScreenshot(Scenario scenario) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");
        String path = ExtentManager.getReportFolderPath() + "/" + safeName + ".png";

        try {
            File destFile = new File(path);
            destFile.getParentFile().mkdirs();
            Files.copy(srcFile.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @AfterAll
    public static void afterAll() {
        ExtentManager.getInstance().flush();
    }
}
