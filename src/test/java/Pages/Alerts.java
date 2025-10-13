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
        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
        Assert.assertEquals(actualText, expectedText, "Alert text does not match!");
        alert.accept();
    }

    public void enterTextInAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    public void clickAlertButton(String value) {
        controlhelper.SafeClick(locators.alert_button(value));
    }

    public void clickalertButton(String buttonText) {
        WebElement button = driver.findElement(By.xpath("//span[contains(text(),'" + buttonText + "')]/ancestor::div[@class='mt-4 row']//button[text()='Click me']"));
        controlhelper.SafeClick(button);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();

            // Log the alert text if you want
            System.out.println("Alert text: " + alert.getText());

            // Handle based on alert type
            if (buttonText.contains("confirm")) {
                alert.accept(); // click OK
            } else if (buttonText.contains("prompt")) {
                alert.sendKeys("Selenium");
                alert.accept();
            } else {
                alert.accept();
            }
        } catch (TimeoutException e) {
            System.out.println("No alert appeared after clicking " + buttonText);
        }
    }


    public void entertextAlert(String value) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(value);
        alert.accept();
    }
}
