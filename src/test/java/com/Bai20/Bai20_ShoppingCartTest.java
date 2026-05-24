package com.Bai20;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BasicTest;

public class Bai20_ShoppingCartTest extends BasicTest {

    private By searchFieldLocator = By.cssSelector(".header-main input#s");
    private By searchResultLocator = By.cssSelector(".header-main .fs-sresult a");
    // private By searchResultLocator =
    // By.xpath("//*[contains(@class,'header-main')]//div[contains(@class,'fs-sresult')]//a[contains(text(),'Bơm
    // nước xe')]");

    private By originDropdownLocator = By.cssSelector("#pa_xuat-xu");
    private By originOptionsLocator = By.cssSelector("#pa_xuat-xu>option");
    private By addToCartButtonLocator = By.cssSelector(".single_add_to_cart_button");

    private String keySearchText = "merc";

    @Test()
    public void shoppingCartTest() {
        // Login to https://bantheme.xyz/hathanhauto/tai-khoan/
        driver.get("https://bantheme.xyz/hathanhauto/tai-khoan/");
        login();

        // Search with keyword: "merc"
        setData(searchFieldLocator, keySearchText);
        // Navigate to the result: "Bơm nước xe"
        List<WebElement> resultOptions = getElementsList(searchResultLocator);
        if (resultOptions.isEmpty()) {
            Assert.fail("No search results found.");
        }

        Assert.assertTrue(resultOptions.get(0).getText().toLowerCase().contains("bơm nước xe"));
        resultOptions.get(0).click();
        // get the product name
        String productName = getTextByElement(By.cssSelector(".product_title"));

        // Add to cart with option: "England"
        clickElement(originDropdownLocator);
        final List<WebElement> originOptions = getElementsList(originOptionsLocator);
        for (WebElement option : originOptions) {
            String value = option.getAttribute("value");
            System.out.println(value);
            if (value.equals("england")) {
                option.click();
                break;
            }
        }
        // .findElement(By.cssSelector("option[value='england']")).click();
        clickElement(addToCartButtonLocator);

        // On the Cart page
        final List<WebElement> cartList = driver.findElements(By.cssSelector(".cart_item"));

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
            By priceLocator = By.cssSelector(".product-price bdi");
            By quantityLocator = By.cssSelector(".product-quantity input");

            int price = parseNumberToInt(item.findElement(priceLocator).getText());
            int quantity = parseNumberToInt(item.findElement(quantityLocator).getAttribute("value"));
            int totalPrice = price * quantity;

            sumOfTotalPrice += totalPrice;
        }

        By cartAmountTotalLocator = By.cssSelector(".cart_totals .order-total .amount bdi");
        int cartTotalAmount = parseNumberToInt(getTextByElement(cartAmountTotalLocator));
        System.out.println(cartTotalAmount);
        Assert.assertEquals(sumOfTotalPrice, cartTotalAmount);

    }

}
