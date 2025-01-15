package TestPages;

import TestBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddtoCartPage extends TestBase {
    WebDriver driver;

 @FindBy(xpath ="//*[@id='item_4_title_link']")
 WebElement itemDetails;


public AddtoCartPage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);
}

public String GetCartItemDetails(){
    String itemTitle = itemDetails.getText();
    System.out.println(" itemTitle " + itemTitle);
    return itemTitle;

}
}
