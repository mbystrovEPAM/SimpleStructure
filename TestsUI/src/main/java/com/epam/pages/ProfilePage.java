package com.epam.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class ProfilePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public ProfilePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    @FindBy(xpath = "//*[contains(@class, 'account_has-accent-letter')]/*[@class='user-account__name']")
    private WebElement userMenu;
    @FindBy(xpath = "//*[contains(@class, 'menu-item_action_exit')]")
    private WebElement logoutBtn;
    @FindBy(xpath = "//*[@data-t='personal-info']")
    private WebElement personalInfoForm;
    @FindBy(xpath = "//*[@class='AdditionalPersonalInfo-change']//a")
    private WebElement changePersonalInformationLink;
    @FindBy(xpath = "//*[@class='personal-info-birthday-day']//input")
    private WebElement personalInfoBirthdayDay;
    @FindBy(xpath = "//*[@data-t='personal-info']//*[@type='submit']")
    private WebElement personalInfoSubmitButton;
    @FindBy(xpath = "//*[@data-t='personal-info-birthday-error']/div")
    private WebElement personalInfoBirthdayErrorText;

    public String getUsername() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(userMenu));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'account_has-accent-letter')]/*[@class='user-account__name']")));
        String userName = userMenu.getText();
        return userName;
    }

    public void entryMenu() {
        userMenu.click();
    }

    public void userLogout() {
        logoutBtn.click();
    }

    public void openPersonalInfoForm() {
        changePersonalInformationLink.click();
    }

    public void inputDayOfBirth(String day) {
        personalInfoBirthdayDay.sendKeys(Keys.CONTROL + "a");
        personalInfoBirthdayDay.sendKeys(Keys.DELETE);
        personalInfoBirthdayDay.sendKeys(day);
    }

    public void saveChanges() {
        personalInfoSubmitButton.click();
    }

    public void checkPersonalInfoBirthdayErrorText(String expectedText) {
        assertEquals(personalInfoBirthdayErrorText.getText(), expectedText);
    }
}
