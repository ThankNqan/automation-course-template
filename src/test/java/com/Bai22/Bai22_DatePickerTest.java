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
    IvivuAllProductsPage searchResultPage;
    IvivuProductPage productDetailsPage;
    IvivuBookingPage bookingPage;

    String url = "https://www.ivivu.com/";
    String expectedLocation = "Phú Quốc";

    int duration = 3;

    @Test()
    public void datePickerTest() {

        driver.get(url);
        homePage = new IvivuHomePage(driver);
        Assert.assertTrue(homePage.isLogoDisplayed());

        // Select location
        String searchText = homePage.selectDestination(expectedLocation);
        Assert.assertEquals(searchText, expectedLocation);

        // Select date duration
        String expectedStartDate = Utils.addDaysToToday(60);
        // expectedStartDate = "31-12-2026";
        String expectedEndDate = Utils.addDays(expectedStartDate, duration);
        System.out.println("StartDate: " + expectedStartDate);
        System.out.println("EndDate: " + expectedEndDate);

        String actualStartDate = homePage.selectCheckinDate(expectedStartDate);
        Assert.assertEquals(expectedStartDate, actualStartDate, "Start date mismatch");

        String actualEndDate = homePage.selectCheckoutDate(expectedEndDate);
        Assert.assertEquals(expectedEndDate, actualEndDate, "End date mismatch");

        searchResultPage = homePage.searchBooking();
        Assert.assertTrue(searchResultPage.isHotelListDisplayed(), "Products page should have results");

        productDetailsPage = searchResultPage.selectHotel();
        Assert.assertNotNull(productDetailsPage, "Product details page should be returned");

        bookingPage = productDetailsPage.clickRequestBooking();
        Assert.assertTrue(bookingPage.isBookingFormDisplayed(), "Booking form should be displayed");

        // bookingPage = new IvivuBookingPage(driver);

        // Assert.assertTrue(bookingPage.isBookingFormDisplayed());
    }
}
