package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IvivuBookingPage extends BasePage {

    public IvivuBookingPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    String url = "https://www.ivivu.com/thong-tin-booking";
    By bookingFormLocator = By.cssSelector("span.rtod__header--title");

    public Boolean isBookingFormDisplayed() {
        return isVisible(bookingFormLocator);
    }
}
