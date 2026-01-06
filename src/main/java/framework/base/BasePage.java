package framework.base;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
        actions.click(locator);
    }

    protected void enterValue(By locator, CharSequence value) {
        actions.enterValue(locator, value);
    }

    protected String getText(By locator) {
        return actions.getText(locator);
    }

    protected List<WebElement> visibleElements(By locator) {
        return actions.visibleElements(locator);
    }

    // ===== navigation helpers =====

    protected void open(String url) {
        driver.get(url);
    }

}