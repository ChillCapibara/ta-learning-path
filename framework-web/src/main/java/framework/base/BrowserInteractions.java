package framework.base;


import framework.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic browser interactions helpers. Intended to be used by tests or page objects when needed,
 * without exposing BasePage internals.
 */
public final class BrowserInteractions {

    private static final Logger log = LoggerFactory.getLogger(BrowserInteractions.class);

    private BrowserInteractions(){}

    private static WebDriver getDriverOrThrow(){
        WebDriver driver = WebDriverManager.getDriver();
        if (driver == null){
            throw new IllegalStateException(
                    "WebDriver is not initialized. Ensure BaseTest.setup() ran WebDriverManager.setDriver()"
            );
        }
        return driver;
    }

    public static void back(){
        log.debug("BrowserNav: back()");
        getDriverOrThrow().navigate().back();
    }
    public static void forward(){
        log.debug("BrowserNav: forward()");
        getDriverOrThrow().navigate().forward();
    }
    public static void refresh(){
        log.debug("BrowserNav: refresh()");
        getDriverOrThrow().navigate().refresh();
    }

}
