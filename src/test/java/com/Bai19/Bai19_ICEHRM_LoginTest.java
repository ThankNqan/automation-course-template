package com.Bai19;

import java.io.ObjectInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.ExcelUtils;

public class Bai19_ICEHRM_LoginTest extends BasicTest {

    ExcelUtils excel = new ExcelUtils("src/test/resources/data/", "TestDataBai19.xlsx");

    @Test(dataProvider = "loginDataTest")
    public void testDataFeed(String user, String pass, String expected, String testcaseId) {
        // Navigate to https://icehrm-open.gamonoid.com/login.php
        String url = "https://icehrm-open.gamonoid.com/login.php";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        WebElement usernameFieldElement = driver.findElement(By.id("username"));
        WebElement passwordFieldElement = driver.findElement(By.id("password"));
        WebElement loginButtonElement = driver
                .findElement(By.xpath("//*[@id='loginForm']//button[contains(text(),'Log in')]"));
        usernameFieldElement.sendKeys(user);
        passwordFieldElement.sendKeys(pass);
        loginButtonElement.click();

        String actualMessage = errorMessage();
        if (actualMessage != "") {
            Assert.assertEquals(actualMessage, expected);
            excel.setCellData(actualMessage, 0, testcaseId, 5);
            excel.setCellData("FAILED", 0, testcaseId, 6);
        } else {
            // Verify display home page
            Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Home')]")).isDisplayed());
            excel.setCellData("PASSED", 0, testcaseId, 6);
        }

    }

    @DataProvider(name = "testLoginData")
    public Object[][] testData() {

        int totalRows = excel.getTotalRow(0);
        Object[][] data = new Object[totalRows - 1][4];
        for (int i = 1; i < totalRows; i++) {
            String user = excel.getData(0, i, 2);
            String pass = excel.getData(0, i, 3);
            String expected = excel.getData(0, i, 4);
            String testcaseId = excel.getData(0, i, 0);

            data[i - 1][0] = user;
            data[i - 1][1] = pass;
            data[i - 1][2] = expected;
            data[i - 1][3] = testcaseId;
        }
        return data;
    }

    public String errorMessage() {
        try {
            WebElement errorMessageElement = driver.findElement(By.cssSelector(".alert-danger"));
            String errorMessage = errorMessageElement.getText().trim();
            return errorMessage;
        } catch (Exception e) {
            return "";
        }

    }

    @DataProvider(name = "loginDataTest")
    public Object[][] dataLoginTest() {
        ExcelUtils excel = new ExcelUtils("D:\\automation-course-template\\src\\test\\resources\\data\\",
                "TestDataBai19.xlsx");
        int totalRows = excel.getTotalRow(0);
        Object[][] data = new Object[totalRows - 1][4];
        for (int i = 1; i < totalRows; i++) {
            String username = excel.getData(0, i, 2);
            String password = excel.getData(0, i, 3);
            String expectedMessage = excel.getData(0, i, 4);
            String testcaseId = excel.getData(0, i, 0);

            data[i - 1][0] = username;
            data[i - 1][1] = password;
            data[i - 1][2] = expectedMessage;
            data[i - 1][3] = testcaseId;
        }
        return data;
    }
}
