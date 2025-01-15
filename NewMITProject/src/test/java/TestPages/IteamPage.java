package TestPages;

import TestBase.TestBase;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class IteamPage extends TestBase {
    WebDriver driver;

 @FindBy(xpath ="//*[@id='inventory_item_container']/div/div/div[2]/div[3]")
 WebElement itemprice;


    public IteamPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getItemDetails()
    {
        String Itempr = itemprice.getText();
        System.out.println("price " + Itempr);
        return Itempr;
    }
}
