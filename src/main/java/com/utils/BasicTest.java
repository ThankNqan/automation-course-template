package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--no-sanbox");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        // Maximize the browser
        // driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 3);
        action = new Actions(driver);
    }

    @AfterMethod
    public void postCondition() {
        // Quit the Browser
        driver.quit();
    }

    public void login() {
        String username = "ntthanhngan.2001@gmail.com";
        String password = "Thanhngan@123456";

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
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
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

    public WebElement getPresenceElement(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Invalid locator: " + locator);
            return null;
        }

    }

    public List<WebElement> getElementsList(By locator) {
        List<WebElement> list;
        try {
            list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            Assert.fail("No search results found.");
            return new ArrayList<>();
        }
        return list;

    }

    public void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public String getTextByElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public String getTextByValueAttribute(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("value");
    }

    public String getFilePath(String path) {
        String absolutePath = new File(path).getAbsolutePath();
        File file = new File(absolutePath);
        if (file.exists()) {
            return absolutePath;
        } else {
            Assert.fail("File does not exist: " + absolutePath);
            return null;
        }

    }

    public void perfomance(String menuText, String actionText) {
        By selectedMenuLocator = By.xpath("//a[contains(text(),'" + menuText + "')]");
        WebElement selectedMenuElement = getPresenceElement(selectedMenuLocator);
        if (actionText.equals("hover"))
            action.moveToElement(selectedMenuElement).perform();
        else if (actionText.equals("click"))
            action.click(selectedMenuElement).perform();
        else
            Assert.fail("Action is invalid");
    }

    public String addDaysToToday(int daysToAdd) {
        LocalDate result = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return result.format(fmt);
    }

    public String addDays(String startDate, int daysToAdd) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(startDate, fmt);
            return date.plusDays(daysToAdd).format(fmt);
        } catch (Exception e) {
            return null;
        }
    }

    public String extractDate(String date, String type) {
        String extractedDate = "";
        switch (type) {
            case "day":
                extractedDate = date.substring(0, 2).replaceFirst("^0", "");
                break;
            case "month":
                extractedDate = date.substring(3, 5).replaceFirst("^0", "");
                break;
            case "year":
                extractedDate = date.substring(date.length() - 4);
                break;
            default:
                break;
        }
        return extractedDate;
    }

}