package ui.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum BrowserType {
    CHROME {
        public WebDriver create() {
            return new ChromeDriver();
        }
    },
    FIREFOX {
        public WebDriver create() {
            return new FirefoxDriver();
        }
    },
    EDGE {
        public WebDriver create() {
            return new EdgeDriver();
        }
    };

    public abstract WebDriver create();
}
