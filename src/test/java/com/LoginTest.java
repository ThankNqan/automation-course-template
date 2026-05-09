package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class LoginTest extends BasicTest {

    @Test()
    public void loginTest() throws Exception {
        // Launch website
        String url = "https://google.com/";
        driver.get(url);
        String webUrl = driver.getCurrentUrl();
        Assert.assertEquals(webUrl, url);

        // Declare locator
        WebElement emailFieldLocator = driver.findElement(By.id("username"));
        WebElement passwordFieldLocator = driver.findElement(By.id("password"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        // Input username & password
        emailFieldLocator.sendKeys("ntthanhngan.2001@gmail.com");
        passwordFieldLocator.sendKeys("Thanhngan@123456");
        loginButtonLocator.click();

        // Verify login button not displayed => login successfully
        Assert.assertFalse(loginButtonLocator.isDisplayed(), "Login Failed");
        Utils.hardWait(5000);

    }

}
