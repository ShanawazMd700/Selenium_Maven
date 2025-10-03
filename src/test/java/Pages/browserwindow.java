package Pages;
import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.time.Duration;

public class browserwindow
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;
    private String Month;
    public browserwindow()
    {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }
    public void validateNewTab(String value) {
        WebDriver driver = _drivers.getDriver();
        String originalWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > 1);
        String newWindowHandle = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(originalWindow))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New window not found"));
        driver.switchTo().window(newWindowHandle);
        WebElement headerElement = waithelper.waitForElement(locators.newtabHeader(value));
        Assert.assertEquals(headerElement.getText(), value,
                "Expected header text '" + value + "' but got '" + headerElement.getText() + "'");
        driver.close();
        driver.switchTo().window(originalWindow);
    }

}
