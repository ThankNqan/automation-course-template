// package com.Bai22;

// import org.testng.Assert;
// import org.testng.annotations.Test;

// import com.pages.HomePageIvivu;
// import com.utils.BasicTest;
// import com.utils.Utils;

// public class Bai22_DatePickerTest extends BasicTest {

// HomePageIvivu homePage;
// String url = "https://www.ivivu.com/";
// String location = "Phú Quốc";

// @Test()
// public void datePickerTest() {
// driver.get(url);
// homePage = new HomePageIvivu(driver);
// Assert.assertEquals(driver.getCurrentUrl(), url);

// homePage.bookingTravel(location);
// Utils.hardWait(2000);
// homePage.selectDate("22", "Tháng 6", "2026");

// }
// }
