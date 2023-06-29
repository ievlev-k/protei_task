package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {


//    @FindBy(css = "a[id='menuMore']")
//    public WebElement linkMenuMore;


//    @FindBy(xpath =)
//    public WebElement linkMenuUsersOpener;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public UsersPage transferToUserPage() {

        driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul/li[3]/div/ul/li[1]")).click();
        return new UsersPage(driver);
    }


    public MorePage transferToMorePage() {

        driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul/li[4]")).click();

        return new MorePage(driver);
    }
//    public String getMessageText() {
//        return driver.findElement(messageBy).getText();
//    }

    public String getTitle() {
        return driver.findElement(By.xpath("//h3")).getText();
    }
}
