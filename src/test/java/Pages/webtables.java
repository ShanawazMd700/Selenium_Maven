package Pages;
import Driver._drivers;
import org.openqa.selenium.*;
import Helpers.*;
import Locators.locators;
import org.testng.Assert;

public class webtables
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    public webtables() {
        WebDriver driver = _drivers.getDriver();   // fetch globally
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }
    public void clickbuton(String btntxt)
    {
        controlhelper.scrollToElement(locators.butttontext(btntxt));
        controlhelper.SafeClick(locators.butttontext(btntxt));
    }

    public void fillfirstname(String fname)
    {
        controlhelper.enterText(locators.firstname,fname);
    }
    public void filllastname(String lname)
    {
        controlhelper.enterText(locators.lastname,lname);
    }
    public void fillemail(String email)
    {
        controlhelper.enterText(locators.email,email);
    }
    public void fill_age(String age)
    {
        controlhelper.enterText(locators.age,age);
    }
    public void fillsalary(String salary)
    {
        controlhelper.enterText(locators.salary, salary);
    }
    public void fill_department(String dept)
    {
        controlhelper.enterText(locators.department, dept);
    }
    public void clickSubmitbutton()
    {
        controlhelper.SafeClick(locators.submitbutton);
    }

    public void validate_firstname(String value)
    {
        WebElement exname = waithelper.waitForElement(locators.textvalidation(value));
        String expectedName = controlhelper.getText((WebElement) exname);
        Assert.assertEquals(expectedName, value,"The texts are not equal....");
    }
}
