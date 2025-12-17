package ui.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.base.DriverOptions;

public enum BrowserType {
    CHROME {
        public WebDriver create(DriverOptions options) {
            ChromeOptions chrome = new ChromeOptions();

            if (options.isDisableNotifications()) {
                chrome.addArguments("--disable-notifications");
            }
            if (options.isHeadless()) {
                chrome.addArguments("--headless=new");
            }
            if (options.hasCustomSize()) {
                chrome.addArguments(
                        "--window-size=" +
                                options.getWidth() + "," + options.getHeight()
                );
            }

            return new ChromeDriver(chrome);
        }
    },
    FIREFOX {
        public WebDriver create(DriverOptions options) {
            FirefoxOptions firefox = new FirefoxOptions();

            if (options.isDisableNotifications()) {
                firefox.addArguments("--disable-notifications");
            }
            if (options.isHeadless()) {
                firefox.addArguments("--headless");
            }

            return new FirefoxDriver(firefox);
        }
    },

    EDGE {
        public WebDriver create(DriverOptions options) {
            return new EdgeDriver();
        }
    };

    public abstract WebDriver create(DriverOptions options);
}
