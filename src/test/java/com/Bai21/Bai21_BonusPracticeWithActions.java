package com.Bai21;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utils.BasicTest;

public class Bai21_BonusPracticeWithActions extends BasicTest {

    @Test()
    public void testDragAndDrop() {
        String url = "https://the-internet.herokuapp.com/drag_and_drop";

        By elementALocator = By.cssSelector("#column-a");
        By elementBLocator = By.cssSelector("#column-b");
        By headerALocator = By.cssSelector("#column-a>header");
        By headerBLocator = By.cssSelector("#column-b>header");

        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        WebElement originFirstElement = getElement(elementALocator);
        WebElement originSecondElement = getElement(elementBLocator);

        String originFirstHeader = getTextByElement(headerALocator);
        String originSecondHeader = getTextByElement(headerBLocator);
        System.out.println(originFirstHeader);
        System.out.println(originSecondHeader);

        action.dragAndDrop(originFirstElement, originSecondElement).perform();

        WebElement finalFirstElement = getElement(elementALocator);
        WebElement finalSecondElement = getElement(elementBLocator);

        String finalFirstHeader = getTextByElement(headerALocator);
        String finalSecondHeader = getTextByElement(headerBLocator);

        System.out.println(finalFirstHeader);
        System.out.println(finalSecondHeader);

        Assert.assertEquals(originFirstHeader, finalSecondHeader);
        Assert.assertEquals(originSecondHeader, finalFirstHeader);

    }
}
