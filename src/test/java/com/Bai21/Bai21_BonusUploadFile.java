package com.Bai21;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utils.BasicTest;

public class Bai21_BonusUploadFile extends BasicTest {

    @Test()
    public void testUploadFileSuccess() {
        String url = "https://the-internet.herokuapp.com/upload";

        String filePath = "src/test/resources/data/image/";
        String fileName = "googleicon.png";
        String absoluteFilePath = getFilePath(filePath + fileName);

        By uploadLocator = By.cssSelector("#file-upload");
        By uploadButtonLocator = By.cssSelector("#file-submit");

        By contentResultLocator = By.cssSelector("#content h3");
        By fileResultLocator = By.cssSelector("#uploaded-files");

        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);

        WebElement uploadInput = getElement(uploadLocator);
        uploadInput.sendKeys(absoluteFilePath);

        clickElement(uploadButtonLocator);

        Assert.assertEquals(getTextByElement(contentResultLocator), "File Uploaded!");
        Assert.assertEquals(getTextByElement(fileResultLocator), fileName.trim());
    }

}
