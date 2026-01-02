package ui.pages;

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

    @FindBy(css = "[aria-label]='Consent'")
    private WebElement cookiesAcceptButton;

    @FindBy(id = "username")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(name = "login")
    private WebElement loginBtn;

    public LandingPage signIn(){
        enterValue(loginField, userLogin);
        enterValue(passwordField, userPassword);
        click(loginBtn);
        return new LandingPage(driver);
    }

    public LoginPage open(){
        navigateTo(loginUrl);
        click(cookiesAcceptButton);
        return this;
    }

}
