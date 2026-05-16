package com.Bai17;

import java.util.List;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.Utils;

public class Bai17_ShoppingCartTest
        extends BasicTest {

    private By searchField = By.cssSelector(".header-main input#s");
    private By searchResult = By.cssSelector(".header-main .fs-sresult a");
    private By xuatXuOption = By.cssSelector("#pa_xuat-xu");

    @Test()
    public void shoppingCartTest() {
        // Login to https://bantheme.xyz/hathanhauto/tai-khoan/
        login("ntthanhngan.2001@gmail.com", "Thanhngan@123456");

        // Search with keyword: "merc"
        setData(searchField, "merc");
        Utils.hardWait();
        // Navigate to the result: "Bơm nước xe"
        List<WebElement> resultOptions = driver.findElements(searchResult);
        if (resultOptions.isEmpty()) {
            Assert.fail("No search results found.");
        }
        WebElement firstOption = resultOptions.get(0);
        firstOption.click();

        // get the product name
        String productName = driver.findElement(By.cssSelector(".product_title")).getText();
        // Add to cart with option: "England"
        driver.findElement(xuatXuOption).click();
        driver.findElement(xuatXuOption).findElement(By.cssSelector("option[value='england']")).click();
        driver.findElement(By.cssSelector(".single_add_to_cart_button")).click();

        // On the Cart page
        List<WebElement> cartList = driver.findElements(By.cssSelector(".cart_item"));

        boolean found = false;
        for (int i = 0; i < cartList.size(); i++) {
            WebElement item = cartList.get(i);
            String itemName = item.findElement(By.cssSelector("td.product-name a")).getText();
            if (itemName.contains(productName)) {
                found = true;
                break;
            }
        }
        // Verify the product added into cart successfully
        Assert.assertTrue(found, "Product is not added into cart list");

        // Get the totalprice of all products in cart
        int sumOfTotalPrice = 0;
        for (WebElement item : cartList) {
            String price = item.findElement(By.cssSelector(".product-price bdi")).getText();
            String quantity = item.findElement(By.cssSelector(".product-quantity input")).getAttribute("value");
            int totalPrice = parseNumberToInt(price) * parseNumberToInt(quantity);
            sumOfTotalPrice += totalPrice;
        }
        WebElement cartTotal = driver.findElement(By.cssSelector(".cart_totals .order-total .amount bdi"));
        int cartTotalAmount = parseNumberToInt(cartTotal.getText());
        System.out.println(cartTotalAmount);
        Assert.assertEquals(sumOfTotalPrice, cartTotalAmount);

    }

}
