package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IvivuProductPage extends BasePage {

    public IvivuProductPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    By productNameLocator = By.xpath("//h1[@class='ho2__title--hotel-name']");
    By orderButtonLocator = By.xpath("//button[@class='ho2__title--book-now-btn']");
    By roomList = By.xpath("//div[@class='hdt__flex--right']");
    By bookingButtonLocator = By.xpath("//span[@class='booking-text']");
    By formRequestLocator = By.xpath("//section//div[contains(@class,'rtod__container')]");
    By detailComboLocator = By.xpath("//iv-detail-combos");

    public String getProductName() {
        return getText(productNameLocator);
    }

    public void clickBooking() {
        // clickTo(orderButtonLocator);
        // List<WebElement> roomList = getRoomList();
        // roomList.get(0).findElement(By.xpath("//button[text()=' Yêu cầu đặt
        // ']")).click();
        if (isVisible(detailComboLocator))
            clickTo(bookingButtonLocator);

    }

    public List<WebElement> getRoomList() {
        return getElements(roomList);
    }

    public boolean isBookingFormDisplay() {
        return isVisible(formRequestLocator);
    }
}
