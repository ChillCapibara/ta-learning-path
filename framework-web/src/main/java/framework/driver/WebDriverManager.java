package framework.driver;

import framework.config.Config;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(WebDriverManager.class);


    public static WebDriver setDriver(){
        if (driverThread.get() == null){
            BrowserType browserType = getBrowserType();
            DriverOptions options = buildOptionsFromConfig();

            //creation delegated to factory
            WebDriver driver = WebDriverFactory.create(browserType, options);

            // lifecycle/configuration
            configureDriver(driver);

            applyWindowMode(driver, options);
            log.info("Creating WebDriver: browser={}, headless={}, windowMode={}",
                    browserType,
                    options.isHeadless(),
                    options.getWindowMode());
            driverThread.set(driver);
        }
        return driverThread.get();
    }

    private static DriverOptions buildOptionsFromConfig() {
        DriverOptions.Builder builder = DriverOptions.builder()
                .headless(Config.getBoolean("headless"))
                .disableNotifications(true);

        switch (getWindowMode()) {
            case MAXIMIZED, DEFAULT -> builder.maximize();
            case CUSTOM -> builder.windowSize(
                    Config.getInt("window.width"),
                    Config.getInt("window.height")
            );
        }

        return builder.build();
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void quitDriver() {
        WebDriver driver = getDriver();

        if (driver == null) {
            log.debug("quitDriver called but WebDriver was null (already quit or not initialized");
            return;
        }

        log.info("Quitting WebDriver");
        try {
            driver.quit();
        } catch (Exception e){
            log.warn("Exception during WebDriver .quit()", e);
        } finally {
            // failsafe against ThreadLocal leaks
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
            log.debug("Driver configured: implicit wait={}s", Config.get("wait.timeout"));
    }

    private static WindowMode getWindowMode() {
        String raw = Config.get("window.mode");
        if (raw == null || raw.isBlank()) {
            return WindowMode.DEFAULT;
        }
        try {
            return WindowMode.valueOf(raw.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unsupported window.mode: " + raw + ". Supported: " + Arrays.toString(WindowMode.values()));
        }
    }

    private static void applyWindowMode(WebDriver driver, DriverOptions options) {
        switch (options.getWindowMode()) {
            case MAXIMIZED, DEFAULT -> driver.manage().window().maximize();
            case CUSTOM -> driver.manage().window().setSize(
                    new Dimension(options.getWidth(), options.getHeight())
            );
        }
    }
}
