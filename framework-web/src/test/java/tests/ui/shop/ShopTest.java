package tests.ui.shop;

import framework.base.BaseTest;
import framework.testdata.UserFactory;
import framework.pages.CartPage;
import framework.pages.LoginPage;
import framework.pages.ShopPage;
import framework.utils.TextParsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ShopTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(ShopTest.class);
    private ShopPage shopPage;

    @Override
    public void beforeEachTest() {
        LoginPage loginPage = new LoginPage(driver());
        shopPage = loginPage
                .open()
                .acceptCookies()
                .signInAs(UserFactory.valid())
                .clickShopLink();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanupCart() {
        System.out.println("Entering cleanup");
        try {
            CartPage cartPage = new CartPage(driver())
                    .open();
            cartPage.removeAllProductsIfPresent();
        } catch (Exception e) {
            log.debug("Cleanup failed: {}", e.getMessage());
        }
    }

    @Test
    public void testAddingToCart() {
        String initialShopPageCartContent = shopPage.getCartContent();
        shopPage.addToBasketAndroidQuickStart();
        String updatedShopPageCartContent = shopPage.getCartContent();
        int newCartCount = TextParsers.firstInt(updatedShopPageCartContent);

        Assert.assertTrue(newCartCount >= 1,
                "Expected cart count >= 1 but was: " + updatedShopPageCartContent);
        Assert.assertNotEquals(initialShopPageCartContent, updatedShopPageCartContent, "Carts content didn't change");

        CartPage cartPage = shopPage.clickCartIcon();
        String cartProductTitle = cartPage.getProductTitle();
        String expectedTitle = "Android Quick Start Guide";

        Assert.assertTrue(cartProductTitle.contains(expectedTitle),
                String.format("\nUnexpected product title: %s \nExpected: %s", cartProductTitle, expectedTitle));
    }
}
