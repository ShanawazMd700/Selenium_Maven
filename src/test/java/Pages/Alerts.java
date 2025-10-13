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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualText = alert.getText();
            Assert.assertEquals(actualText, expectedText, "Alert text does not match!");
            alert.accept();
        } catch (TimeoutException e) {
            throw new RuntimeException("⚠️ Alert not found to verify text: " + expectedText);
        }
    }

    public void enterTextInAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    public void clickAlertButton(String value) {
        controlhelper.SafeClick(locators.alert_button(value));
    }

    public void clickAlertButtonAndHandle(String buttonText, String inputText) {
        By buttonLocator = By.xpath("//span[contains(text(),'" + buttonText + "')]/ancestor::div[@class='mt-4 row']//button[text()='Click me']");
        controlhelper.SafeClick(buttonLocator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        System.out.println("Alert appeared: " + alertText);

        if (buttonText.toLowerCase().contains("prompt") && inputText != null) {
            alert.sendKeys(inputText);
        }
        alert.accept();
    }


    public void entertextAlert(String value) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(value);
        alert.accept();
    }
}
