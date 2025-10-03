package Pages;

import Driver._drivers;
import Locators.locators;
import org.openqa.selenium.*;
import Helpers.*;
import org.testng.Assert;

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

    public void clickalertButton(String value) {
        controlhelper.SafeClick(locators.alert_buttons(value));
    }

    public void entertextAlert(String value) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(value);
        alert.accept();
    }
}
