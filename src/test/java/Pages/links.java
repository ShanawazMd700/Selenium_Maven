package Pages;
import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.*;

public class links
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;

    public links() {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }
    public void clickHome() {
        controlhelper.SafeClick(locators.links_link1);
    }

    public void clickHome2() {
        controlhelper.SafeClick(locators.links_link2);
    }

    public void validateClickHome1(String expectedURL)
    {
        String expectedUrl = expectedURL;
        // Store the original window handle
        String originalWindow = driver.getWindowHandle();

        // Wait until a new window/tab is opened
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > 1);

        // Switch to the new window/tab
        Set<String> windowHandles = driver.getWindowHandles();
        String newWindowHandle = windowHandles.stream()
                .filter(handle -> !handle.equals(originalWindow))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New window not found"));
        driver.switchTo().window(newWindowHandle);

        // Validate the URL
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl,
                "Expected URL '" + expectedUrl + "' but got '" + actualUrl + "'");

        // Close the new tab
        driver.close();

        // Switch back to the original tab
        driver.switchTo().window(originalWindow);
    }

    public void selectLinkOptions(String value) {
        controlhelper.SafeClick(Locators.locators.linkoptions(value));
    }

    public void validateTheClick(String value) {
        controlhelper.validateText(locators.link_response, value);
    }

}
