package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

//    public WebElement linkMenuUsersOpener = driver.findElement(By.xpath("//*[@id='menuUsersOpener']"));


    public HomePage(WebDriver webDriver){
        super( webDriver);
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }
    
    public UsersPage transferToUserPage(){
//        linkMenuUsersOpener.click();
        return new UsersPage(driver);
    }

//    public String getMessageText() {
//        return driver.findElement(messageBy).getText();
//    }
}
