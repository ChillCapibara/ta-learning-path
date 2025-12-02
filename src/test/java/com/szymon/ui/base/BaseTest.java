package com.szymon.ui.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.base.WebDriverFactory;
import ui.enums.BrowserType;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup(){
        this.driver = WebDriverFactory.setDriver(BrowserType.CHROME);
    }

    @AfterTest
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }

    protected WebDriver driver(){
        return WebDriverFactory.getDriver();
    }

}
