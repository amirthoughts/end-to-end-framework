package web.pages.loginPage;

import web.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import web.logger.Logs;
import org.testng.asserts.SoftAssert;
import web.pages.base.BasePage;
import web.pages.home.HomePage;

public class LoginPage{
    WebDriver driver;

    By username = By.id("user_name");
    By password = By.id("user_password");
    By login = By.id("sysverb_login");
    By usernameLabel = By.xpath("//label[@for='user_name']") ;
    By passwordLabel = By.xpath("//label[@for='user_password']") ;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmail() {
        return driver.findElement(username);
    }

    public WebElement getPassword() {
        return driver.findElement(password);
    }

    public WebElement getLogin() {
        return driver.findElement(login);
    }

    public WebElement getUsernameLabel() {
        return driver.findElement(usernameLabel);
    }

    public WebElement getPasswordLabel() {
        return driver.findElement(passwordLabel);
    }

    @Step("Login to Dashboard")
    public HomePage login(String email, SoftAssert assertion){
        assertion = new SoftAssert();
        assertion.assertTrue(driver.getTitle().equals("Log in | ServiceNow"), Constants.TITLE_MISMATCHED);
        assertion.assertEquals(this.getUsernameLabel().getText().equals("User name"), Constants.TEXT_MISMATCHED);
        Logs.logReporter("----------- Entering valid username for login ------------");
        getEmail().sendKeys(email);
        assertion.assertEquals(this.getPasswordLabel().getText().equals("Password"), Constants.TEXT_MISMATCHED);
        getPassword().sendKeys(BasePage.decodeStr("UGFzc3dvcmRAMDc4Ng=="));
        assertion.assertTrue(this.getLogin().isDisplayed(), Constants.ELEMENT_NOT_DISPLAYED);
        getLogin().click();
        Logs.logReporter("----------- Successfully verified valid login ------------");
        return new HomePage(driver);
    }

}