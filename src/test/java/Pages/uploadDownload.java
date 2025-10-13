package Pages;

import Driver._drivers;
import Helpers.*;
import Locators.locators;
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

    /** ✅ Upload works both locally and in CI */
    public void uploadFile(String relativePath) {
        // Get the project base directory
        String projectPath = System.getProperty("user.dir");

        // Construct the full file path using the relative path
        File file = new File(projectPath, relativePath);

        // If file doesn’t exist in given relative path, try default resource folder
        if (!file.exists()) {
            file = new File(projectPath + File.separator + "src/test/resources/files/" + relativePath);
        }

        // Assert file existence before upload
        Assert.assertTrue(file.exists(), "❌ Upload file not found: " + file.getAbsolutePath());

        // Find the upload input element and upload the file
        WebElement uploadInput = waithelper.waitForElement(locators.uploadButton);
        uploadInput.sendKeys(file.getAbsolutePath());
    }


    public void clickdownloadfile() {
        controlhelper.SafeClick(locators.downloadButton);
    }

    /** ✅ Waits for file in project downloads folder */
    public void VerifyFileDownloaded() {
        String fileName = "sampleFile.jpeg";
        String downloadPath = System.getProperty("user.dir")
                + File.separator + "downloads" + File.separator + fileName;

        File downloadedFile = new File(downloadPath);
        int retries = 30;
        while (retries-- > 0 && !downloadedFile.exists()) {
            try {
                Thread.sleep(1000);
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
