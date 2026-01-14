package framework.base;

import framework.config.Config;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class ElementActions {

    private final int maxRetries = Config.getInt("attempts.max");
    private static final Logger log = LoggerFactory.getLogger(ElementActions.class);

    void click(By locator) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WaitUtils.getElementIfClickable(locator).click();
                log.debug("Clicked {}", locator);
                return;
            } catch (ElementClickInterceptedException |
                     StaleElementReferenceException |
                     TimeoutException e) {
                log.debug("Click attempt {}/{} failed for {} ({})",
                        attempt, maxRetries, locator, e.getClass().getSimpleName());
                if (attempt == maxRetries) {
                    log.warn("Fallback JS click used for {}", locator);
                    new JsActions().jsClick(locator);
                }
            }
        }
    }

    void enterValue(By locator, CharSequence value) {
        WebElement element = WaitUtils.getElementIfVisible(locator);
        element.clear();
        element.sendKeys(value);
        log.debug("Entered value into {}", locator);
    }

    // Multiple elements interactions will be added once needed
    List<WebElement> visibleElements(By locator) {
        log.debug("Retrieved all elements matching {} locator", locator);
        return WaitUtils.getAllVisibleElement(locator);
    }

    String getText(By locator) {
        log.debug("Retrieved text for {}", locator);
        return WaitUtils.getElementIfVisible(locator).getText();
    }

    String waitForTextToChange(By locator) {
        return WaitUtils.waitForTextToChange(locator);
    }

}
