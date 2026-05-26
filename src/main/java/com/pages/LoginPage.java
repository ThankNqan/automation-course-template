package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    By usernameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By buttonLocator = By.cssSelector("button[name='login']");
    By errorMessageLocator = By.cssSelector("ul[role=\"alert\"] li");

    public void enterUsername(String username) {
        sendKeys(usernameLocator, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordLocator, password);
    }

    public void clickLoginButton() {
        clickTo(buttonLocator);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        return getText(errorMessageLocator);
    }
}
