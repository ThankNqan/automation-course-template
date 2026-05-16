package com;

import org.testng.annotations.Test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.utils.Utils;
import com.utils.BasicTest;

public class Bonus_RememberMeTest extends BasicTest {

    @Test
    public void rememberMeEnableTest() {
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        // Input username & password
        WebElement emailFieldLocator = driver.findElement(By.id("username"));
        WebElement passwordFieldLocator = driver.findElement(By.id("password"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        emailFieldLocator.sendKeys("ntthanhngan.2001@gmail.com");
        passwordFieldLocator.sendKeys("Thanhngan@123456");

        // Check the rememberMe Checkbox
        WebElement rememberMeCheckboxLocator = driver.findElement(By.id("rememberme"));
        rememberMeCheckboxLocator.click();

        // Click loginButtonLocator
        loginButtonLocator.click();
        Utils.hardWait();

        // Verify login successfully
        WebElement contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
        Assert.assertTrue(contentTextLocator.getText().contains("Xin chào"));

        Set<Cookie> cookies = driver.manage().getCookies();

        // Close the browser
        driver.quit();

        // Open again
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();

        // Verify the broswer save the login session
        contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
        Assert.assertTrue(contentTextLocator.getText().contains("Xin chào"));
        Utils.hardWait();

    }

    @Test
    public void rememberMeDisableTest() {
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        // Input username & password
        WebElement emailFieldLocator = driver.findElement(By.id("username"));
        WebElement passwordFieldLocator = driver.findElement(By.id("password"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        emailFieldLocator.sendKeys("ntthanhngan.2001@gmail.com");
        passwordFieldLocator.sendKeys("Thanhngan@123456");

        // No no check the rememberMe Checkbox
        // WebElement rememberMeCheckboxLocator =
        // driver.findElement(By.id("rememberme"));
        // rememberMeCheckboxLocator.click();

        // Click loginButtonLocator
        loginButtonLocator.click();
        Utils.hardWait();

        // Verify login successfully
        WebElement contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
        Assert.assertTrue(contentTextLocator.getText().contains("Xin chào"));

        Set<Cookie> cookies = driver.manage().getCookies();

        // Close the browser
        driver.quit();

        // Open again
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();

        // Verify the broswer save the login session
        contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
        Assert.assertFalse(contentTextLocator.getText().contains("Xin chào"));
        Utils.hardWait();

    }
}
