package com.pages;

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

    public boolean hasResults() {
        return getProductList().size() > 0;
    }

    public IvivuProductPage selectProduct() {

        List<WebElement> productList = getProductList();
        // get the first choice
        productList.get(0).findElement(By.xpath(".//span[@class='pdv__hotel--name']")).click();

        // wait for new window/tab and switch to it
        String originalHandle = driver.getWindowHandle();
        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        productPage = new IvivuProductPage(driver);
        return productPage;
    }

    public String getSelectedProductName(List<WebElement> productList) {
        String productName = productList.get(0)
                .findElement(By.xpath("//iv-product-view//span[@class='pdv__hotel--name']")).getText();
        return productName;
    }
}
