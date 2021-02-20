package com.epam;

import com.epam.base.TestBase;
import com.epam.dataProviders.DataProviders;
import com.epam.driver.ConfProperties;
import com.epam.pages.LoginPage;
import com.epam.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase {

    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;
    public static ChromeOptions chromeOptions;

    @BeforeClass
    public static void beforeClass() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
//        chromeOptions.addArguments("disable-infobars"); // disabling infobars
//        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
//        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
    }
    @BeforeMethod
    public static void beforeMethod() {
        driver = new ChromeDriver(chromeOptions);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @AfterMethod
    public static void afterMethod() {
        driver.close();
    }

    @Test(dataProvider = "invalidUsername", dataProviderClass = DataProviders.class)
    public void dataProviderLoginTest(String username) {
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //вводим логин
        loginPage.typeLogin(username);
        //нажимаем кнопку входа
        loginPage.clickSubmitBtn();
        //проверяем сообщение об ошибке
        loginPage.checkWrongPasswordText(ConfProperties.getProperty("invalidUsernameMsg"));
    }

    @Test
    public void loginTest() {
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //вводим логин
        loginPage.typeLogin(ConfProperties.getProperty("username"));
        //нажимаем кнопку входа
        loginPage.clickSubmitBtn();
        //вводим пароль
        loginPage.typePasswd(ConfProperties.getProperty("passwd"));
        //нажимаем кнопку входа
        loginPage.clickSubmitBtn();
        //получаем отображаемый логин
        String user = profilePage.getUsername();
        //и сравниваем его с логином из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("username"), user);
    }

    @Test
    public void loginTest2() {
        loginPage.loginAsTheValidUser();
        //получаем отображаемый логин
        String user = profilePage.getUsername();
        //и сравниваем его с логином из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("login"), user);
    }

    @Test
    public void loginWithInvalidPasswd() {
        loginPage.loginAsUserWithTheInvalidPasswd();
        //проверяем сообщение об ошибке
        loginPage.checkWrongPasswordText(ConfProperties.getProperty("wrongPasswordMsg"));
    }

    @Test
    public void loginWithInvalidLogin() {
        loginPage.loginAsUserWithTheInvalidLogin();
        //проверяем сообщение об ошибке
        loginPage.checkWrongPasswordText(ConfProperties.getProperty("invalidUsernameMsg"));
    }
}
