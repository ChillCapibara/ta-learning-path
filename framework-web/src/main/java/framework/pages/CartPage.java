package framework.pages;

import framework.base.BasePage;
import framework.navigation.Endpoint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    protected static final By PRODUCT_TITLE = By.cssSelector("[data-title='Product']");
    protected static final By PRODUCT_REMOVE = By.cssSelector(".product-remove a");
    protected static final By PRODUCT_REMOVE_UNDO = By.cssSelector(".woocommerce-message a");

    public CartPage open() {
        open(Endpoint.CART.url());
        return this;
    }

    public String getProductTitle() {
        return getText(PRODUCT_TITLE);
    }

    public void removeAllProductsIfPresent() {
        int maxIterations = 20;

        while (!visibleElements(PRODUCT_REMOVE).isEmpty() && maxIterations-- > 0) {
            click(PRODUCT_REMOVE);
            getText(PRODUCT_REMOVE_UNDO); // confirmation that product was removed
        }
    }
}
