package framework.pages;

import framework.base.BasePage;
import framework.data.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.navigation.Endpoint;

public class LoginPage extends BasePage {

    private final String loginUrl = Endpoint.MY_ACCOUNT.url();


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    protected static final By COOKIES_ACCEPT_BUTTON = By.cssSelector("[aria-label='Consent']");
    protected static final By LOGIN_FIELD = By.id("username");
    protected static final By PASSWORD_FIELD = By.id("password");
    protected static final By LOGIN_BUTTON = By.name("login");
    protected static final By ERROR_MSG_NO_SUCH_USER = By.cssSelector(".woocommerce-error li");


    public LandingPage signInAs(User user) {
        enterValue(LOGIN_FIELD, user.getUsername());
        enterValue(PASSWORD_FIELD, user.getPassword());
        click(LOGIN_BUTTON);
        return new LandingPage(driver);
    }

    public LoginPage open() {
        open(loginUrl);
        return this;
    }

    public LoginPage acceptCookies() {
        click(COOKIES_ACCEPT_BUTTON);
        return this;
    }

    public String getErrorMsg() {
        return getText(ERROR_MSG_NO_SUCH_USER);
    }

    public boolean loginPageIsOpened(){
        return !visibleElements(LOGIN_FIELD).isEmpty();
    }

}
