package Pages;

import Driver._drivers;
import Locators.locators;
import org.openqa.selenium.*;
import Helpers.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Alerts
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;
    private String Month;
    public Alerts()
    {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }
    public void verifyAlertText(String expectedText) {
        Alert alert = waitForAlertWithRetry(3, 5); // retries x waitSeconds

        if (alert == null) {
            throw new RuntimeException("⚠️ Alert not found to verify text: " + expectedText);
        }

        String actualText = alert.getText().trim();
        System.out.println("✅ Alert found with text: " + actualText);
        Assert.assertEquals(actualText, expectedText, "Alert text does not match!");
        alert.accept();
    }

    // Retry logic to handle slower CI alerts
    private Alert waitForAlertWithRetry(int maxRetries, int waitSeconds) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
                return wait.until(ExpectedConditions.alertIsPresent());
            } catch (TimeoutException e) {
                System.out.println("⏳ Alert not present yet (retry " + (i + 1) + "/" + maxRetries + ")");
            }
        }
        return null;
    }

    public void enterTextInAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    public void clickAlertButton(String value) {
        try {
            By locator = locators.alert_button(value);
            controlhelper.SafeClick(locator);

            // Defensive wait for JS event binding in CI
            Thread.sleep(1000);

        } catch (UnhandledAlertException e) {
            System.out.println("⚠️ Alert appeared immediately after click: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click alert button: " + e.getMessage());
        }
    }

    public void clickAlertButtonAndHandle(String buttonText, String inputText) {
        By buttonLocator = By.xpath("//span[contains(text(),'" + buttonText + "')]/ancestor::div[@class='mt-4 row']//button[text()='Click me']");
        controlhelper.SafeClick(buttonLocator);

        Alert alert = waitForAlertWithRetry(3, 10);
        if (alert == null) {
            throw new RuntimeException("⚠️ Alert not found for button: " + buttonText);
        }

        String alertText = alert.getText();
        System.out.println("✅ Alert appeared: " + alertText);

        if (buttonText.toLowerCase().contains("prompt") && inputText != null) {
            alert.sendKeys(inputText);
        }
        alert.accept();
    }
}
