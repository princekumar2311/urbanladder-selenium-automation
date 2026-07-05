package tests;

import org.example.utils.DriverManager;
import org.example.utils.ReqUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        ReqUtils utils = new ReqUtils(driver);
        utils.screenShot(result.getName());
    }
}