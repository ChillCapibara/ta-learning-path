package tests.ui.login;

import framework.base.BaseTest;
import framework.data.Users;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.LandingPage;
import framework.pages.LoginPage;

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
