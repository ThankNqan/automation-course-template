package com.Bai21;

import org.openqa.selenium.By;
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

        By breadCrumLocator = By.cssSelector(".woocommerce-breadcrumb");

        driver.get("https://bantheme.xyz/hathanhauto/tai-khoan/");

        perfomance(selectedMenu, "hover");
        perfomance(selectedSubMenu, "hover");
        perfomance(selectedItem, "click");

        String breadcrumbsText = getTextByElement(breadCrumLocator);
        Assert.assertTrue(breadcrumbsText.contains(expectedBreadCrumText),
                "The breadcrumb does not reflect the correct path. " +
                        "\nActual: " + breadcrumbsText +
                        "\nExpected: " + expectedBreadCrumText);

    }
}
