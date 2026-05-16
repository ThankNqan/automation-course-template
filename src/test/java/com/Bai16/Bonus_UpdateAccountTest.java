package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class Bonus_UpdateAccountTest extends BasicTest {

    private String emailLogin = "ntthanhngan.2001@gmail.com";
    private String passwordLogin = "Thanhngan@123456";
    private String originalEmail = "ntthanhngan.2001@gmail.com";
    private String originalDisplayName;

    private By accountLocator = By.cssSelector("a[href*='edit-account']");
    private By displayNameFieldLocator = By.cssSelector("[name='account_display_name']");
    private By emailAccountFieldLocator = By.cssSelector("[name='account_email']");

    @Test()
    public void updateAccountSuccessfullyTest() throws Exception {

        login(emailLogin, passwordLogin);

        // Navigate to the Update account
        navigateTo(accountLocator);
        // Navigate to the DisplayName field
        originalDisplayName = getElementLocator(displayNameFieldLocator).getAttribute("value");
        String newDisplayName = originalDisplayName + "_updated";
        setData(displayNameFieldLocator, newDisplayName);
        // Navigate to the Email field
        String newEmail = originalEmail.substring(0, originalEmail.indexOf("@")) + "_updated"
                + originalEmail.substring(originalEmail.indexOf("@"));
        setData(emailAccountFieldLocator, newEmail);
        // Save the updates
        driver.findElement(By.name("save_account_details")).click();
        Utils.hardWait();
        // Verify the success message
        WebElement alertMessageLocator = driver.findElement(By.className("woocommerce-message"));
        Assert.assertTrue(alertMessageLocator.getText().contains("Thông tin tài khoản đã được cập nhật"));

    }

    @AfterMethod
    public void revertAccountInfo() {
        // Revert updates
        navigateTo(accountLocator);
        setData(displayNameFieldLocator, originalDisplayName);
        setData(emailAccountFieldLocator, originalEmail);
        driver.findElement(By.name("save_account_details")).click();
        Utils.hardWait();
    }

}
