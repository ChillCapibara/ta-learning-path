package framework.listeners;

import framework.stability.FailureReason;
import framework.stability.WebFailureClassifier;
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

    protected final Logger log = LoggerFactory.getLogger(MdcTestListener.class);

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
        FailureReason reason = WebFailureClassifier.classify(result.getThrowable());
        log.error("FAILURE_REASON={} test={} error={}",
                reason,
                result.getTestClass().getRealClass().getSimpleName() + "." + result.getName(),
                result.getThrowable() == null ? "null" : result.getThrowable().toString());

        WebDriver driver = framework.driver.WebDriverManager.getDriver();
        if (driver != null) {
            try {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.attachment("Screenshot - " + result.getName(),
                        new ByteArrayInputStream(png));
            } catch (Exception e) {
                log.warn("Failed to capture screenshot for {}: {}", result.getName(), e.toString());
            }
            try {
                Allure.attachment("URL - " + result.getName(), driver.getCurrentUrl());
            } catch (Exception ignored) {}
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
