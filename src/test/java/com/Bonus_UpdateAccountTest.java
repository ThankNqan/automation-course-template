package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class Bonus_UpdateAccountTest extends BasicTest {

    @Test()
    public void updateAccountSuccessfullyTest() throws Exception {
        // Launch website and navigate to https://bantheme.xyz/hathanhauto/tai-khoan/
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        // Login
        WebElement loginEmailFieldLocator = driver.findElement(By.id("username"));
        WebElement loginPasswordFieldLocator = driver.findElement(By.id("password"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        String emailLogin = "ntthanhngan.2001@gmail.com";
        String passwordLogin = "Thanhngan@123456";
        loginEmailFieldLocator.sendKeys(emailLogin);
        loginPasswordFieldLocator.sendKeys(passwordLogin);
        loginButtonLocator.click();

        WebElement contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
        Assert.assertTrue(contentTextLocator.getText()
                .contains("Xin chào")); // login successfully

        // Navigate to the Update account
        driver.findElement(By.cssSelector("a[href*='edit-account']")).click();
        // Navigate to the DisplayName field
        WebElement displayNameFieldLocator = driver.findElement(By.name("account_display_name"));
        String originalDisplayName = displayNameFieldLocator.getAttribute("value");
        String newDisplayName = originalDisplayName + "_updated";
        displayNameFieldLocator.clear();
        displayNameFieldLocator.sendKeys(newDisplayName);
        // Navigate to the Email field
        WebElement emailAccountFieldLocator = driver.findElement(By.name("account_email"));
        String originalEmail = "ntthanhngan.2001@gmail.com";
        String newEmail = originalEmail.substring(0, originalEmail.indexOf("@")) + "_updated"
                + originalEmail.substring(originalEmail.indexOf("@"));
        emailAccountFieldLocator.clear();
        emailAccountFieldLocator.sendKeys(newEmail);
        // Save the updates
        driver.findElement(By.name("save_account_details")).click();
        Utils.hardWait();
        // Verify the success message
        WebElement alertMessageLocator = driver.findElement(By.className("woocommerce-message"));
        Assert.assertTrue(alertMessageLocator.getText().contains("Thông tin tài khoản đã được cập nhật"));

        revertUpdates(originalDisplayName, originalEmail);

    }

    public void revertUpdates(String originalDisplayName, String originalEmail) {
        // Revert updates
        driver.findElement(By.cssSelector("a[href*='edit-account']")).click();
        WebElement displayNameFieldLocator = driver.findElement(By.name("account_display_name"));
        displayNameFieldLocator.clear();
        displayNameFieldLocator.sendKeys(originalDisplayName);
        WebElement emailAccountFieldLocator = driver.findElement(By.name("account_email"));
        emailAccountFieldLocator.clear();
        emailAccountFieldLocator.sendKeys(originalEmail);
        driver.findElement(By.name("save_account_details")).click();
        Utils.hardWait();
    }

}
