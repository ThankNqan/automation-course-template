package com.steps;

import org.testng.Assert;

import com.mongodb.util.Util;
import com.pages.IvivuAllProductsPage;
import com.pages.IvivuBookingPage;
import com.pages.IvivuHomePage;
import com.pages.IvivuProductPage;
import com.utils.BasicTest;
import com.utils.ConfigReader;
import com.utils.DriverManager;
import com.utils.Utils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IvivuBookingSteps extends BasicTest {
    IvivuHomePage homePage;
    IvivuAllProductsPage searchResultPage;
    IvivuProductPage hotelDetailsPage;
    IvivuBookingPage bookingPage;

    String baseUrl = ConfigReader.get("base.url");

    int duration = 3;
    String checkinDate;

    // String checkinDate = Utils.addDaysToToday(60);
    // String checkoutDate = Utils.addDays(checkinDate, duration);

    @Given("the browser is opened")

    public void openBrower() {

        driver = DriverManager.getDriver();
        // Maximize the browser
        // driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(baseUrl);
        homePage = new IvivuHomePage(driver);
    }

    @When("select a {string} in the search bar")
    public void selectDestination(String destination) {
        String searchText = homePage.selectDestination(destination);
        Assert.assertEquals(searchText, destination, "Destination not selected correctly");
    }

    @And("choose checkin-date as {string}")
    public void selectCheckinDate(String text) {
        checkinDate = Utils.getStartDate(text);
        System.out.println("checkinDate" + checkinDate);
        String actualCheckinDate = homePage.selectCheckinDate(checkinDate);
        Assert.assertEquals(checkinDate, actualCheckinDate, "Checkin date mismatch");

    }

    @And("choose checkout-date as {string}")
    public void selectCheckoutDate(String text) {
        String checkoutDate = Utils.getEndDateFromStartDate(text, checkinDate);
        System.out.println("checkoutDate" + checkoutDate);
        String actualCheckoutDate = homePage.selectCheckoutDate(checkoutDate);
        Assert.assertEquals(checkoutDate, actualCheckoutDate, "Checkout date mismatch");
    }

    @And("click the 'Tìm' button")
    public void clickSearch() {
        searchResultPage = homePage.searchBooking();
    }

    @Then("a list of available hotels is displayed")
    public void verifyHotelList() {
        Assert.assertTrue(searchResultPage.isHotelListDisplayed(), "No hotels found");

    }

    @When("choose a hotel option")
    public void selectHotelOption() {
        hotelDetailsPage = searchResultPage.selectHotel();

    }

    @Then("the hotel details page is opened")
    public void verifyHotelDetailsPage() {
        Assert.assertNotNull(hotelDetailsPage, "Hotel details page not opened");
    }

    @When("click the 'Yêu cầu đặt' button")
    public void clickRequestButton() {
        hotelDetailsPage.clickRequestBooking();
    }

    @Then("the Travel Combo Request Form is displayed")
    public void verifyRequestForm() {
        Assert.assertTrue(hotelDetailsPage.isBookingFormDisplayed(), "Booking form should be displayed");
    }

}
