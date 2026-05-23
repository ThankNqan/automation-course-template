package com.Bai20;

import com.utils.BasicTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Bai20 extends BasicTest {

    private By phuTungLocator = By.xpath("//a[contains(@href,'thuong-hieu-phu-tung')]");
    private By xeElementLocator = By.xpath("//a[contains(@href,'phu-tung-range-rover')]");

    @Test()

    public void Test() {
        driver.get("https://bantheme.xyz/hathanhauto/");
        WebElement phuTungElement = getElement(phuTungLocator);
        action.moveToElement(phuTungElement).perform();

        WebElement xeElement = getElement(xeElementLocator);
        action.click(xeElement).perform();

    }

}
