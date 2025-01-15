package UITestCases;

import TestBase.TestBase;
import TestPages.HomePage;
import TestPages.LoginPage;
import Utils.ErrorScreenCapture;
import io.qameta.allure.Description;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageDetails extends TestBase {

  /*  @BeforeTest
    public void setup() {
        setUpBrowser();
    }
*/
    @Test(priority = 1)
    @Description("Test case to verify Home page Items")
    public void checkHomePageItems() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        String ExpectedHomePageTitle = homePage.getHomeTitle();


        try{
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();
            Assert.assertEquals(ExpectedHomePageTitle,"Swag Labs");
            this.sleep(5);
            String ItemOneDetails = homePage.getItemDetails("Backpack");

            Assert.assertEquals(ItemOneDetails,"Sauce Labs Backpack");
            String ItemTwoDetails = homePage.getItemDetails("Light");
            Assert.assertEquals(ItemTwoDetails,"Sauce Labs Bike Light");

            //test =extent.createTest("Successful view Item","System Successfully searched the item");
            //String sp1 = ErrorScreenCapture.getScreenshort()
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    @Description("Test case to verify Item Discriptions")
    public void checkHomePageItemsDiscriptions() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        String ExpectedHomePageTitle = homePage.getHomeTitle();


        try{
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();
            Assert.assertEquals(ExpectedHomePageTitle,"Swag Labs");
            this.sleep(5);
            String ItemOneDetails = homePage.getItemDiscriDetails("Item1");

            Assert.assertEquals(ItemOneDetails,"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
            String ItemTwoDetails = homePage.getItemDiscriDetails("Item2");
            Assert.assertEquals(ItemTwoDetails,"A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
