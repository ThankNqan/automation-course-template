package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    By welcomeMessageLocator = By.cssSelector(".woocommerce-MyAccount-content");
    By accountLocator = By.cssSelector("//a[contains(text(),'Trang tài khoản')]");
    By editAccountLocator = By.cssSelector("//a[contains(text(),'Tài khoản')]");

    public String getWelcomeMessage() {
        return getText(welcomeMessageLocator);
    }

}
