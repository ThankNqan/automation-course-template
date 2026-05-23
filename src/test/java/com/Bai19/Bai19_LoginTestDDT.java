package com.Bai19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.BasicTest;
import com.utils.ExcelUtils;
import com.utils.Utils;

public class Bai19_LoginTestDDT extends BasicTest {
    ExcelUtils excel = new ExcelUtils("src/test/resources/data/", "TestData.xlsx");

    @Test(dataProvider = "loginTestData")
    public void loginTestSuccess(String username, String password, String expectedMessage, String testcaseId)
            throws Exception {
        // Launch website and navigate to https://bantheme.xyz/hathanhauto/tai-khoan/
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        // Declare locator
        WebElement loginEmailFieldLocator = driver.findElement(By.id("username"));
        WebElement loginPasswordFieldLocator = driver.findElement(By.id("password"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        loginEmailFieldLocator.sendKeys(username);
        loginPasswordFieldLocator.sendKeys(password);
        loginButtonLocator.click();
        Utils.hardWait(3000);

        // Verify the error mesage display Mục nhập mật khẩu trống
        String actualMessage = getNotificationMessage();
        excel.setCellData(actualMessage, 0, testcaseId, 5);

        if (actualMessage.contains(expectedMessage))
            excel.setCellData("PASSED", 0, testcaseId, 6);
        else
            excel.setCellData("FAILED", 0, testcaseId, 6);

        Assert.assertTrue(actualMessage
                .contains(expectedMessage));

    }

    @DataProvider(name = "loginTestData")
    public Object[][] dataTest() {
        // Object[][] data = {
        // { "ntthanhngan.2001@gmail.com", "Thanhngan@123456", "" },
        // { "", "Thanhngan@123456", "Yêu cầu tên tài khoản" },
        // { "ntthanhngan.2001@gmail.com", "", "Mục nhập mật khẩu trống" },
        // { "ntthanhngan@gmail.com", "Thanhngan@123456",
        // "Địa chỉ email không xác định. Kiểm tra lại hoặc thử tên người dùng của bạn"
        // }

        int totalRows = excel.getTotalRow(0);
        Object[][] data = new Object[totalRows - 1][4];
        for (int i = 1; i < totalRows; i++) {
            String username = excel.getData(0, i, 2);
            String password = excel.getData(0, i, 3);
            String expectedResult = excel.getData(0, i, 4);
            String testcaseId = excel.getData(0, i, 0);
            data[i - 1][0] = username;
            data[i - 1][1] = password;
            data[i - 1][2] = expectedResult;
            data[i - 1][3] = testcaseId;
            System.out.println("TC: " + testcaseId + " Username: " + username + " Pass: " + password);
        }
        return data;
    }

    public String getNotificationMessage() {
        String notiMessage;
        try {
            WebElement errorMessageLocator = driver.findElement(By.cssSelector("ul[role=\"alert\"] li"));
            String errorMessage = errorMessageLocator.getText();
            notiMessage = errorMessage;
        } catch (Exception exception) {
            notiMessage = "";
        }
        return notiMessage;

    }

}
