package framework.base;

import framework.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup(){
        this.driver = WebDriverManager.setDriver();
        beforeEachTest();
    }

    @AfterMethod(alwaysRun = true)
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
