package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthPage extends BasePage {


    private final WebElement authButton = driver.findElement(By.xpath(
            "//*[@id='authButton']"));


    private final WebElement email = driver.findElement(By.xpath(
            "//*[@id='loginEmail']"));


    private final WebElement password = driver.findElement(By.xpath(
            "//*[@id='loginPassword']"));


    public AuthPage(WebDriver webDriver) {
        super(webDriver);


    }

    public HomePage authorization(String userName, String userPassword) {

        email.sendKeys(userName);
        System.out.println(userName);
        password.sendKeys(userPassword);

        authButton.click();

        return new HomePage(driver);
    }


}
