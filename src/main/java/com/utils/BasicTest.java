package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;

public abstract class BasicTest {

    public static final Logger logger = LogManager.getLogger();
    protected static WebDriver driver;
    // private String driverPath;

    @BeforeMethod
    public void preCondition() {
        // Chromedriver path
        // driverPath = "src/main/resources/WebDrivers/chromedriver.exe";
        // ChromeOptions options = new ChromeOptions();
        // System.setProperty("webdriver.chrome.driver", driverPath);
        // driver = new ChromeDriver(options);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Maximize the browser
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void postCondition() {
        // Quit the Browser
        driver.quit();
    }

    public void login(String username, String password) {

        // Declare locator
        WebElement loginEmailFieldLocator = driver.findElement(By.id("username"));
        WebElement loginPasswordFieldLocator = driver.findElement(By.id("password"));
        WebElement loginButtonLocator = driver.findElement(By.cssSelector("button[name='login']"));

        // Enter email address into email field
        loginEmailFieldLocator.sendKeys(username);
        // Enter password into password field
        loginPasswordFieldLocator.sendKeys(password);
        // Click login button
        loginButtonLocator.click();

        // Verify displaying Xin Chào after login successfully
        WebElement contentTextLocator = driver.findElement(By.className("woocommerce-MyAccount-content"));
        Assert.assertTrue(contentTextLocator.getText()
                .contains("Xin chào"));
    }

    public WebElement getElementLocator(By locator) {
        return driver.findElement(locator);
    }

    public void navigateTo(By locator) {
        driver.findElement(locator).click();
    }

    public void setData(By locator, String value) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public int parseNumberToInt(String numberString) {
        String cleanNumber = numberString.replaceAll("[^\\d]", "");
        return Integer.parseInt(cleanNumber);
    }
}