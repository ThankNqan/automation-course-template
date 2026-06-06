package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.utils.Utils;

import io.appium.java_client.functions.ExpectedCondition;

public class IvivuHomePage extends BasePage {

    IvivuAllProductsPage searchResultPage;

    public IvivuHomePage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    By logoLocator = By.xpath("//img[contains(@src,'img/logo/logo-goc-ivv.svg')]");
    By searchFieldLocator = By.xpath("//input[@placeholder='Bạn muốn đi đâu?']");
    By startDateFieldLocator = By
            .xpath("(//div[contains(@class,'ds__btn-item')])[1]");
    By endDateFieldLocator = By
            .xpath("(//div[contains(@class,'ds__btn-item')])[2]");
    By datePickerLocator = By
            .xpath("//div[contains(@class,'container__months')]/div[contains(@class,'month-item')][1]");

    By nextMonthButtonLocator = By.xpath("(//button[@class='button-next-month'])[2]");

    By locationResultsFieldLocator = By.xpath("//tui-dropdown");
    By searchButtonLocator = By.xpath("//span[text()='Tìm']");

    public boolean isLogoDisplayed() {

        return isVisible(logoLocator);
    }

    public String selectDestination(String location) {
        By selectedLocation = By.xpath("//span[text()='" + location + "']");
        clickTo(searchFieldLocator);
        clickTo(selectedLocation);
        String getSearchText = getAttributeValue(searchFieldLocator);
        return getSearchText;
    }

    public String selectCheckinDate(String expectedStartDate) {
        clickTo(startDateFieldLocator);
        selectDate(expectedStartDate, startDateFieldLocator);
        String actualStartDate = getDate(startDateFieldLocator);
        return actualStartDate;
    }

    public String selectCheckoutDate(String expectedEndDate) {
        // performAction(endDateFieldLocator, "doubleclick");
        selectDate(expectedEndDate, endDateFieldLocator);
        String actualEndDate = getDate(endDateFieldLocator);
        return actualEndDate;
    }

    public IvivuAllProductsPage searchBooking() {

        clickTo(searchButtonLocator);
        searchResultPage = new IvivuAllProductsPage(this.driver);
        return searchResultPage;

    }

    public void selectDate(String expectedDate, By dateFieldLocator) {
        String expectedDay = Utils.extractDate(expectedDate, "day");
        String expectedMonth = Utils.extractDate(expectedDate, "month");
        String expectedYear = Utils.extractDate(expectedDate, "year");

        String currentMonth = getCurrentMonth(datePickerLocator);
        String currentYear = getCurrentYear(datePickerLocator);

        while (!(currentMonth.equals(expectedMonth) &&
                currentYear.equals(expectedYear))) {
            clickTo(nextMonthButtonLocator);
            currentMonth = getCurrentMonth(datePickerLocator);
            currentYear = getCurrentYear(datePickerLocator);
        }
        selectExpectedDay(datePickerLocator, expectedDay);
    }

    public String getDate(By datePickerLocator) {
        String actualDate = getElement(datePickerLocator).findElement(By.xpath(".//span[2]")).getText();
        return actualDate;
    }

    public String getCurrentMonth(By datePickerLocator) {
        return getElement(datePickerLocator).findElement(By.xpath(".//strong[@class='month-item-name']"))
                .getText().trim()
                .replace("Tháng ", "");
    }

    public String getCurrentYear(By datePickerLocator) {
        return getElement(datePickerLocator).findElement(By.xpath(".//span[@class='month-item-year']"))
                .getText().trim();
    }

    public void selectExpectedDay(By datePickerLocator, String expectedDay) {
        getElement(datePickerLocator)
                .findElement(By.xpath(".//div[@class='container__days']//div[text()='" + expectedDay + "']")).click();

    }

}
