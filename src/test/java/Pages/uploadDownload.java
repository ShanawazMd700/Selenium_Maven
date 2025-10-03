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

    public void uploadFile(String path)
    {
        WebElement file = waithelper.waitForElement(locators.uploadButton);
        file.sendKeys(path);
    }
    public void clickdownloadfile()
    {
        controlhelper.SafeClick(locators.downloadButton);
    }
    public void VerifyFileDownloaded()
    {
        String downloadDir = "C:\\Users\\iray\\Downloads\\";
        String fileName = "sampleFile.jpeg";
        File filePath = new File(downloadDir, fileName);

        int retries = 30; // wait up to 30 seconds
        while (retries > 0 && !filePath.exists()) {
            try {
                Thread.sleep(1000); // wait 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            retries--;
        }

        Assert.assertTrue(filePath.exists(), "File '" + fileName + "' was not downloaded.");
    }


    // ✅ Verify upload success like in C#
    public void verifyUploadSuccess(String expectedText) {
        String actualText = controlhelper.getText(locators.uploadResponse);
        Assert.assertTrue(actualText.contains(expectedText),
                "Expected text '" + expectedText + "' not found in response: " + actualText);
    }
}
