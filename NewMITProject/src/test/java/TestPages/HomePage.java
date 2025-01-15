package TestPages;

import TestBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    WebDriver driver;

    @FindBy(xpath = "//*[@id='item_4_img_link']/img")
    WebElement BackPackSelect;

    @FindBy(xpath = "//*[@id='item_4_title_link']/div")
    WebElement backpack;

    @FindBy(xpath = "//*[@id='item_0_title_link']/div")
    WebElement light;

    @FindBy(xpath = "//*[@id='inventory_container']/div/div[1]/div[2]/div[1]/div/text()")
    WebElement ItemDetails1;

    @FindBy(xpath = "//*[@id='inventory_container']/div/div[2]/div[2]/div[1]/div/text()")
    WebElement ItemDetails2;

    @FindBy(xpath ="//*[@id='add-to-cart-sauce-labs-backpack']")
      WebElement AddtoCart;

    @FindBy(xpath ="//*[@id='shopping_cart_container']")
     WebElement cartOption;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getHomeTitle(){
        String lgTitle = driver.getTitle();
        System.out.println("title homePage " + lgTitle);
        return lgTitle;
    }
    public String getItemDetails(String name ){
        String validateItem;
      if(name == "Backpack"){
          validateItem = backpack.getText();
          System.out.println("title homePage " + validateItem);
          return validateItem;
      }
      else if (name == "Light"){
          validateItem = light.getText();
          System.out.println("title homePage " + validateItem);
          return validateItem;
         }
      return null;
    }

    public String getItemDiscriDetails(String name ){
        String validateItem1;
        if(name == "Item1"){
            validateItem1 = ItemDetails1.getText();
            System.out.println("ItemDetails1 " + validateItem1);
            return validateItem1;
        }
        else if (name == "Item2"){
            validateItem1 = ItemDetails2.getText();
            System.out.println("ItemDetails1 " + validateItem1);
            return validateItem1;
        }
        return null;
    }

    public void clickBackPackButton(){
        BackPackSelect.click();
    }

    public void clickAddtoCartOption() { AddtoCart.click();}

    public void clickCartOption() {cartOption.click();}
}
