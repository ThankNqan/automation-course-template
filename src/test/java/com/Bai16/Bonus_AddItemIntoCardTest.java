package com.Bai16;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class Bonus_AddItemIntoCardTest
                extends BasicTest {

        @Test()
        public void addItemIntoCartSuccess() throws Exception {
                // Launch website and navigate to https://bantheme.xyz/hathanhauto/
                String url = "https://bantheme.xyz/hathanhauto/";
                driver.get(url);
                Assert.assertEquals(driver.getCurrentUrl(), url);

                // Find the product item
                WebElement productItemLocator = driver
                                .findElement(By.xpath(
                                                "(//section[@id='eweb_new_product-2']//div[@class='item-product'])[2]"));
                ;
                productItemLocator.click();

                // Open the product details
                // Add product to cart
                String productName = driver.findElement(By.className("product_title")).getText();
                driver.findElement(By.name("add-to-cart")).click();

                // Verify the product added to cart correctly
                WebElement firtItemCartLocator = driver.findElement(By.cssSelector(".cart_item:nth-child(1)"));
                String firstItemProductName = firtItemCartLocator.findElement(By.cssSelector(".product-name a"))
                                .getText();
                Assert.assertTrue(firstItemProductName.contains(productName));

                // Login app
                // Navigate to Tai Khoan
                driver.findElement(By.className("pos-login")).click();
                WebElement loginEmailFieldLocator = driver.findElement(By.id("username"));
                WebElement loginPasswordFieldLocator = driver.findElement(By.id("password"));
                WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

                // Enter email address into email field
                loginEmailFieldLocator.sendKeys("ntthanhngan.2001@gmail.com");
                // Enter password into password field
                loginPasswordFieldLocator.sendKeys("Thanhngan@123456");
                // Click login button
                loginButtonLocator.click();

                // Utils.hardWait(3000);

                // // Verify displaying Xin Chào after login successfully
                WebElement contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
                Assert.assertTrue(contentTextLocator.getText()
                                .contains("Xin chào"));

                // Navigate to Cart
                driver.findElement(By.cssSelector(".header-main a[title*='Giỏ hàng']")).click();
                // Verify the new item added after login
                boolean isExist = false;
                List<WebElement> cartList = driver.findElements(By.cssSelector(".cart_item"));
                for (WebElement item : cartList) {
                        String itemProductName = item.findElement(By.cssSelector(".product-name a")).getText();
                        if (itemProductName.contains(productName)) {
                                isExist = true;
                                break;
                        }
                }
                Assert.assertTrue(isExist, "Failed to add product to cart");
                Utils.hardWait();

        }

}
