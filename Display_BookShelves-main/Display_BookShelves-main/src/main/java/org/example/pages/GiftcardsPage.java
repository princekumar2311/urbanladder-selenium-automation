package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class representing Gift Cards page
 */
public class GiftcardsPage extends BasePage {

    /**
     * Constructor to initialize driver via parent HomePage
     */
    public GiftcardsPage(WebDriver driver) {
        super(driver);
    }

    /* ===================== Gift Card Selection Fields ===================== */

    // Gift card amount field
    @FindBy(id = "denomination")
    private WebElement amount;

    // Gift card quantity field
    @FindBy(id = "quantity")
    private WebElement quantity;

    // Gift card design/theme selection
    @FindBy(xpath = "//div[@data-index='2']//img")
    private WebElement design;

    /* ===================== Sender Details Fields ===================== */

    // Sender first name input field
    @FindBy(xpath = "//*[@id='sender-details']//*[@id='firstname']")
    private WebElement sender_firstName;

    // Sender last name input field
    @FindBy(xpath = "//*[@id='sender-details']//*[@id='lastname']")
    private WebElement sender_lastName;

    // Sender email input field
    @FindBy(xpath = "//*[@id='sender-details']//*[@id='email']")
    private WebElement sender_Email;

    // Sender mobile number input field
    @FindBy(xpath = "//*[@id='sender-details']//*[@id='telephone']")
    private WebElement sender_mobileNumber;

    /* ===================== Receiver Details Fields ===================== */

    // Receiver first name input field
    @FindBy(xpath = "//*[@id='receiver-details']//*[@id='firstname']")
    private WebElement receiver_firstName;

    // Receiver last name input field
    @FindBy(xpath = "//*[@id='receiver-details']//*[@id='lastname']")
    private WebElement receiver_lastName;

    // Receiver email input field
    @FindBy(xpath = "//*[@id='receiver-details']//*[@id='email']")
    private WebElement receiver_Email;

    // Receiver gift message textarea
    @FindBy(xpath = "//*[@id='receiver-details']//*[@id='giftMessage']")
    private WebElement receiver_msg;

    @FindBy(xpath = "//button[normalize-space()='PREVIEW E-GIFT-CARD']")
    private WebElement previewButton;

    @FindBy(xpath = "//div[@data-index='2']//img")
    private WebElement chooseWishImage;

    /* ===================== Error Details Fields ===================== */

    // Invalid Email
    @FindBy(xpath ="//div[contains(text(),'Enter valid Email ID')]")
    private WebElement errorEmailMsg;

    //Invalid Mobile Number
    @FindBy(xpath = "//div[contains(text(),'Enter valid Mobile Number')]")
    private WebElement errorNumMsg;

    // Home page reference to navigate to Gift Cards
    HomePage page = new HomePage(driver);

    /**
     * Method to fill gift card form and validate email error message
     */

    public String giftCardPage() {

        // Navigate to Gift Cards page from Home Page
        page.goToGiftcardsPage();
        log.info("Navigated to Gift Card Page");
        // Switch to newly opened Gift Cards window/tab
        utils.switchToNew();
        log.info("Driver Switched to Gift Card Tab");
        // Wait until amount field is visible
        log.info("Wait till everything is visible");
        utils.visible(amount);

        // Enter gift card amount and quantity
//        amount.sendKeys("1000");
        utils.type(amount,"1000");
        log.info("Amount Entered : Rs.1000/-");
//        quantity.sendKeys("2");
        utils.type(quantity,"2");
        log.info("Quality Entered : 2");
        // Explicit wait for gift card theme
        WebElement theme = utils.waitForPresence(chooseWishImage);
        utils.scrollIntoView(theme);
        //ElementClickInterceptedException: element click intercepted: Element is not clickable
        //theme.click();
        utils.clickByJavascript(theme);
        log.info("Theme Selected");
        /* ===================== Fill Sender Details ===================== */

//        sender_firstName.sendKeys("Prince");
        utils.type(sender_firstName,"Prince");
//        sender_lastName.sendKeys("Kumar");
        utils.type(sender_lastName,"Kumar");
//        sender_Email.sendKeys("abc@gmailcom");
        utils.type(sender_Email,"abc@gmailcom"); // Invalid email to trigger validation
//        sender_mobileNumber.sendKeys("999999999999");
        utils.type(sender_mobileNumber,"999999999999"); // Invalid Mobile Number to trigger validation
        log.info("Entered Sender Details");
        /* ===================== Fill Receiver Details ===================== */

//        receiver_firstName.sendKeys("Adam");
        utils.type(receiver_firstName,"Adam");
//        receiver_lastName.sendKeys("Gilchrist");
        utils.type(receiver_lastName,"Gilchrist");
//        receiver_Email.sendKeys("xyz@gmail.com");
        utils.type(receiver_Email,"xyz@gmail.com");
//        receiver_msg.sendKeys("Happy birthday yaar.....");
        utils.type(receiver_msg,"Happy birthday yaar.....");
        log.info("Entered Receiver Details");

        // Scroll to gift message field
        utils.scrollIntoView(receiver_msg);
        utils.scrollIntoView(previewButton);

        return driver.getTitle();
    }
    public String validateEmail(){
        // Capture validation error message for invalid receiver email
        try {
            utils.visible(errorEmailMsg);
            utils.scrollIntoView(errorEmailMsg);
            return "Invalid Email ID";
        } catch (TimeoutException e) {
            return "Valid Email ID";
        }

    }

    public String validateMobile(){
        // Capture validation error message for invalid receiver email
        try {
            utils.visible(errorNumMsg);
            utils.scrollIntoView(errorNumMsg);
            return "Invalid Mobile Number";
        } catch (TimeoutException e) {
            return "Valid Mobile Number";
        }

    }
}
