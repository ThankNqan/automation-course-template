package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IvivuAllProductsPage extends BasePage {

    IvivuProductPage productPage;

    public IvivuAllProductsPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    By productListLocator = By.xpath("//iv-product-view");

    public List<WebElement> getProductList() {
        return getElements(productListLocator);
    }

    public IvivuProductPage selectProduct(WebDriver driver) {

        List<WebElement> productList = getProductList();
        // get the first choice
        productList.get(0).findElement(By.xpath("//span[@class='pdv__hotel--name']")).click();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // Switch to the new tab
        productPage = new IvivuProductPage(driver);
        return productPage;
    }

    public String getSelectedProductName(List<WebElement> productList) {
        String productName = productList.get(0)
                .findElement(By.xpath("//iv-product-view//span[@class='pdv__hotel--name']")).getText();
        return productName;
    }
}
