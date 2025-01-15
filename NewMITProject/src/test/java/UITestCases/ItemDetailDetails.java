package UITestCases;

import TestBase.TestBase;
import TestPages.HomePage;
import TestPages.IteamPage;
import TestPages.LoginPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ItemDetailDetails extends TestBase {

    @Test(priority = 1)
    @Description("Test case to verify Home page Items")
    public void checkItemPageItemsDetails() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        IteamPage iteamPage =new IteamPage(driver);
        String ExpectedHomePageTitle = homePage.getHomeTitle();

        try{
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();
            Assert.assertEquals(ExpectedHomePageTitle,"Swag Labs");
            this.sleep(3);
            homePage.clickBackPackButton();
            this.sleep(5);
            String priceExp =iteamPage.getItemDetails();
            Assert.assertEquals(priceExp,"$29.99");
            //test =extent.createTest("Successful view Item","System Successfully searched the item");
            //String sp1 = ErrorScreenCapture.getScreenshort()
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
