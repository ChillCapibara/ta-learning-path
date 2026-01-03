package com.szymon.ta.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import com.szymon.ta.enums.BrowserType;

public class WebDriverFactory {

    // create() only: prepare driver binary & return instance
    public static WebDriver create(BrowserType browserType, DriverOptions driverOptions) {
        setupDriverBinary(browserType);

        //creation delegated to enum
        return browserType.create(driverOptions);
    }


    private static void setupDriverBinary(BrowserType type) {
        switch (type) {
            case CHROME -> WebDriverManager.chromedriver().setup();
            case FIREFOX -> WebDriverManager.firefoxdriver().setup();
            case EDGE -> WebDriverManager.edgedriver().setup();
        }
    }
}
