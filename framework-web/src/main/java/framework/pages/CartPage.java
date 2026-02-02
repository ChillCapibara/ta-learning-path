package framework.pages;

import framework.base.BasePage;
import framework.base.StabilityLocators;
import framework.navigation.Endpoint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    protected static final By PRODUCT_TITLE = By.cssSelector("[data-title='Product']");
    protected static final By PRODUCT_REMOVE = By.cssSelector(".product-remove a");
//    protected static final By PRODUCT_REMOVE_UNDO = By.cssSelector(".woocommerce-message a");

    public CartPage open() {
        open(Endpoint.CART.url());
        return this;
    }

    public String getProductTitle() {
        return getText(PRODUCT_TITLE);
    }

    public void removeAllProductsIfPresent() {
        while (!visibleElements(PRODUCT_REMOVE).isEmpty()) {
            //find all remove buttons
            int before = visibleElements(PRODUCT_REMOVE).size();

            click(PRODUCT_REMOVE);
            waitForSpinnerToDisappearIfPresent(StabilityLocators.SPINNER);

            waitUntilItemIsRemoved(before);
        }
    }

    private void waitUntilItemIsRemoved(int countBefore) {
        waitUntil("Cart item count should decrease after clicking remove",
                () -> visibleElements(PRODUCT_REMOVE).size() < countBefore);
    }

}
