package ui.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.enums.BrowserType;
import utils.Config;

import java.time.Duration;
import java.util.Arrays;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver setDriver() {

        if (driverThread.get() == null) {

            BrowserType browserType = getBrowserType();

            //prepare options
            WebDriverManagerSetup(browserType);

            //driver creation inside enum
            WebDriver driver = browserType.create();

            //global config
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();

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

    public static BrowserType getBrowserType() {
        String browserName = Config.get("browser");
        if (browserName == null || browserName.isBlank()) {
            return BrowserType.CHROME;
        }
        try {
            return BrowserType.valueOf(browserName.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Invalid browser: '" + browserName +
                            "'. Supported: " + Arrays.toString(BrowserType.values()));
        }
    }

    private static void WebDriverManagerSetup(BrowserType type) {

        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                break;
        }
    }
}
