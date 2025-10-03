package Pages;
import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
public class brokenlink
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;

    public brokenlink() {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }
    public void clickonlink(String value) {
        controlhelper.SafeClick(locators.link1(value));
    }

    public void validatelink(String url) {
        WebDriver driver = _drivers.getDriver();   // always available
        String expectedUrl = url;
        waithelper.waitForElement(locators.homebanner); // example locator
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl,
                "Expected URL '" + expectedUrl + "' but got '" + actualUrl + "'");
    }
}
