package com.Bai21;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BasicTest;

public class Bai21_BreadcrumbTest extends BasicTest {

    @Test()
    public void breadcrumbTest() {

        String selectedMenu = "Hệ thống truyền động, Khung gầm";
        String selectedSubMenu = "Hệ thống phanh";
        String selectedItem = "Phanh trước ô tô";
        String expectedBreadCrumText = selectedMenu + " / " + selectedSubMenu + " / " + selectedItem;

        By selectedMenuLocator = By.xpath("//a[contains(text(),'" + selectedMenu + "')]");
        By selectedSubMenuLocator = By.xpath("//a[contains(text(),'" + selectedSubMenu + "')]");
        By selectedItemLocator = By.xpath("//a[contains(text(),'" + selectedItem + "')]");

        By breadCrumLocator = By.cssSelector(".woocommerce-breadcrumb");

        driver.get("https://bantheme.xyz/hathanhauto/tai-khoan/");

        WebElement selectedMenuElement = getPresenceElement(selectedMenuLocator);
        WebElement selectedSubMenuElement = getPresenceElement(selectedSubMenuLocator);
        WebElement selectedItemElement = getPresenceElement(selectedItemLocator);

        action.moveToElement(selectedMenuElement).perform();
        action.moveToElement(selectedSubMenuElement).perform();
        action.click(selectedItemElement).perform();

        String breadcrumbsText = getTextByElement(breadCrumLocator);
        breadcrumbsText = breadcrumbsText.substring(0, 4);
        Assert.assertTrue(breadcrumbsText.contains(expectedBreadCrumText),
                "The breadcrumb does not reflect the correct path. " +
                        "\nActual: " + breadcrumbsText +
                        "\nExpected: " + expectedBreadCrumText);

    }
}
