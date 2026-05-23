package com.Bai16;

import java.lang.annotation.ElementType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class Bai16_RegisterTest extends BasicTest {

    @Test()
    public void registerTest() throws Exception {
        // Launch website and navigate to https://bantheme.xyz/hathanhauto/tai-khoan/
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        // Declare locator
        WebElement registerEmailFieldLocator = driver.findElement(By.id("reg_email"));
        WebElement passwordFieldLocator = driver.findElement(By.id("reg_password"));
        WebElement registerButtonLocator = driver.findElement(By.cssSelector("button[name='register']"));

        // Enter testtest@gmail.com into email field
        registerEmailFieldLocator.sendKeys("testtest@gmail.com");
        // Click submit button
        registerButtonLocator.click();

        // Get the error message
        WebElement errorMessageLocator = driver.findElement(By.cssSelector("ul[role=\"alert\"] li"));
        String errorMessage = errorMessageLocator.getText();
        // Verify error message display An account is already registered with your email
        // address
        Assert.assertTrue(errorMessage
                .contains("An account is already registered with your email address"));

    }

}
