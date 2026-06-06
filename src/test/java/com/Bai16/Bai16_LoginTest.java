package com.Bai16;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pages.LoginPage;
import com.pages.HomePage;
import com.utils.BasicTest;

public class Bai16_LoginTest extends BasicTest {

        LoginPage loginPage;
        String url = "https://bantheme.xyz/hathanhauto/tai-khoan/";

        @Test(priority = 1)
        public void loginTestSuccess() throws Exception {
                // Launch website and navigate to https://bantheme.xyz/hathanhauto/tai-khoan/
                driver.get(url);
                loginPage = new LoginPage(driver);
                Assert.assertEquals(driver.getCurrentUrl(), url);

                String emailLogin = "ntthanhngan.2001@gmail.com";
                String passwordLogin = "Thanhngan@123456";

                loginPage.enterUsername(emailLogin);
                loginPage.enterPassword(passwordLogin);
                loginPage.clickLoginButton();

                HomePage homePage = new HomePage(driver);
                String welcomeText = homePage.getWelcomeMessage();
                // Verify displaying Xin Chào after login successfully
                Assert.assertTrue(welcomeText.contains("Xin chào"));

        }

        @Test()
        public void loginTestFailed() throws Exception {

                driver.get(url);
                loginPage = new LoginPage(driver);
                Assert.assertEquals(driver.getCurrentUrl(), url);

                String emailLogin = "ntthanhngan.2001@gmail.com";

                loginPage.enterUsername(emailLogin);
                loginPage.clickLoginButton();

                // Get the error message

                String errorMessage = loginPage.getErrorMessage();
                // Verify the error mesage display Mục nhập mật khẩu trống
                Assert.assertTrue(errorMessage
                                .contains("Mục nhập mật khẩu trống"));
        }

}
