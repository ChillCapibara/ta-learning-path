package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.base.BasePage;
import utils.Config;
import utils.Endpoint;

public class LoginPage extends BasePage {
    //potentially sensitive data
    private final String userLogin = Config.get("login.username");
    private final String userPassword = Config.get("login.password");

    private final String loginUrl = Endpoint.MY_ACCOUNT.url();


    public LoginPage(WebDriver driver){
        super(driver);
    }

    protected static final By COOKIES_ACCEPT_BUTTON = By.cssSelector("[aria-label='Consent']");
    protected static final By LOGIN_FIELD = By.id("username");
    protected static final By PASSWORD_FIELD = By.id("password");
    protected static final By LOGIN_BUTTON = By.name("login");


    public LandingPage signIn(){
        enterValue(LOGIN_FIELD, userLogin);
        enterValue(PASSWORD_FIELD, userPassword);
        click(LOGIN_BUTTON);
        return new LandingPage(driver);
    }

    public LoginPage open(){
        navigateTo(loginUrl);
        click(COOKIES_ACCEPT_BUTTON);
        return this;
    }

}
