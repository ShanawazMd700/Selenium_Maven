package Pages;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
public class submitform
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;
    private String fullNameValue;
    private String emailValue;
    private String currentAddressValue;
    private String permanentAddressValue;

    public submitform(WebDriver driver) {
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
        this.driver = driver;
    }
    public locators locators;

    public void gotowebpage(String url)
    {
        controlhelper.navigateToUrl(url);
    }
    public void selectoption(String value)
    {
        controlhelper.scrollToElement(Locators.locators.selectOption(value));
        controlhelper.SafeClick(Locators.locators.selectOption(value));
    }
    public void selectheader(String value)
    {
        controlhelper.SafeClick(Locators.locators.selectSliderOption(value));
    }
    public void entername(String value)
    {
        WebElement ele = waithelper.waitForElement(locators.fullname);
        controlhelper.enterText(ele, value);
        fullNameValue = value;
    }
    public void enteremail(String value)
    {
        WebElement ele = waithelper.waitForElement(locators.emailbox);
        controlhelper.enterText(ele, value);
        emailValue = value;
    }
    public void enteraddress(String address)
    {
        WebElement ele = waithelper.waitForElement(locators.currentaddress);
        controlhelper.enterText(ele, address);
        currentAddressValue = address;
    }
    public void enterpermanentaddress(String value)
    {
        WebElement ele = waithelper.waitForElement(locators.permanentaddress);
        controlhelper.enterText(ele, value);
        permanentAddressValue = value;
    }

    public void validateTexts() {
        controlhelper.scrollToElement(locators.emailbox);
        controlhelper.SafeClick(locators.submitButton); // <-- ensure submitButton is in locators

        controlhelper.validateText(locators.fullname, fullNameValue);
        controlhelper.validateText(locators.emailbox, emailValue);
        controlhelper.validateText(locators.currentaddress, currentAddressValue);
        controlhelper.validateText(locators.permanentaddress, permanentAddressValue);
    }

}
