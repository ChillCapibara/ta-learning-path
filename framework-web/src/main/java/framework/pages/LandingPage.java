package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver){
        super(driver);
    }

    protected static final By WELCOME_TEXT = By.xpath("//*[starts-with(normalize-space(), 'Hello')]/strong");
    protected static final By SIGN_OUT = By.cssSelector("a[href='https://practice.automationtesting.in/my-account/customer-logout/']");

    public String getWelcomeText(){
        return getText(WELCOME_TEXT);
    }

    public LoginPage signOut(){
        click(SIGN_OUT);
        return new LoginPage(driver);
    }

}
