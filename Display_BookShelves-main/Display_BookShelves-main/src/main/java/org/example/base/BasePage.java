package org.example.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.ReqUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected ReqUtils utils;
    protected Logger log;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.utils = new ReqUtils(driver);
        this.log = LogManager.getLogger(this.getClass());

        PageFactory.initElements(driver, this);

        log.debug("Initialized page: {}", this.getClass().getSimpleName());
    }
}