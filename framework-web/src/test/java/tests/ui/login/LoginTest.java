package tests.ui.login;

import framework.base.BaseTest;
import framework.base.BrowserInteractions;
import framework.data.providers.LoginDataProviders;
import framework.testdata.UserFactory;
import framework.data.model.User;
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

    @Test(groups="smoke")
    public void testValidLogin() {
        LandingPage landingPage = loginPage.signInAs(UserFactory.valid());
        String welcomeText = landingPage.getWelcomeText();

        Assert.assertTrue(welcomeText.contains("loremSzymon"), "A user is not signed in!");
    }

    @Test
    public void testLoginWithNoSuchUser() {
        loginPage.signInAs(UserFactory.invalid("Capybara", "invalidPassword1234!"));
        String errorMsg = loginPage.getErrorMsg();

        Assert.assertEquals(errorMsg, "Error: The username Capybara is not registered on this site. If you are unsure of your username, try your email address instead.");
    }

    @Test
    public void testLoginInvalidPassword() {
        User valid = UserFactory.valid();
        loginPage.signInAs(UserFactory.invalid(valid.getUsername(), "invalidPassword1234!"));

        String errorMsg = loginPage.getErrorMsg();
        Assert.assertEquals(errorMsg, "Error: The password you entered for the username loremSzymon@ipsum.com is incorrect. Lost your password?");
    }

    @Test
    public void testSignOut() {
        LandingPage landingPage = loginPage.signInAs(UserFactory.valid());
        LoginPage loginPage = landingPage.signOut();

        Assert.assertTrue(loginPage.loginPageIsOpened(), "Login field was not found, a different page might be opened!");
    }

    @Test
    public void testNavigateBackAfterSignOut() {
        LoginPage login = loginPage
                .signInAs(UserFactory.valid())
                .signOut();
        BrowserInteractions.back();

        Assert.assertTrue(
                login.loginPageIsOpened(),
                "Login field was not found, a different page might be opened!");
    }

    // Failing on purpose to trigger the retry logic
    @Test
    public void failingTestToConfirmRetryLogic() {
        LandingPage landingPage = loginPage.signInAs(UserFactory.valid());
        String welcomeText = landingPage.getWelcomeText();

        Assert.assertTrue(welcomeText.contains("Grumpy Wombat"), "Wrong user!");
    }

    //=== mixed validations as a POC for data-driven tests ===

    @Test(dataProvider = "usersCsv", dataProviderClass = LoginDataProviders.class)
    public void loginFromCsv(String username, String password, String expected) {
        User user = new User(username, password);
        LandingPage landingPage = loginPage.signInAs(user);

        if (expected.equals("ERROR_INVALID")) {
            String errorMsg = loginPage.getErrorMsg();
            Assert.assertTrue(errorMsg.contains("Error"));
        } else if (expected.equals("SUCCESS")) {
            String welcomeText = landingPage.getWelcomeText();
            Assert.assertTrue(welcomeText.contains("loremSzymon"), "A user is not signed in!");
        }
    }

    @Test(dataProvider = "loginJson", dataProviderClass = LoginDataProviders.class)
    public void login_from_json(String username, String password, String expected) {
        User user = new User(username, password);
        LandingPage landingPage = loginPage.signInAs(user);

        if (expected.equals("ERROR_INVALID")) {
            String errorMsg = loginPage.getErrorMsg();
            Assert.assertTrue(errorMsg.contains("Error"));
        } else if (expected.equals("SUCCESS")) {
            String welcomeText = landingPage.getWelcomeText();
            Assert.assertTrue(welcomeText.contains("loremSzymon"), "A user is not signed in!");
        }
    }
}
