package com.szymon.ta.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup(){
        this.driver = WebDriverManager.setDriver();
        beforeEachTest();
    }

    @AfterTest
    public void tearDown(){
        WebDriverManager.quitDriver();
    }

    protected void beforeEachTest(){
        //common setup for different test suites. Override with specific implementation inside the specific suites
    }

    protected WebDriver driver(){
        return WebDriverManager.getDriver();
    }

}
