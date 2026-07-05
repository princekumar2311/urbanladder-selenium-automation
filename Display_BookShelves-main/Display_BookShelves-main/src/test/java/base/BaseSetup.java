package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseSetup {

    protected WebDriver driver;
    protected Logger log = LogManager.getLogger(this.getClass());
    final String baseURL = "https://www.urbanladder.com/";
    @BeforeClass
    public void setUp() {
        log.info("===== TEST SETUP STARTED =====");

        driver = DriverManager.getDriver();
        driver.get(baseURL);

        log.info("Browser launched and URL opened");
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        log.info("Browser closed");
        log.info("===== TEST TEARDOWN COMPLETED =====");
    }
}