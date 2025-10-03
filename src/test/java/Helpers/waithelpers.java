package Helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;

public class waithelpers
{
    private final WebDriver driver;

    public waithelpers(WebDriver driver) {
        this.driver = driver;
    }
    public  WebElement waitForElement(By locator) {
        return waitForElement(locator, 20); // default 20 seconds
    }
    //Wait using By
    public WebElement waitForElement(By locator, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(element.isDisplayed(), "Element located by " + locator + " should be visible, but it is not.");
        return element;
    }
    public WebElement waitForElement(WebElement element) {
        return waitForElement(element, 20);
    }

    // Wait using WebElement, custom time
    public WebElement waitForElement(WebElement element, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(visibleElement.isDisplayed(), "Element should be visible, but it is not.");
        return visibleElement;
    }
}
