package com.Bai19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.BasicTest;

public class Bai19_ICEHRM_LoginTest extends BasicTest {

    @Test(dataProvider = "testLoginData")
    public void testDataFeed(String user, String pass, String expected) {
        // Navigate to https://icehrm-open.gamonoid.com/login.php
        String url = "https://icehrm-open.gamonoid.com/login.php";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        WebElement usernameFieldElement = driver.findElement(By.id("username"));
        WebElement passwordFieldElement = driver.findElement(By.id("password"));
        WebElement loginButtonElement = driver
                .findElement(By.xpath("//*[@id='loginForm']//button[contains(text(),'Log in')]"));
        usernameFieldElement.sendKeys(user);
        passwordFieldElement.sendKeys(pass);
        loginButtonElement.click();

        String errorMessage = errorMessage();
        if (errorMessage != "") {
            Assert.assertEquals(errorMessage, expected);
        } else {
            // Verify display home page
            Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Home')]")).isDisplayed());
        }

    }

    @DataProvider(name = "testLoginData")
    public Object[][] testData() {
        Object[][] data = {
                { "admin", "admin", "" },
                { "admin", "admin123", "Login failed" }
        };
        return data;
    }

    public String errorMessage() {
        try {
            WebElement errorMessageElement = driver.findElement(By.cssSelector(".alert-danger"));
            String errorMessage = errorMessageElement.getText().trim();
            return errorMessage;
        } catch (Exception e) {
            return "";
        }

    }
}
