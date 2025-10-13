package Pages;

import Driver._drivers;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.time.Duration;

public class uploadDownload {
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;

    public uploadDownload() {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }

    public void uploadFile(String path) {
        File file;

        if (new File(path).isAbsolute()) {
            file = new File(path);
        } else {
            file = new File(System.getProperty("user.dir"), path);
        }

        // Ensure dummy file exists in CI
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("✅ Dummy upload file created: " + file.getAbsolutePath());
            } catch (Exception e) {
                throw new RuntimeException("❌ Failed to create file: " + file.getAbsolutePath(), e);
            }
        }

        // Wait for upload input and send file path
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement uploadElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFile")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadElement);
            wait.until(ExpectedConditions.elementToBeClickable(uploadElement));

            uploadElement.sendKeys(file.getAbsolutePath());
            System.out.println("✅ File uploaded successfully: " + file.getAbsolutePath());

        } catch (TimeoutException e) {
            throw new RuntimeException("❌ Upload input (#uploadFile) not found or not clickable after waiting.", e);
        }
    }



    public void clickdownloadfile() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locators.downloadButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            button.click();
            System.out.println("✅ Download button clicked successfully.");
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click Download button.", e);
        }
    }


    /** ✅ Waits for file in project downloads folder */

    public void VerifyFileDownloaded(String fileName) {
        String downloadPath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator + fileName;
        File downloadedFile = new File(downloadPath);

        System.out.println("🔍 Checking for downloaded file at: " + downloadPath);

        int retries = 60; // 60 seconds max
        while (retries-- > 0 && !downloadedFile.exists()) {
            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();

        Assert.assertTrue(downloadedFile.exists(),
                "❌ File '" + fileName + "' was not downloaded. Checked path: " + downloadPath);

        System.out.println("✅ File downloaded successfully: " + downloadedFile.getAbsolutePath());
    }

    public void verifyUploadSuccess(String expectedText) {
        String actualText = controlhelper.getText(locators.uploadResponse);
        Assert.assertTrue(actualText.contains(expectedText),
                "❌ Expected text '" + expectedText + "' not found. Actual: '" + actualText + "'");
    }
}
