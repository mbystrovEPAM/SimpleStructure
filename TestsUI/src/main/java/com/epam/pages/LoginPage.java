package com.epam.pages;

import com.epam.driver.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class LoginPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    @FindBy (xpath = "//input[contains(@class,\"Textinput\")]")
    private WebElement loginField;
    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")
    private WebElement passwdField;
    @FindBy (xpath = "//*[@type=\"submit\"]")
    private WebElement submitBtn;
    @FindBy (xpath = "//*[contains(@class,'Textinput-Hint')]")
    private WebElement hintStateErrorMessage;

    public void typeLogin(String login) {
        loginField.sendKeys(login);
    }

    public void typePasswd(String passwd) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'passp-field-passwd')]")));
        loginField.sendKeys(passwd);
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }

    private void inputUsername(String username) {
        //вводим логин
        this.typeLogin(username);
        //нажимаем кнопку входа
        this.clickSubmitBtn();
    }

    private void inputPasswd(String passwd) {
        //вводим пароль
        this.typePasswd(passwd);
        //нажимаем кнопку входа
        this.clickSubmitBtn();
    }

    private void login(String username, String password) {
        //вводим логин
        this.inputUsername(username);
        //вводим пароль
        this.typePasswd(password);
        //нажимаем кнопку входа
        this.clickSubmitBtn();
    }

    public void loginAsTheValidUser() {
        inputUsername(ConfProperties.getProperty("username"));
        inputPasswd(ConfProperties.getProperty("passwd"));
    }

    public void loginAsUserWithTheInvalidLogin() {
        inputUsername(ConfProperties.getProperty("invalidUsername"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(hintStateErrorMessage));
    }

    public void loginAsUserWithTheInvalidPasswd() {
        inputUsername(ConfProperties.getProperty("username"));
        inputPasswd(ConfProperties.getProperty("invalidPasswd"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(hintStateErrorMessage));
    }

    public void checkWrongPasswordText(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(hintStateErrorMessage));
        assertEquals(hintStateErrorMessage.getText(), expectedText);
    }
}
