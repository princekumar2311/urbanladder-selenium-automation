package org.example.pages;

import java.time.Duration;
import java.util.List;

import org.example.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookShelvesPage extends BasePage {

    private final WebDriverWait filterWait;
    private final Actions actions;

    public BookShelvesPage(WebDriver driver) {
        super(driver);
        this.filterWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
    }

    // ---------------- Locators (FindBy) ----------------

    @FindBy(id = "productsContainer")
    private WebElement filtersButton;

    @FindBy(xpath = "//div[text()='Filter and Sort']")
    private WebElement filterContainer;

    @FindBy(css = "[aria-label='Storage Type']")
    private WebElement storageType;

    @FindBy(xpath = "//div[@role='checkbox' and contains(.,'Open Storage')]")
    private WebElement openStorage;

    @FindBy(css = "[aria-label='Storage Availability']")
    private WebElement availability;

    @FindBy(xpath = "//div[@role='checkbox' and contains(.,'With Storage')]")
    private WebElement withStorage;

    @FindBy(css = "[aria-label='Price']")
    private WebElement priceFilter;

    @FindBy(css = "[aria-label='Maximum value ₹99,999']")
    private WebElement maxPrice;

    @FindBy(xpath = "//*[@data-testid='plp-filter-apply-button']")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@role='link']")
    private List<WebElement> products;

    // ---------------- Actions ----------------
    HomePage home = new HomePage(driver);

    public void searchBookshelves() {
        home.goToBookshelvesPage();
        log.info("Navigated to BookShelves Listing Page");
    }

    public String openFilters() {
        utils.click(filtersButton);
        log.info("All Filter button Clicked and Filter Container Opened");

        return utils.getText(filterContainer);
    }

    public void applyFilter(String price) {
        System.out.println("Applying Filters");

        filterWait.until(ExpectedConditions.elementToBeClickable(storageType));
        actions.moveToElement(storageType).click().perform();
        log.info("Storage Type filter clicked");
        filterWait.until(ExpectedConditions.elementToBeClickable(openStorage));
        actions.moveToElement(openStorage).click().perform();
        log.info("Storage Type : Open Storage Selected");

        filterWait.until(ExpectedConditions.elementToBeClickable(availability));
        actions.moveToElement(availability).click().perform();
        log.info("Availability Filter Clicked");
        filterWait.until(ExpectedConditions.elementToBeClickable(withStorage));
        actions.moveToElement(withStorage).click().perform();
        log.info("Availability : With Storage Selected");

        filterWait.until(ExpectedConditions.elementToBeClickable(priceFilter));
        actions.moveToElement(priceFilter).click().perform();
        log.info("Price Filter Clicked");
        utils.type(maxPrice,price,Keys.TAB);
        log.info("Set Max Price to Rs.15000/-");

        filterWait.until(ExpectedConditions.elementToBeClickable(applyButton));
        actions.moveToElement(applyButton).click().perform();
        log.info("Apply Button Clicked");
        // wait until products refresh
        filterWait.until(driver -> !products.isEmpty());
    }

    // ---------------- Product Handling ----------------

    public List<WebElement> getProducts() {
        return products;
    }

    public String getProductName(WebElement product) {
        return product
                .findElement(By.xpath(".//h3[@class='XxwSy']"))
                .getText()
                .trim();
    }

    public int getProductPrice(WebElement product) {
        WebElement priceEl =
                product.findElement(By.xpath(".//div[@class='UYQNp']"));
        return Integer.parseInt(priceEl.getText().replaceAll("[^0-9]", ""));
    }

    public void topThreeProducts(){
        System.out.println("\nFirst 3 Bookshelves below Rs. 15000:\n");
        int count = 0;
        for (WebElement product : products) {
            String name = getProductName(product);
            int price = getProductPrice(product);

            if (price <= 15000) {
                System.out.println(++count + ". " + name + " - Rs. " + price);
            }
            if (count == 3)
                break;
        }
        utils.scrollIntoView(products.get(0));
        utils.screenShot("Top_Three_BookShelves");
    }
    public String navigateToHomePage(){
        driver.navigate().to("https://www.urbanladder.com/");
        log.info("Navigated Back to HomePage");
        return driver.getTitle();
    }
}
