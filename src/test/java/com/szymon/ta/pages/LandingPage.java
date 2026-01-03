package com.szymon.ta.pages;

import com.szymon.ta.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver){
        super(driver);
    }

    protected static final By WELCOME_TEXT = By.xpath("//*[starts-with(normalize-space(), 'Hello')]/strong");


    public String getWelcomeText(){
        return getText(WELCOME_TEXT);
    }
}
