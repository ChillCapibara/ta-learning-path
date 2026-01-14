package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends BasePage {

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    protected static final By BUTTON_ADD_TO_BASKET_ANDROID_QUICK_START = By.cssSelector("[data-product_id='169']");
    protected static final By CART_CONTENT = By.id("wpmenucartli");
    protected static final By CART_FIELD = By.id("wpmenucartli");

    public void addToBasketAndroidQuickStart(){
        click(BUTTON_ADD_TO_BASKET_ANDROID_QUICK_START);
        waitForTextToChange(CART_CONTENT);
    }

    public String getCartContent(){
        return getText(CART_CONTENT);
    }

    public CartPage clickCartIcon(){
        click(CART_FIELD);
        return new CartPage(driver);
    }

}
