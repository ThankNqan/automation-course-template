package com.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    public WebDriver driver;
    protected Actions action;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 3);
        this.action = new Actions(driver);
        PageFactory.initElements(this.driver, driver);
    }

    public void clickTo(By locator) {
        WebElement element = getElement(locator);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            Assert.fail("Cannot click to: " + locator + " | Exception: " + e.getMessage());
        }
    }

    public void navigateTo(By locator) {
        clickTo(locator);
    }

    public void sendKeys(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public boolean isVisible(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            Assert.fail("Locator is not displayed " + locator);
            return false;

        }
    }

    public WebElement getElement(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Locator is not existed: " + locator + " | Exception: " + e.getMessage());
            return null;
        }
    }

    public List<WebElement> findElements(By locator) {
        List<WebElement> list;
        try {
            WebDriverWait tmpWait = new WebDriverWait(this.driver, 10);
            list = tmpWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            Assert.fail("No search results found.");
            return new ArrayList<>();
        }
        return list;
    }

    public WebElement findPresenceElement(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Locator is not existed: " + locator + " | Exception: " + e.getMessage());
            return null;
        }
    }

    public void perfomance(String menuText, String actionText) {
        By selectedMenuLocator = By.xpath("//a[contains(text(),'" + menuText + "')]");
        WebElement selectedMenuElement = findPresenceElement(selectedMenuLocator);
        if (actionText.equals("hover"))
            action.moveToElement(selectedMenuElement).perform();
        else if (actionText.equals("click"))
            action.click(selectedMenuElement).perform();
        else
            Assert.fail("Action is invalid");
    }

    public String getText(By locator) {
        WebElement element = getElement(locator);
        try {
            return element.getText();
        } catch (Exception e) {
            Assert.fail("Failed to get text at:" + locator + " | Exception: " + e.getMessage());
            return null;
        }

    }

    public String formatDate(String dayStr, String monthStr, String yearStr) {
        try {
            int day = Integer.parseInt(dayStr.replaceAll("\\D+", ""));
            int month = Integer.parseInt(monthStr.replaceAll("\\D+", ""));
            int year = Integer.parseInt(yearStr.replaceAll("\\D+", ""));
            return String.format("%02d-%02d-%04d", day, month, year);
        } catch (NumberFormatException e) {
            // fallback: join raw values if parsing fails
            return dayStr + "-" + monthStr + "-" + yearStr;
        }
    }

    public String getToday() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return today.format(fmt);
    }

    public String addDaysToToday(int daysToAdd) {
        LocalDate result = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return result.format(fmt);
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

    public void performAction(By locator, String actionText) {
        WebElement element = getElement(locator);
        switch (actionText) {
            case "click":
                action.click(element).perform();
                break;
            case "hover":
                action.moveToElement(element).perform();
                break;
            case "doubleclick":
                action.doubleClick(element).perform();
                break;
            default:
                Assert.fail("Action is invalid");
                break;
        }
    }

    public String getAttributeValue(By locator) {
        return getElement(locator).getAttribute("value");
    }
}
