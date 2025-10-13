package Helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class controlhelpers {
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

    /** ✅ Fully robust click with retries and JS fallback */
    public void SafeClick(By locator) {
        removeAds();
        WebElement element = waitHelpers.waitForElement(locator);

        int attempts = 3;
        while (attempts > 0) {
            try {
                element.click();
                return;
            } catch (ElementClickInterceptedException e) {
                handleClickException(element);
            } catch (ElementNotInteractableException e) {
                handleClickException(element);
            }
            catch (UnhandledAlertException e) {
                try {
                    Alert alert = driver.switchTo().alert();
                    System.out.println("⚠️ Unhandled alert found before clicking: " + alert.getText());
                    alert.accept(); // or alert.dismiss();
                } catch (NoAlertPresentException ignored) {}

                // retry click after alert handled
                element = waitHelpers.waitForElement(locator);
                element.click();
                return;
            }

            waitFor(1);
            attempts--;
        }
        throw new RuntimeException("❌ Failed to click element: " + locator);
    }

    private void handleClickException(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ignored) { }
    }

    public void click(By locator) {
        WebElement element = waitHelpers.waitForElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
        SafeClick(locator);
    }

    /** ✅ Reliable double-click with fallback */
    public void doubleClick(By locator) {
        removeAds();
        WebElement element = waitHelpers.waitForElement(locator);

        int attempts = 2;
        while (attempts > 0) {
            try {
                new Actions(driver).doubleClick(element).perform();
                return;
            } catch (ElementClickInterceptedException e) {
                handleDoubleClickFallback(element);
            } catch (ElementNotInteractableException e) {
                handleDoubleClickFallback(element);
            }
            waitFor(1);
            attempts--;
        }
        throw new RuntimeException("❌ Failed to double-click element: " + locator);
    }

    private void handleDoubleClickFallback(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "var evt = new MouseEvent('dblclick', {bubbles:true,cancelable:true}); arguments[0].dispatchEvent(evt);",
                element);
    }

    /** ✅ Robust right-click with JS fallback */
    public void rightClick(By locator) {
        removeAds();
        WebElement element = waitHelpers.waitForElement(locator);

        int attempts = 2;
        while (attempts > 0) {
            try {
                new Actions(driver).contextClick(element).perform();
                return;
            } catch (ElementClickInterceptedException e) {
                handleRightClickFallback(element);
            } catch (ElementNotInteractableException e) {
                handleRightClickFallback(element);
            }
            waitFor(1);
            attempts--;
        }
        throw new RuntimeException("❌ Failed to right-click element: " + locator);
    }

    private void handleRightClickFallback(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
        ((JavascriptExecutor) driver).executeScript(
                "var evt = new MouseEvent('contextmenu', {bubbles:true,cancelable:true}); arguments[0].dispatchEvent(evt);",
                element);
    }

    public void enterText(By locator, String text) {
        WebElement element = waitHelpers.waitForElement(locator);
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
    public String getText(WebElement element) {
        return element.getText();
    }


    public void validateText(By locator, String expectedText) {
        WebElement element = waitHelpers.waitForElement(locator);
        String actualText = element.getTagName().matches("input|textarea")
                ? element.getAttribute("value") : element.getText();

        Assert.assertTrue(
                actualText.contains(expectedText),
                "❌ Expected text '" + expectedText + "' not found. Actual: '" + actualText + "'");
    }

    public void waitFor(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void removeAds() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll('iframe, #adplus-anchor').forEach(e => e.remove());");
        } catch (Exception ignored) {}
    }
    public void scrollToElement(By locator) {
        WebElement element = waitHelpers.waitForElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void SafeClick(WebElement  locator) {
        removeAds();
        WebElement element = waitHelpers.waitForElement(locator);

        int attempts = 3;
        while (attempts > 0) {
            try {
                element.click();
                return;
            } catch (ElementClickInterceptedException e) {
                handleClickException(element);
            } catch (ElementNotInteractableException e) {
                handleClickException(element);
            }
            waitFor(1);
            attempts--;
        }
        throw new RuntimeException("❌ Failed to click element: " + locator);
    }


}
