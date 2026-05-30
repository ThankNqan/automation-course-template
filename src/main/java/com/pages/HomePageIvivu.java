// package com.pages;

// import java.util.List;

// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;

// public class HomePageIvivu extends BasePage {

// public HomePageIvivu(WebDriver driver) {
// super(driver);
// // TODO Auto-generated constructor stub
// }

// By searchFieldLocator = By.xpath("//input[@placeholder='Bạn muốn đi đâu?']");
// By startDateFieldLocator = By.xpath("//button[@class='col-span-5
// ds__btn-left']");
// By endDateFieldLocator = By.xpath("//button[@class='col-span-5
// ds__btn-right']");
// By datePickerLocator =
// By.xpath("//div[@class='litepicker']//div[@class='month-item']");
// By previousdatePickerLocator = By.xpath("//div[@class='month-item'][1]']");
// By nextdatePickerLocator = By.xpath("//div[@class='month-item'][2]']");

// By locationResultsFieldLocator = By.xpath("//tui-dropdown");

// public void bookingTravel(String location) {
// By selectedLocation = By.xpath("//span[text()='" + location + "']");
// clickTo(searchFieldLocator);
// clickTo(selectedLocation);
// }

// public void selectDate(String expetecdDay, String expectedMonth, String
// expectedYear) {
// clickTo(startDateFieldLocator);
// List<WebElement> dateItems = findElements(datePickerLocator);
// int temp = 0;
// for (int i = 0; i < dateItems.size(); i++) {
// WebElement item = dateItems.get(i);
// String currentMonth =
// item.findElement(By.xpath("//strong[@class='month-item-name']")).getText();
// String currentYear = item
// .findElement(By.xpath("//strong[@class='month-item-year']")).getText();
// System.out.println("Current Month: " + currentMonth);
// System.out.println("Current Year: " + currentYear);

// if (!(currentMonth.equals(expectedMonth) &&
// currentYear.equals(expectedYear))) {
// continue;
// } else {
// temp = i;
// }

// }
// By expectedDayLocator =
// By.xpath("//div[@class='container__days']//div[text()='" + expetecdDay +
// "']");
// dateItems.get(temp).findElement(expectedDayLocator).click();
// }

// }
