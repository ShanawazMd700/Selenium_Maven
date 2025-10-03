package Pages;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;

public class checkbox {
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;

    public checkbox(WebDriver driver) {
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
        this.driver = driver;
    }

    // select checkbox by label
    public void selectcheckbox(String value) {
        controlhelper.click(locators.checkboxWithLabel(value));
    }

    // select toggle button by index
    public void selectTogglebutton(String value) {
        controlhelper.SafeClick(locators.checkboxToggle(value));
    }

    // check if a checkbox is in "checked" state
    public boolean isCheckedstate(String value) {
        WebElement checkboxElement = waithelper.waitForElement(locators.checkboxWithLabel(value));
        String classValue = checkboxElement.getAttribute("class");
        return classValue != null && classValue.contains("check");
    }
}
