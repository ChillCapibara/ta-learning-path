package com.szymon.ta.tests.ui.login;

import com.szymon.ta.base.BaseTest;
import com.szymon.ta.data.Users;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.szymon.ta.pages.LandingPage;
import com.szymon.ta.pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Override
    public void beforeEachTest(){
        loginPage = new LoginPage(driver());
        loginPage
                .open()
                .acceptCookies();
    }

    @Test
    public void testValidLogin(){
        LandingPage landingPage = loginPage.signInAs(Users.valid());
        String welcomeText = landingPage.getWelcomeText();

        Assert.assertTrue(welcomeText.contains("loremSzymon"), "A user is not signed in!");
    }

}
