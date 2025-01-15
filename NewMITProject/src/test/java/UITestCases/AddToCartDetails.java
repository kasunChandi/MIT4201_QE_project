package UITestCases;

import TestBase.TestBase;
import TestPages.AddtoCartPage;
import TestPages.HomePage;
import TestPages.IteamPage;
import TestPages.LoginPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;

public class AddToCartDetails extends TestBase {
    @Test(priority = 1)
    @Description("Test case to verify add to cart option")
    public void checkCartViewItems() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        IteamPage iteamPage =new IteamPage(driver);
        AddtoCartPage addtoCartPage = new AddtoCartPage(driver);
        String ExpectedHomePageTitle = homePage.getHomeTitle();

        try{
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();
            this.sleep(3);
            homePage.clickAddtoCartOption();
            this.sleep(5);
            homePage.clickCartOption();
            String ItemDetails =addtoCartPage.GetCartItemDetails();
            Assert.assertEquals(ItemDetails,"Sauce Labs Backpack");
            //test =extent.createTest("Successful view Item","System Successfully searched the item");
            //String sp1 = ErrorScreenCapture.getScreenshort()
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
