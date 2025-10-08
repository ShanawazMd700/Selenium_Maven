package Helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class controlhelpers
{
    private final waithelpers waitHelpers;
    private final WebDriver driver;

    public controlhelpers(WebDriver driver) {
        this.driver = driver;
        this.waitHelpers = new waithelpers(driver);
    }

    public controlhelpers(waithelpers waitHelpers, WebDriver driver) {
        this.waitHelpers = waitHelpers;
        this.driver = driver;
    }

    public void SafeClick(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, #adplus-anchor').forEach(e => e.remove());"
        );

        waitHelpers.waitForElement(locator).click();
    }

    public void doubleClick(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, #adplus-anchor').forEach(e => e.remove());"
        );

        WebElement element = waitHelpers.waitForElement(locator);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    public void rightClick(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, #adplus-anchor').forEach(e => e.remove());"
        );

        WebElement element = waitHelpers.waitForElement(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    public void enterText(By locator, String text) {
        WebElement element = waitHelpers.waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }
    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }


    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    public String getText(By locator) {
        WebElement element = waitHelpers.waitForElement(locator);
        return element.getText();
    }


    public void validateText(By locator, String expectedText) {
        WebElement element = waitHelpers.waitForElement(locator);
        String actualText;
        String tagName = element.getTagName().toLowerCase();

        if (tagName.equals("input") || tagName.equals("textarea")) {
            actualText = element.getAttribute("value");
        } else {
            actualText = element.getText();
        }

        Assert.assertTrue(
                actualText.contains(expectedText),
                "Expected text '" + expectedText + "' not found in actual text '" + actualText + "'"
        );
    }

    public void click(By locator) {
        WebElement element = waitHelpers.waitForElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element
        );
        element.click();
    }

    public void scrollToElement(By locator) {
        WebElement element = waitHelpers.waitForElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitFor(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = waitHelpers.waitForElement(sourceLocator);
        WebElement target = waitHelpers.waitForElement(targetLocator);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }

    public void resizeElement(By handleLocator, int xOffset, int yOffset) {
        WebElement handle = waitHelpers.waitForElement(handleLocator);
        Actions actions = new Actions(driver);
        actions.clickAndHold(handle)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void hoverOverElement(By locator) {
        WebElement element = waitHelpers.waitForElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    //new methods
    public void hoverAndClick(By menuLocator, By subMenuLocator) {
        // Hover over the main menu
        WebElement menu = waitHelpers.waitForElement(menuLocator);
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();

        // Wait for submenu to be visible
        WebElement subMenu = waitHelpers.waitForElement(subMenuLocator);

        // Click the submenu
        subMenu.click();
    }

    public void hoverAndClick(WebElement menu, WebElement subMenu) {
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();

        // Optional: wait if needed
        waitHelpers.waitForElement(subMenu);

        subMenu.click();
    }



}
