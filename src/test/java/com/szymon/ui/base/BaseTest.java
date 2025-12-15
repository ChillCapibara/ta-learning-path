package com.szymon.ui.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.base.WebDriverFactory;
import ui.base.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup(){
        this.driver = WebDriverManager.setDriver();
    }

    @AfterTest
    public void tearDown(){
        WebDriverManager.quitDriver();
    }

    protected WebDriver driver(){
        return WebDriverManager.getDriver();
    }

}
