package com.Bai16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class Bai16_LoginTest extends BasicTest {

    @Test(priority = 1)
    public void loginTestSuccess() throws Exception {
        // Launch website and navigate to https://bantheme.xyz/hathanhauto/tai-khoan/
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        // Declare locator
        WebElement loginEmailFieldLocator = driver.findElement(By.id("username"));
        WebElement loginPasswordFieldLocator = driver.findElement(By.id("password"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        String emailLogin = "ntthanhngan.2001@gmail.com";
        String passwordLogin = "Thanhngan@123456";
        // Enter email address into email field
        loginEmailFieldLocator.sendKeys(emailLogin);
        // Enter password into password field
        loginPasswordFieldLocator.sendKeys(passwordLogin);
        // Click login button
        loginButtonLocator.click();
        // Verify displaying Xin Chào after login successfully
        WebElement contentTextLocator = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-MyAccount-content")));
        Assert.assertTrue(contentTextLocator.getText()
                .contains("Xin chào"));

    }

    @Test()
    public void loginTestFailed() throws Exception {
        // Launch website and navigate to https://bantheme.xyz/hathanhauto/tai-khoan/
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        // Declare locator
        WebElement loginEmailFieldLocator = driver.findElement(By.id("username"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        String emailLogin = "ntthanhngan.2001@gmail.com";
        // Enter email address into email field
        loginEmailFieldLocator.sendKeys(emailLogin);
        // Do not enter password into password field
        // Click login button
        loginButtonLocator.click();
        // Utils.hardWait(3000);

        // Get the error message
        WebElement errorMessageLocator = wait
                .until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("ul[role=\"alert\"] li"))));
        String errorMessage = errorMessageLocator.getText();
        // Verify the error mesage display Mục nhập mật khẩu trống
        Assert.assertTrue(errorMessage
                .contains("Mục nhập mật khẩu trống"));
    }

}
