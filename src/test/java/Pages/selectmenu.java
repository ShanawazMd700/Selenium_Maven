package Pages;

import Driver._drivers;
import Locators.locators;
import org.openqa.selenium.*;
import Helpers.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class selectmenu
{
    private final controlhelpers controlhelper;
    private final waithelpers waithelper;
    private final WebDriver driver;
    private String Month;
    public selectmenu()
    {
        this.driver = _drivers.getDriver();
        this.controlhelper = new controlhelpers(driver);
        this.waithelper = new waithelpers(driver);
    }

    public void selectDropdownOption(String optionText) {
        controlhelper.SafeClick(locators.firstDropdown);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(By.xpath("//div[contains(@class,'css-1uccc91-singleValue') " +
                "or (contains(@class,'css-1n7v3ny-option') and text()='" + optionText + "')]")));
        WebElement option = driver.findElement(By.xpath("//div[contains(@class,'css-1n7v3ny-option') " +
                "and text()='" + optionText + "']"));
        option.click();
    }

    public void verifySelectedOption(String option) {
        WebElement dropdownSelectOption = driver.findElement(By.xpath(
                "(//div[contains(@class,'css-1uccc91-singleValue') and text()='" + option + "'])[1]"));
        String actText = controlhelper.getText(dropdownSelectOption);
        Assert.assertEquals(actText, option, "The selected option does not match the expected option.");
    }

    public void selectSecondOption(String optionText) {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, #adplus-anchor').forEach(e => e.remove());"
        );
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Click the dropdown to expand options
        WebElement dropdown = driver.findElement(By.id("selectOne"));
        dropdown.click();

        // Wait for the desired option to appear
        WebElement option = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class,'option') and text()='" + optionText + "']")));

        option.click();
    }


    public void selectStandardOption(String option) {
        WebElement dropdownElement = waithelper.waitForElement(locators.standardDropdown);
        Select selectElement = new Select(dropdownElement);
        selectElement.selectByVisibleText(option);
    }

    public void verifyStandardOption(String option) {
        WebElement dropdownElement = waithelper.waitForElement(locators.standardDropdown);
        Select selectElement = new Select(dropdownElement);
        String selectedOption = selectElement.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption, option,
                "The selected option does not match the expected option.");
    }
    public void selectMultiDropdownOptions(String... optionTexts) {
        WebDriver driver = _drivers.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By dropdownLocator = By.xpath("(//div[contains(@class,'css-1hwfws3')])[3]");
        controlhelper.scrollToElement(dropdownLocator);

        for (String optionText : optionTexts) {
            By optionLocator = By.xpath(
                    "//div[contains(@class,'css-1n7v3ny-option') and normalize-space(text())='" + optionText + "']");

            // Ensure the dropdown is open. If clicking once didn't keep it open, we re-click before trying.
            try {
                WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
                dropdown.click();
            } catch (Exception e) {
                // If dropdown cannot be clicked right now, we'll still attempt to find option below (it might already be open).
            }

            // Wait for option presence/visibility then try clicking with retries
            wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));

            boolean clicked = false;
            int attempts = 0;
            while (!clicked && attempts < 4) {
                attempts++;
                try {
                    // locate fresh each attempt
                    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
                    option.click();
                    clicked = true;
                } catch (StaleElementReferenceException sere) {
                    // DOM refreshed: reopen dropdown and retry
                    try {
                        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
                        dropdown.click();
                    } catch (Exception ignore) {}
                } catch (ElementClickInterceptedException eci) {
                    // Try JS click as fallback
                    try {
                        WebElement option = driver.findElement(optionLocator);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                        clicked = true;
                    } catch (Exception ex) {
                        // swallow and retry
                    }
                } catch (TimeoutException te) {
                    // Option not clickable yet; re-open dropdown and retry
                    try {
                        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
                        dropdown.click();
                    } catch (Exception ignore) {}
                }
            }

            if (!clicked) {
                throw new RuntimeException("Failed to select option '" + optionText + "' after retries");
            }

            // brief pause to let UI settle (avoid tight-loop issues). Replace with smarter wait if you can.
            try { Thread.sleep(200); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
        }

        // close the dropdown if it is still open (best-effort)
        try {
            WebElement dropdownClose = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
            dropdownClose.click();
        } catch (Exception ignore) {}
    }
    public void verifyselectedfromdropdown(String value)
    {
        String exText = controlhelper.getText(locators.dropdownOptions(value));
        Assert.assertEquals(exText,value,"The"+value+" is not selected ");
    }
}
