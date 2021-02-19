package com.epam;

import com.epam.driver.ConfProperties;
import com.epam.pages.LoginPage;
import com.epam.pages.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ProfilePageTest {

    private static LoginPage loginPage;
    private static ProfilePage profilePage;
    private static WebDriver driver;

    @BeforeMethod
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @AfterMethod
    public static void tearDown() {
        driver.close();
    }

    @Test
    public void personalInfoForm() {
        loginPage.loginAsTheValidUser();
        profilePage.openPersonalInfoForm();
        profilePage.inputDayOfBirth("50");
        profilePage.saveChanges();
        profilePage.checkPersonalInfoBirthdayErrorText("Пожалуйста, укажите правильную дату");
    }
}
