package UITestCases;

import TestPages.HomePage;
import TestPages.LoginPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import TestBase.TestBase;
import org.testng.annotations.Test;

public class LoginWithCorrectLoginDetails extends TestBase{

    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test(priority = 1)
    @Description("Test case to verify the valid login")
    public void loginWithCorrectUserData() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        String ExpectedLoginPageTitle = loginPage.getTitle();
        String ExpectedHomePageTitle = homePage.getHomeTitle();

        try{
            Assert.assertEquals(ExpectedLoginPageTitle,"Swag Labs");
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();
            Assert.assertEquals(ExpectedHomePageTitle,"Swag Labs");
            this.sleep(5);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    @Description("Test case to verify the in valid login")
    public void loginWithInCorrectUserData() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        String ExpectedLoginPageTitle = loginPage.getTitle();


        try {
            Assert.assertEquals(ExpectedLoginPageTitle, "Swag Labs");
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce123");
            loginPage.clickLoginButton();
            String ErrorMsg = loginPage.getErrorPage();
            Assert.assertEquals(ErrorMsg, "Epic sadface: Username and password do not match any user in this service");
            this.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
