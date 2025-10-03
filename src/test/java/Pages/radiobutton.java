package Pages;
import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.testng.Assert;

public class radiobutton
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    public radiobutton() {
        WebDriver driver = _drivers.getDriver();   // fetch globally
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }
    public void selectRadiobutton(String value)
    {
        try {
            Thread.sleep(3000); // small wait (replace with proper wait if needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controlhelper.click(locators.radiobutton(value));
    }
    public void validateRadiobutton(String value) {
        WebElement radiobuttonElement = waithelper.waitForElement(locators.radiobuttonvalidationtext(value)
        );

        String actualText = radiobuttonElement.getText().trim();

        if (actualText.equalsIgnoreCase(value)) {
            System.out.println("Radio button '" + value + "' is selected.");
        } else {
            Assert.fail("Expected radio button '" + value + "' to be selected, but found: " + actualText);
        }
    }
}
