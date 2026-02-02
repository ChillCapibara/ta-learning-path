package framework.base;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Supplier;

public abstract class BasePage {

    protected final WebDriver driver;
    private final ElementActions actions;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ElementActions();
    }

    // ===== interaction facade =====

    protected void click(By locator) {
        log.debug("Click: {}", locator);
        try{
            actions.click(locator);
        } catch (Exception e){
            log.error("Click failed: {}", locator, e);
            throw e;
        }
    }

    protected void enterValue(By locator, CharSequence value) {
        log.debug("Enter: {}, into field: {}", value, locator);
        try{
            actions.enterValue(locator, value);
        } catch (Exception e){
            log.error("Enter {} into {} field failed", value, locator, e);
            throw e;
        }
    }

    protected String getText(By locator) {
        log.debug("Retrieve text with {} locator", locator);
        try{
            return actions.getText(locator);
        } catch (Exception e){
            log.error("Get text failed: {}", locator, e);
            throw e;
        }
    }

    protected List<WebElement> visibleElements(By locator) {
        log.debug("Retrieve elements with {} locator", locator);
        try{
            return actions.visibleElements(locator);
        } catch (Exception e){
            log.error("Get list of elements with {} failed", locator, e);
            throw e;
        }
    }

    protected String waitForTextToChange(By locator) {
        log.debug("Wait for text to change: {}", locator);
        return actions.waitForTextToChange(locator);
    }

    // ===== navigation helpers =====

    protected void open(String url) {
        log.debug("Open url {}", url);
        try{
            driver.get(url);
        } catch (Exception e){
            log.error("Open url failed: {}", url, e);
            throw e;
        }
    }

    // ===== wait helpers =====

    protected void waitForSpinnerToDisappearIfPresent(By spinnerLocator){
        try{
            WaitUtils.waitForSpinnerToDisappearIfPresent(spinnerLocator);
        } catch (TimeoutException e){
            throw new TimeoutException(
                    "[SPINNER_TIMEOUT] spinner did not disappear: " + spinnerLocator, e);
        }
    }

    protected void waitUntil(String reason, Supplier<Boolean> condition){
        try{
            WaitUtils.waitUntil(condition);
        } catch (TimeoutException e){
            throw new TimeoutException("Wait timed out " + reason, e);
        }
    }
}