package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MorePage extends BasePage {


    public MorePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTitle() {
        return driver.findElement(By.xpath("//h3")).getText();
    }


}
