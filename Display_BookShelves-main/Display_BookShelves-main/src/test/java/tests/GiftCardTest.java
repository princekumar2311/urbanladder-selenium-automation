package tests;

import base.BaseSetup;
import org.example.pages.GiftcardsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class GiftCardTest extends BaseSetup {
    GiftcardsPage giftPage;
    @BeforeMethod
    public void init(){
        giftPage=new GiftcardsPage(driver);
    }
    @Test(priority = 1)
    public void validateTitle() {
        String actualResult = giftPage.giftCardPage();
        Assert.assertEquals(actualResult, "Gift Cards", "Invalid Gift Card Page");
    }

    @Test(priority = 2)
    public void validateEmail() {
        String actualResult = giftPage.validateEmail();
        Assert.assertEquals(actualResult, "Valid Email ID", "Email ID validation failed");
    }

    @Test(priority = 3)
    public void validateMobileNumber() {
        String actualResult = giftPage.validateMobile();
        Assert.assertEquals(actualResult, "Valid Mobile Number", "Mobile number validation failed");
    }
}
