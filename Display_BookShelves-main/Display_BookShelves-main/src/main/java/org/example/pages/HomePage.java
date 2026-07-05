package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);

    }

    //Locators
    @FindBy(xpath = "//div[@data-testid='header-logo']/child::img[@alt='logo']")
    private WebElement logo;

    @FindBy(xpath = "//input[@id='searchInput']")
    private WebElement searchBox;

    @FindBy(xpath = "//img[@alt='Bookshelves']/parent::div/parent::a")
    private WebElement bookShelves;

    @FindBy(xpath = "//span[text()='Living']")
    private WebElement livingMenu;

    @FindBy(xpath = "//div[@id='category-menu-3']")
    private WebElement livingContainer;

    @FindBy(xpath = "//div[@id='category-menu-3']//div[contains(@data-testid,'navigation-desktop-sub-category')]")
    private List<WebElement> livingMenuItems;

    @FindBy(xpath = "//*[@class='ETrkE' and text()='Gift Cards']")
    private WebElement giftCards;

    //Functions
    public void checkPageLoad(){ utils.visible(logo); }


    public void goToBookshelvesPage(){
        utils.click(searchBox);
        utils.type(searchBox,"Bookshelves", Keys.ENTER);
        log.info("Bookshelves entered as input in search box and triggered for search");
    }

    public void goToGiftcardsPage(){
        utils.click(giftCards);
        log.info("\nGift Card Navigation Link Clicked");
    }

    public int getLivingMenuItemsList(){
        utils.hover(livingMenu);
        log.info("\nLiving Menu hovered");
        utils.visible(livingContainer);
        log.info("Living Menu container Visible");
        Map<String, List<String>> menuItems = new LinkedHashMap<>();
        log.info("Fetching Living Menu Items");
        for (WebElement subMenu : livingMenuItems){
            List<WebElement> subItems = subMenu.findElements(By.tagName("a"));
            if(subItems.isEmpty()) continue;
            String header = subItems.getFirst().getText().trim();
            List<String> items = new ArrayList<>();
            for(int i = 1;i<subItems.size();i++){
                String text = subItems.get(i).getText().trim();
                if(!text.isEmpty()){
                    items.add(text);
                }
            }
            menuItems.put(header,items);
        }
        utils.screenShot("LivingMenu_List");
        System.out.println("\nLiving Menu Items List");
        for (String header : menuItems.keySet()){
            System.out.println("\n"+header);
            for (String item : menuItems.get(header)){
                System.out.println("  ->  "+item);
            }
        }
        return menuItems.size();
    }

}
