package ui.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public void click(WebElement element){
        element.click();
    }

    public void enterValue(WebElement element, CharSequence value){
        element.clear();
        element.sendKeys(value);
    }

    public void waitUntilElementIsVisible(By locator){
        new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("wait.timeout")))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
