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
        this.wait = new WebDriverWait(driver, 10);
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

    public List<WebElement> getElements(By locator) {
        List<WebElement> list;
        try {
            list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
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
