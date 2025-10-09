package Hooks;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.Status;
import Driver._drivers;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions; // Add this at the top

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Launching browser in CI-safe mode...");
        System.out.println("Launching browser...");

        // Start Extent Test for this scenario
        ExtentTestManager.startTest(scenario.getName(), "Scenario Execution");

        // Browser setup
        WebDriverManager.chromedriver().setup();

        // ✅ Chrome Options for CI (GitHub Actions)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Use headless mode (new headless mode for Chrome 109+)
        options.addArguments("--no-sandbox"); // Required in CI
        options.addArguments("--disable-dev-shm-usage"); // Avoid shared memory issues
        options.addArguments("--disable-gpu"); // Disable GPU (not needed in CI)
        options.addArguments("--window-size=1920,1080"); // Optional - set default size
        options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis()); // Unique profile per run
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions"); // disable ad extensions
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");


        // Chrome download preferences
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloads");
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        _drivers.setDriver(driver);

    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                ExtentTestManager.getTest().log(Status.FAIL, "Scenario Failed: " + scenario.getName());
                ExtentTestManager.getTest().addScreenCaptureFromPath(takeScreenshot(scenario));
            } else {
                ExtentTestManager.getTest().log(Status.PASS, "Scenario Passed: " + scenario.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (driver != null) driver.quit();
    }

    @AfterStep
    public void afterStep(Scenario scenario)
    {
        // Get step type and name
        String stepText = scenario.getName();
        String stepType = scenario.getStatus().toString(); // pseudocode
        if (scenario.isFailed()) {
            ExtentTestManager.getTest().fail("Step Failed: " + stepText
            );
        } else {
            ExtentTestManager.getTest().pass("Step Passed: " + stepText);
        }
    }
    private String takeScreenshot(Scenario scenario) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Replace special characters in scenario name
        String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9\\-_]", "_");

        // Save screenshot in the same folder as Extent report
        String path = ExtentManager.getReportFolderPath() + "/" + safeName + ".png";

        try {
            File destFile = new File(path);
            destFile.getParentFile().mkdirs(); // ensure folder exists
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
