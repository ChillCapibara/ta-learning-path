package tests.ui.login;

import framework.base.BaseTest;
import framework.base.BrowserInteractions;
import framework.data.Users;
import framework.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.LandingPage;
import framework.pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Override
    public void beforeEachTest() {
        loginPage = new LoginPage(driver());
        loginPage
                .open()
                .acceptCookies();
    }

    @Test
    public void testValidLogin() {
        LandingPage landingPage = loginPage.signInAs(Users.valid());
        String welcomeText = landingPage.getWelcomeText();

        Assert.assertTrue(welcomeText.contains("loremSzymon"), "A user is not signed in!");
    }

    @Test
    public void testLoginWithNoSuchUser() {
        loginPage.signInAs(Users.invalid("Capybara", "invalidPassword1234!"));
        String errorMsg = loginPage.getErrorMsg();

        Assert.assertEquals(errorMsg, "Error: The username Capybara is not registered on this site. If you are unsure of your username, try your email address instead.");
    }

    @Test
    public void testLoginInvalidPassword() {
        User valid = Users.valid();
        loginPage.signInAs(Users.invalid(valid.getUsername(), "invalidPassword1234!"));

        String errorMsg = loginPage.getErrorMsg();
        Assert.assertEquals(errorMsg, "Error: The password you entered for the username loremSzymon@ipsum.com is incorrect. Lost your password?");
    }

    @Test
    public void testSignOut() {
        LandingPage landingPage = loginPage.signInAs(Users.valid());
        LoginPage loginPage = landingPage.signOut();

        Assert.assertTrue(loginPage.loginPageIsOpened(), "Login field was not found, a different page might be opened!");
    }

    @Test
    public void testNavigateBackAfterSignOut() {
        LoginPage login = loginPage
                .signInAs(Users.valid())
                .signOut();
        BrowserInteractions.back();

        Assert.assertTrue(
                login.loginPageIsOpened(),
                "Login field was not found, a different page might be opened!");
    }

}
