package framework.base;

import framework.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setMdc(ITestResult result){
        String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        MDC.put("test", testName);
    }

    @AfterMethod(alwaysRun = true)
    public void clearMdc(){
        MDC.clear();
    }

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
