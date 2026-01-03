package com.szymon.ta.base;

import org.openqa.selenium.*;
import com.szymon.ta.utils.Config;
import com.szymon.ta.utils.WaitUtils;

import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    private final int maxRetries = Config.getInt("attempts.max");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected void click(By locator) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WaitUtils.getElementIfClickable(locator).click();
                return;
            } catch (ElementClickInterceptedException | StaleElementReferenceException | TimeoutException e) {
                if (attempt == maxRetries) {
                    System.out.println("Fallback JS click used for: " + locator);
                    jsClick(locator);
                }
            }
        }
    }

    protected void enterValue(By locator, CharSequence value) {
        WebElement element = WaitUtils.getElementIfVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    // Multiple elements interactions will be added once needed
    protected List<WebElement> visibleElements(By locator) {
        return WaitUtils.getAllVisibleElement(locator);
    }

    protected String getText(By locator){
        return WaitUtils.getElementIfVisible(locator).getText();
    }

    private void jsClick(By locator) {
        WebElement el = WaitUtils.getElementIfVisible(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", el);
    }
}