package com.szymon.ui.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.base.WebDriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup(){
        this.driver = WebDriverFactory.setDriver();
    }

    @AfterTest
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }

    protected WebDriver driver(){
        return WebDriverFactory.getDriver();
    }

}
