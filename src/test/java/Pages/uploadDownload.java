package Pages;

import Driver._drivers;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.File;

public class uploadDownload {
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;

    public uploadDownload() {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }

    public void uploadFile(String relativePath) {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, relativePath.replace("\\", "/"));

        // Create dummy file in CI if missing
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                if (file.createNewFile()) {
                    System.out.println("✅ Dummy upload file created for CI: " + file.getAbsolutePath());
                }
            } catch (Exception e) {
                throw new RuntimeException("❌ Cannot create file for upload: " + file.getAbsolutePath(), e);
            }
        }

        Assert.assertTrue(file.exists(), "❌ Upload file not found: " + file.getAbsolutePath());
        WebElement uploadElement = driver.findElement(By.id("uploadFile"));
        uploadElement.sendKeys(file.getAbsolutePath());
    }



    public void clickdownloadfile() {
        controlhelper.SafeClick(locators.downloadButton);
    }

    /** ✅ Waits for file in project downloads folder */

    public void VerifyFileDownloaded() {
        String fileName = "sampleFile.jpeg"; // name of the file you expect
        String downloadPath = System.getProperty("user.dir")
                + File.separator + "downloads" + File.separator + fileName;

        File downloadedFile = new File(downloadPath);
        int retries = 30;

        while (retries-- > 0 && !downloadedFile.exists()) {
            try {
                Thread.sleep(1000); // wait 1 second before retry
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for file download", e);
            }
        }

        Assert.assertTrue(downloadedFile.exists(),
                "❌ File '" + fileName + "' was not downloaded. Checked: " + downloadPath);
    }

    public void verifyUploadSuccess(String expectedText) {
        String actualText = controlhelper.getText(locators.uploadResponse);
        Assert.assertTrue(actualText.contains(expectedText),
                "❌ Expected text '" + expectedText + "' not found. Actual: '" + actualText + "'");
    }
}
