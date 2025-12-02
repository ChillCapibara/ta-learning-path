package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.base.BasePage;

public class LoginPage extends BasePage {
    private final String loginPageUrl = "https://practice.automationtesting.in/my-account/";
    private final String userLogin = "loremSzymon@ipsum.com";
    private final String userPassword = "Test_1234!";


    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = ".fc-cta-consent.fc-primary-button")
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

    public LoginPage navigateTo(){
        navigateTo(loginPageUrl);
        try{
            waitUntilElementIsLoaded(cookiesAcceptButton);
            cookiesAcceptButton.click();
        } catch (Exception ignore){}
        return new LoginPage(driver);
    }

}
