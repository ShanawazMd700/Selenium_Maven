package Pages;
import Driver._drivers;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.File;

public class uploadDownload
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;
    public uploadDownload() {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }

    public void uploadFile(String relativePath) {
        String absolutePath = new File("src/test/resources/files/" + relativePath).getAbsolutePath();
        WebElement file = waithelper.waitForElement(locators.uploadButton);
        file.sendKeys(absolutePath);
    }

    public void clickdownloadfile()
    {
        controlhelper.SafeClick(locators.downloadButton);
    }
    public void VerifyFileDownloaded()
    {
        String fileName = "sampleFile.jpeg";
        String downloadPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + fileName;

        File downloadedFile = new File(downloadPath);

        int retries = 30; // wait up to 30 seconds
        while (retries > 0 && !downloadedFile.exists()) {
            try {
                Thread.sleep(1000); // wait 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for file download", e);
            }
            retries--;
        }

        Assert.assertTrue(downloadedFile.exists(), "File '" + fileName + "' was not downloaded.");
    }


    // ✅ Verify upload success like in C#
    public void verifyUploadSuccess(String expectedText) {
        String actualText = controlhelper.getText(locators.uploadResponse);
        Assert.assertTrue(actualText.contains(expectedText),
                "Expected text '" + expectedText + "' not found in response: " + actualText);
    }
}
