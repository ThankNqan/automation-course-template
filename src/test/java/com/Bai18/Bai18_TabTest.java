package com.Bai18;

import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class Bai18_TabTest extends BasicTest {

    @Test
    public void testTab() {

        // login to the https://bantheme.xyz/hathanhauto/tai-khoan/
        driver.get("https://bantheme.xyz/hathanhauto/tai-khoan/");

        login();

        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // Switch to the new tab
        driver.get("https://bantheme.xyz/hathanhauto");

        // Close the old tab
        driver.switchTo().window(tabs.get(0)); // Switch to the old tab
        driver.close();

        driver.switchTo().window(tabs.get(1));
        // Click Login button
        driver.findElement(By.cssSelector(".pos-login")).click();
        // Verify user is still logged
        WebElement contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
        Assert.assertTrue(contentTextLocator.getText()
                .contains("Xin chào"));
        Utils.hardWait();

    }
}
