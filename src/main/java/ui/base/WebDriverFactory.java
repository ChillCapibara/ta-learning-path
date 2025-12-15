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

    // create() only: prepare driver binary & return instance
    public static WebDriver create(BrowserType browserType) {
        setupDriverBinary(browserType);

        //creation delegated to enum
        return browserType.create();
    }


    private static void setupDriverBinary(BrowserType type) {
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
