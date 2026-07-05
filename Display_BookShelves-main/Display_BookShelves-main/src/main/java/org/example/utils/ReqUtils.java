package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.io.FileHandler;

public class ReqUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public ReqUtils(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    // Wait for visibility
    public WebElement visible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //wait for presence
    public WebElement waitForPresence(WebElement element){
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@data-index='2']//img")
                )
        );
    }

    // Click
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    //click by js
    public void clickByJavascript(WebElement theme) {
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript(
                "arguments[0].click();",
                theme
        );

    }

    // Type
    public void type(WebElement element, CharSequence... keys) {
        WebElement el = visible(element);
        el.clear();
        el.sendKeys(keys);
    }

    // Hover
    public void hover(WebElement element) {
        actions.moveToElement(visible(element)).pause(1000).perform();
    }

    // Scroll
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // Get text
    public String getText(WebElement element) {
        return visible(element).getText();
    }

    public void switchToNew(){
        String current = driver.getWindowHandle();
        for(String handle:driver.getWindowHandles()){
            if(!handle.equals(current)){
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public void switchToParent(){
        for(String handle:driver.getWindowHandles()){
            driver.switchTo().window(handle);
            break;
        }
    }

    public void screenShot( String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String projectPath = System.getProperty("user.dir");

        // Destination with custom file name
        File destination = new File(
                projectPath + "/Visual_Output/" + fileName + ".png"
        );

        try {
//            destination.getParentFile().mkdirs();
            FileHandler.copy(source, destination);

//            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
