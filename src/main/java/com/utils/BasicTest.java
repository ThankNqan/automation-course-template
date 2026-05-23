package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BasicTest {

    public static final Logger logger = LogManager.getLogger();
    protected static WebDriver driver;
    protected WebDriverWait wait;
    // private String driverPath;
    protected String baseUrl;
    protected Actions action;

    @BeforeMethod
    // @Parameters({ "baseURL" })
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
        wait = new WebDriverWait(driver, 3);
        action = new Actions(driver);
    }

    @AfterMethod
    public void postCondition() {
        // Quit the Browser
        driver.quit();
    }

    public void login(String username, String password) {

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
        WebElement contentTextLocator = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.className("woocommerce-MyAccount-content")));
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

    public WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // @AfterSuite()
    // public void resetCartList() {
    // driver.get("https://bantheme.xyz/hathanhauto/tai-khoan");
    // login("ntthanhngan.2001@gmail.com", "Thanhngan@123456");
    // driver.findElement(By.cssSelector(".header-main a[title*='Giỏ
    // hàng']")).click();
    // // On the Cart page
    // List<WebElement> cartList =
    // driver.findElements(By.cssSelector(".cart_item"));
    // for (WebElement item : cartList) {
    // item.findElement(By.cssSelector(".remove")).click();
    // Utils.hardWait();
    // }
    // WebElement emptyCartMessage = driver.findElement(By.cssSelector("cart-empty
    // woocommerce-info"));
    // Assert.assertTrue(emptyCartMessage.isDisplayed());

    // }
}