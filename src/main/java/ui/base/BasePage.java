package ui.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private final int maxNumberOfRetries = Config.getInt("attempts.max");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("wait.timeout")));
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void click(WebElement element) {
        for (int attempt = 0; attempt < Math.max(1, maxNumberOfRetries); attempt++) {
            try {
                waitUntilElementIsClickable(element).click();
                return;
            } catch (ElementClickInterceptedException | StaleElementReferenceException | TimeoutException e) {
                // if last attempt -> try JS click fallback and rethrow if it fails
                if (attempt == Math.max(1, maxNumberOfRetries) - 1) {
                    try {
                        WebElement refreshed = waitUntilElementIsClickable(element);
                        jsClick(refreshed);
                        return;
                    } catch (RuntimeException ex) {
                        throw new RuntimeException("click() failed after " + maxNumberOfRetries + " attempts", ex);
                    }
                }
            }
        }
    }

    public void enterValue(WebElement element, CharSequence value) {
        element.clear();
        element.sendKeys(value);
    }

    public void waitUntilElementIsVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilElementIsClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
