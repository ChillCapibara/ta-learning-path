package framework.listeners;

import framework.driver.WebDriverManager;
import framework.stability.FailureReason;
import framework.stability.WebFailureClassifier;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class FailureArtifactsListener implements ITestListener {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onTestFailure(ITestResult result) {
        FailureReason reason = WebFailureClassifier.classify(result.getThrowable());
        Allure.addAttachment("Failure reason", reason.name());

        WebDriver driver = WebDriverManager.getDriver();
        if (driver == null) return;

        String testName = result.getTestClass().getRealClass().getSimpleName()
                + "." + result.getName();

        //URL
        safeAttachText("URL - " + testName, safeGetCurrentUrl(driver));

        //Screenshot
        safeAttachScreenshot(driver, "Screenshot - " + testName);

        //Page source
        safeAttachText("Page Source - " + testName, safeGetPageSource(driver));
    }

    private void safeAttachScreenshot(WebDriver driver, String name) {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name, new ByteArrayInputStream(png));
            }
        } catch (Exception ignored) {
            // never fail the test because of evidence collection
            log.warn("Screenshot attachment failed!");
        }
    }

    private void safeAttachText(String name, String content) {
        try {
            if (content == null) content = "";
            Allure.addAttachment(name, "text/plain",
                    new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)),
                    ".txt");
        } catch (Exception ignored) {
            log.warn("Text attachment failed!");
        }
    }

    private String safeGetCurrentUrl(WebDriver driver) {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            return "[unavailable] " + e.getClass().getSimpleName() + " @ " + Instant.now();
        }
    }

    private String safeGetPageSource(WebDriver driver) {
        try {
            return driver.getPageSource();
        } catch (Exception e) {
            return "[unavailable] " + e.getClass().getSimpleName() + " @ " + Instant.now();
        }
    }
}
