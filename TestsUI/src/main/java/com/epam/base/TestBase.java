package com.epam.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static java.lang.System.currentTimeMillis;

public abstract class TestBase {

    private WebDriver driver;
    private long time;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        time = currentTimeMillis();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (driver != null) {
            driver.close();
        }
        System.out.println("Tests duration: " + (currentTimeMillis() - time));
    }

}
