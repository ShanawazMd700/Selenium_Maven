package Pages;
import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
public class dynamicInteraction
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    public dynamicInteraction()
    {
        WebDriver driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }

    public void validateTheButtonColor(String button, String expectedColor) {
        WebElement buttonElement = waithelper.waitForElement(locators.butttontext(button));

        // get CSS 'color' property (text color)
        String actualColor = buttonElement.getCssValue("color");

        String normalizedExpected;

        // If user passed hex (e.g. "#dc3545") or a named color ("red")
        if (expectedColor.startsWith("#") || !expectedColor.startsWith("rgba")) {
            // Convert from HEX or name → rgba
            Color color = Color.fromString(expectedColor);
            normalizedExpected = color.asRgba();
        } else {
            // already rgba
            normalizedExpected = expectedColor;
        }

        Assert.assertEquals(actualColor, normalizedExpected,
                "Expected button color " + normalizedExpected + " but got " + actualColor);
    }

    public void pause(int timeInSeconds)
    {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
