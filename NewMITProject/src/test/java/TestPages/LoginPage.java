package TestPages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id ="user-name")
    WebElement userName;

    @FindBy(id ="password")
    WebElement uPassword;

    @FindBy(id= "login-button")
    WebElement LoginButton;

    @FindBy(xpath = "/html/head/title")
    WebElement LoginTitle;

    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3")
    WebElement LoginPageError;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username){
        userName.sendKeys(username);
    }
    public void enterPassword(String password){
        uPassword.sendKeys(password);
    }
    public void clickLoginButton(){
        LoginButton.click();
    }
    public String getTitle(){
       String lgTitle = driver.getTitle();
        System.out.println("title " + lgTitle);
       return lgTitle;
    }

    public String getErrorPage(){
        String errormsg1 = LoginPageError.getText();
        System.out.println("errormsg" + errormsg1);
        return errormsg1;
    }


}
