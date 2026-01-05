package framework.base;

import framework.config.Config;
import org.openqa.selenium.*;

import java.util.List;

class ElementActions {

    private final int maxRetries = Config.getInt("attempts.max");

    void click(By locator) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WaitUtils.getElementIfClickable(locator).click();
                return;
            } catch (ElementClickInterceptedException |
                     StaleElementReferenceException |
                     TimeoutException e) {

                if (attempt == maxRetries) {
                    System.out.println("Fallback JS click used for: " + locator);
                    new JsActions().jsClick(locator);
                }
            }
        }
    }

    void enterValue(By locator, CharSequence value) {
        WebElement element = WaitUtils.getElementIfVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    // Multiple elements interactions will be added once needed
    List<WebElement> visibleElements(By locator) {
        return WaitUtils.getAllVisibleElement(locator);
    }

    String getText(By locator) {
        return WaitUtils.getElementIfVisible(locator).getText();
    }
}
