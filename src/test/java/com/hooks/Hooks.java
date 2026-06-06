package com.hooks;

// src/test/java/hooks/Hooks.java
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.ConfigReader;
import com.utils.DriverManager;
import com.utils.Utils;

public class Hooks {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    @Before
    public void initDriver() {
        // String baseUrl = ConfigReader.Fget("base.url");

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        options.setHeadless(ConfigReader.getBoolean("headless"));
        options.addArguments("window-size=1920,1080");
        options.addArguments("--no-sanbox");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);

        wait = new WebDriverWait(driver, Integer.parseInt(ConfigReader.get("explicit.wait")));
        action = new Actions(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot on failure
        if (scenario.isFailed()) {
            Utils.takeScrenshot(driver, scenario, scenario.getName());
        }

        DriverManager.quitDriver();
    }
}