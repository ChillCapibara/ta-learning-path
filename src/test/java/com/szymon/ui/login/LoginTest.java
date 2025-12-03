package com.szymon.ui.login;

import com.szymon.ui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.pages.LandingPage;
import ui.pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeTest
    public void setUp(){
        loginPage = new LoginPage(driver());
        loginPage.open();
    }

    @Test
    public void testValidLogin(){
        LandingPage landingPage = loginPage.signIn();

        Assert.assertTrue(landingPage.getWelcomeText().contains("loremSzymon"), "A user is not signed in!");
    }

}
