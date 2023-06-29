package org.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

}
