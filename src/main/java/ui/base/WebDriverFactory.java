package ui.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.enums.BrowserType;

import java.time.Duration;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver setDriver(BrowserType browserType) {
        if (driverThread.get() == null) {
            WebDriver driver = createDriver(browserType);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            driverThread.set(driver);
        }
        return driverThread.get();
    }

    private static WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("Browser type " + browserType + "not supported");
        }
    }

    public static WebDriver getDriver(){
        return driverThread.get();
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}