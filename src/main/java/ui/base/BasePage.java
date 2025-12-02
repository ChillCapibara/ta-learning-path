package ui.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

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

    public FluentWait<WebDriver> fluentWait(int waitTimeSeconds, int pollingTimeMillis){
       return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(waitTimeSeconds))
                .pollingEvery(Duration.ofMillis(pollingTimeMillis))
                .ignoring(NoSuchElementException.class)
               .ignoring(StaleElementReferenceException.class);
    }

    public void waitUntilElementIsLoaded(WebElement element){
        fluentWait(5, 200).until(d -> {
            try{
                return element.isDisplayed();
            } catch (Exception e){
                return false;
            }
        });
    }
}
