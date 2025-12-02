package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.base.BasePage;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = ".woocommerce-MyAccount-content > p")
    private WebElement welcomeText;


    public String getWelcomeText(){
        return welcomeText.getText();
    }
}
