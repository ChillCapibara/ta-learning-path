package framework.base;

import framework.driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

class JsActions {

    void jsClick(By locator) {
        WebElement el = WaitUtils.getElementIfVisible(locator);
        ((JavascriptExecutor) WebDriverManager.getDriver())
                .executeScript("arguments[0].click();", el);
    }

}
