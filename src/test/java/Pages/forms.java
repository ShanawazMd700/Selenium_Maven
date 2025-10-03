package Pages;

import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import Driver._drivers;
import java.time.Duration;



public class forms
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;
    private String Month;
    public forms()
    {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }
    public void fillname(String nametype, String value)
    {
        controlhelper.enterText(locators.name_registration(nametype),value);
    }
    public void enteremail(String email)
    {
        controlhelper.scrollToElement(locators.email);
        controlhelper.enterText(locators.email,email);
    }
    public void selectoption(String value)
    {
        controlhelper.SafeClick(locators.form_genderSelect(value));
    }
    public void enterNumber(String value)
    {
        controlhelper.enterText(locators.mobilenumber,value);
    }
    public void EnterSubjects(String value)
    {
       WebElement element= waithelper.waitForElement(locators.subjectinput);
        waithelper.waitForElement(locators.subjectinput);
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);
    }

    public void selectState(String state) {
        controlhelper.enterText(locators.state_input, state + Keys.ARROW_DOWN + Keys.TAB);
    }

    public void selectCity(String city) {
        controlhelper.enterText(locators.city_input, city + Keys.ARROW_DOWN + Keys.TAB);
    }
    public void verifyTheText(String expectedText) {
        String actualText = controlhelper.getText(locators.header_text(expectedText));
        Assert.assertTrue(actualText.contains(expectedText),
                "Expected text '" + expectedText + "' but found in header: " + actualText);
    }
    public void selectYearMonthDay(String year, String month, String day) {
        System.out.println(">>> Using selectYearMonthDay: " + year + " " + month + " " + day);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        controlhelper.SafeClick(locators.dob_box);

        WebElement yearDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locators.yearbase));
        new Select(yearDropdown).selectByVisibleText(year);

        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locators.monthbase));
        new Select(monthDropdown).selectByVisibleText(month);

        By dayLocator = By.xpath("//div[contains(@class,'react-datepicker__day') " +
                "and not(contains(@class,'outside-month')) and text()='" + day + "']");
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dayElement.click();
    }

}




