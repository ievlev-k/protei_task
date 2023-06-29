package org.pages;

import org.component.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class UsersPage extends BasePage {
    public UsersPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void addUser() {
        driver.findElement(By.xpath("//*[@id='addUser']")).click();
        driver.findElement(By.xpath("//*[@id='dataEmail']")).sendKeys("a@a");
        driver.findElement(By.xpath("//*[@id='dataPassword']")).sendKeys("132");
        driver.findElement(By.xpath("//*[@id='dataName']")).sendKeys("KuPuK");
        driver.findElement(By.xpath("//*[@id='dataGender']")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/fieldset/div[5]/select/option[1]")).click();
        driver.findElement(By.xpath("//*[@id='dataSelect12']")).click();
        driver.findElement(By.xpath("//*[@id='dataSelect22']")).click();
        driver.findElement(By.xpath("//*[@id='dataSend']")).click();
    }

    public List<User> getUsers() {
        int i = 0;
        return driver.findElements(By.xpath("/html/body/div[2]/table/tbody/tr")).stream().map(User::new
        ).toList();
    }

    public void transferToTable() {
        driver.findElement(By.xpath("//button[contains(@class, 'uk-modal-close')]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/nav/div/ul/li[3]/div/ul/li[1]")).click();

    }

}
