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

        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        WebElement originFirstElement = getElement(elementALocator);
        WebElement originSecondElement = getElement(elementBLocator);

        String originFirstHeader = getTextByElement(elementALocator);
        String originSecondHeader = getTextByElement(elementBLocator);

        action.dragAndDrop(originFirstElement, originSecondElement).perform();

        String finalFirstHeader = getTextByElement(elementALocator);
        String finalSecondHeader = getTextByElement(elementBLocator);

        Assert.assertEquals(originFirstHeader, finalSecondHeader);
        Assert.assertEquals(originSecondHeader, finalFirstHeader);

    }
}
