package Pages;
import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.support.ui.*;
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
    public void clickonlink(String linkText) {
        By linkLocator = By.xpath("//a[text()='" + linkText + "']");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkLocator));
            link.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("⚠️ Stale element found. Retrying...");
            WebElement link = driver.findElement(linkLocator);
            link.click();
        }
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
