package com.szymon.ta.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

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
