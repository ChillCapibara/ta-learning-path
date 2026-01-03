package com.szymon.ta.pages;

import com.szymon.ta.base.BasePage;
import com.szymon.ta.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.szymon.ta.utils.Endpoint;

public class LoginPage extends BasePage {

    private final String loginUrl = Endpoint.MY_ACCOUNT.url();


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    protected static final By COOKIES_ACCEPT_BUTTON = By.cssSelector("[aria-label='Consent']");
    protected static final By LOGIN_FIELD = By.id("username");
    protected static final By PASSWORD_FIELD = By.id("password");
    protected static final By LOGIN_BUTTON = By.name("login");


    public LandingPage signInAs(User user) {
        enterValue(LOGIN_FIELD, user.getUsername());
        enterValue(PASSWORD_FIELD, user.getPassword());
        click(LOGIN_BUTTON);
        return new LandingPage(driver);
    }

    public LoginPage open() {
        navigateTo(loginUrl);
        return this;
    }

    public LoginPage acceptCookies() {
        click(COOKIES_ACCEPT_BUTTON);
        return this;
    }

}
