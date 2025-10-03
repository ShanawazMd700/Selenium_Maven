package Pages;

import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.testng.Assert;


public class buttons
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    public buttons() {
        WebDriver driver = _drivers.getDriver();   // fetch globally
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }

    public void doubleclickbutton(String button)
    {
        controlhelper.doubleClick(Locators.locators.butttontext(button));
    }
    public void rightclick(String button)
    {
        controlhelper.rightClick(Locators.locators.butttontext(button));
    }
    public void validatebuttonaction(String exmessage)
    {
        WebElement messageElement = waithelper.waitForElement(locators.buttonActionText(exmessage));
        String actualMessage = messageElement.getText();
        Assert.assertEquals(actualMessage, exmessage,"Expected message '" + exmessage + "' but found '" + actualMessage + "'");
    }
}
