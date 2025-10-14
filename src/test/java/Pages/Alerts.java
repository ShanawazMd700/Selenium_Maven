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
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String actualText = alert.getText();
            System.out.println("✅ Alert found: " + actualText);

            if (!actualText.equals(expectedText)) {
                throw new RuntimeException("❌ Expected: '" + expectedText + "', but got: '" + actualText + "'");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("⚠️ Alert not found to verify text: " + expectedText);
        }
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
