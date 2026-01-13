package framework.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

public class MdcTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // method name is usually enough; you can also include class
        String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        MDC.put("test", testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        MDC.remove("test");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = framework.driver.WebDriverManager.getDriver();
        if (driver != null) {
            try {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.attachment("Screenshot - " + result.getName(),
                        new ByteArrayInputStream(png));
            } catch (Exception e) {
                Logger log = LoggerFactory.getLogger(MdcTestListener.class);
                log.warn("Failed to capture screenshot for {}: {}", result.getName(), e.toString());
            }
        }
        MDC.remove("test");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        MDC.remove("test");
    }

    @Override
    public void onFinish(ITestContext context) {
        MDC.remove("test");
    }
}
