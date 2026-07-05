package tests;

import base.BaseSetup;
import org.example.pages.HomePage;
import org.example.utils.ReqUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseSetup {

    HomePage homePage;

    @BeforeMethod
    public void init() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testLivingMenu() {
        int actualOutput = homePage.getLivingMenuItemsList();
        Assert.assertEquals(actualOutput,5,"Fetching Living Menu Items Failed");

    }
}