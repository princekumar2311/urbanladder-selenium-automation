package tests;

import base.BaseSetup;
import org.example.pages.BookShelvesPage;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class BookShelvesTest extends BaseSetup {

    BookShelvesPage bookPage;

    @BeforeMethod
    public void init() {
        bookPage = new BookShelvesPage(driver);
    }


    @Test(priority = 1)
    public void openBookPage(){
        bookPage.searchBookshelves();
    }

    @Test(priority = 2)
    public void clickFilter(){
        String actualTitle = bookPage.openFilters();
        String expectedTitle = "Filter and Sort";
        Assert.assertEquals(actualTitle,expectedTitle,"Filter and Sort Button not Working");
    }

    @Test(priority = 3)
    public void clickStorageType(){
        bookPage.applyFilter("15000");
    }


    @Test(priority = 4)
    public  void printTopThreeProducts(){
        bookPage.topThreeProducts();
    }

    @Test(priority = 5)
    public void navigateToHomePage(){
        String actualTitle = bookPage.navigateToHomePage();
        String expectedTitle = "Buy Furniture Online: Upto 70% off in this Festive Season";
        Assert.assertEquals(actualTitle,expectedTitle,"Navigation to Homepage failed");
    }

}