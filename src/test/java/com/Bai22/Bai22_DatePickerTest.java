package com.Bai22;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.IvivuAllProductsPage;
import com.pages.IvivuBookingPage;
import com.pages.IvivuHomePage;
import com.pages.IvivuProductPage;
import com.utils.BasicTest;
import com.utils.Utils;

public class Bai22_DatePickerTest extends BasicTest {

    IvivuHomePage homePage;
    IvivuAllProductsPage productsPage;
    IvivuProductPage productDetailsPage;
    IvivuBookingPage bookingPage;

    String url = "https://www.ivivu.com/";
    String expectedLocation = "Phú Quốc";

    int duration = 3;

    @Test()
    public void datePickerTest() {

        driver.get(url);
        homePage = new IvivuHomePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), url);
        Assert.assertTrue(homePage.isLogoDisplayed());

        // Select location
        String searchText = homePage.selectLocation(expectedLocation);
        Assert.assertEquals(searchText, expectedLocation);

        // Select date duration
        String expectedStartDate = addDaysToToday(60);
        // expectedStartDate = "31-12-2026";
        String expectedEndDate = addDays(expectedStartDate, duration);
        System.out.println("StartDate: " + expectedStartDate);
        System.out.println("EndDate: " + expectedEndDate);

        String actualStartDate = homePage.selectStartDate(expectedStartDate);
        Assert.assertEquals(expectedStartDate, actualStartDate);
        String actualEndDate = homePage.selectEndDate(expectedEndDate);
        Assert.assertEquals(expectedEndDate, actualEndDate);

        productsPage = homePage.searchBooking(driver);

        productDetailsPage = productsPage.selectProduct(driver);

        productDetailsPage.clickBooking();

        // bookingPage = new IvivuBookingPage(driver);

        // Assert.assertTrue(bookingPage.isBookingFormDisplayed());
        Assert.assertTrue(productDetailsPage.isBookingFormDisplay());
    }
}
