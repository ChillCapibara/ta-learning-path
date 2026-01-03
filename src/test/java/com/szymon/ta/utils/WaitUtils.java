package com.szymon.ta.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

import static com.szymon.ta.base.WebDriverManager.getDriver;


public final class WaitUtils {

    private WaitUtils() {}

    private static Wait<WebDriver> fluentWait() {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(Config.getInt("wait.timeout")))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public static WebElement getElementIfClickable(By locator) {
        return fluentWait().until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static WebElement getElementIfVisible(By locator) {
        return fluentWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static List<WebElement> getAllVisibleElement(By locator) {
        return fluentWait().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
    }

    public static boolean isInvisible(By locator) {
        return fluentWait().until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }


}
