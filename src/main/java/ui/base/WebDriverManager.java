package ui.base;

import org.openqa.selenium.WebDriver;
import ui.enums.BrowserType;
import utils.Config;

import java.time.Duration;
import java.util.Arrays;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();


    public static WebDriver setDriver(){
        if (driverThread.get() == null){
            BrowserType browserType = getBrowserType();

            //creation delegated to factory
            WebDriver driver = WebDriverFactory.create(browserType);

            // lifecycle/configuration
            configureDriver(driver);

            driverThread.set(driver);
        }
        return driverThread.get();
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }

    private static BrowserType getBrowserType() {
        String browserName = Config.get("browser");
        if (browserName == null || browserName.isBlank()) {
            return BrowserType.CHROME;
        }
        try {
            return BrowserType.valueOf(browserName.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Invalid browser: '" + browserName + "'. Supported: " + Arrays.toString(BrowserType.values()));
        }
    }


    private static void configureDriver(WebDriver driver){
            //Global config values read centrally
            int waitTimeout = Config.getInt("wait.timeout");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimeout));
            driver.manage().window().maximize();

            driverThread.set(driver);
    }


}
