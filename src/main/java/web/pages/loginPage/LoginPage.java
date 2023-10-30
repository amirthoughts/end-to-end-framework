package web.pages.loginPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import web.constants.Constants;
import web.logger.Logs;
import web.pages.base.BasePage;

public class LoginPage extends BasePage{
    WebDriver driver;

    By username = By.id("user_name");
    By password = By.id("user_password");
    By login = By.id("sysverb_login");
    By usernameLabel = By.xpath("//label[@for='user_name']") ;
    By passwordLabel = By.xpath("//label[@for='user_password']") ;
    By invalidUsername = By.xpath("//div[@class='outputmsg_text']");
    By errorMessage = By.xpath("//div[@class='dp-invalid-login']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get Email")
    public WebElement getEmail() {
        return driver.findElement(username);
    }

    @Step("Get Password")
    public WebElement getPassword() {
        return driver.findElement(password);
    }

    @Step
    public WebElement getLogin() {
        return driver.findElement(login);
    }

    @Step
    public WebElement getUsernameLabel() {
        return driver.findElement(usernameLabel);
    }

    @Step
    public WebElement getPasswordLabel() {
        return driver.findElement(passwordLabel);
    }

    @Step
    public WebElement getErrorMessage(){
        return driver.findElement(errorMessage);
    }

    @Step
    public WebElement getInvalidUsername(){
        return driver.findElement(invalidUsername);
    }

    @Step("Login to the application")
    public void login(String email, String type, SoftAssert assertion){
        if(type.equalsIgnoreCase("blank_details")){
            System.out.println("Inside blank_details");
            assertion.assertTrue(driver.getTitle().equals("Log in | ServiceNow"), Constants.TITLE_MISMATCHED);
            getLogin().click();
            assertion.assertTrue(this.getInvalidUsername().isDisplayed(), Constants.ELEMENT_NOT_DISPLAYED);
            assertion.assertEquals(this.getInvalidUsername().getText(),"Invalid input in user name!", Constants.TEXT_MISMATCHED);
        } else if(type.equalsIgnoreCase("only_with_username")){
            System.out.println("Inside only_with_username");
            getEmail().sendKeys(email);
            getLogin().click();
            assertion.assertTrue(this.getErrorMessage().isDisplayed(), Constants.ELEMENT_NOT_DISPLAYED);
            assertion.assertEquals(this.getErrorMessage().getText(),"User name or password invalid. To reset your admin password click here. Please note this is different than the credentials used to sign in to the Developer Site.", Constants.TEXT_MISMATCHED);
        } else if(type.equalsIgnoreCase("only_with_password")){
            System.out.println("Inside only_with_password");
            getPassword().sendKeys(BasePage.decodeStr("UGFzc3dvcmRAMDc4Ng=="));
            getLogin().click();
            assertion.assertTrue(this.getInvalidUsername().isDisplayed(), Constants.ELEMENT_NOT_DISPLAYED);
            assertion.assertEquals(this.getInvalidUsername().getText(),"Invalid input in user name!", Constants.TEXT_MISMATCHED);
        } else {
            System.out.println("Inside valid");
            assertion.assertTrue(driver.getTitle().equals("Log in | ServiceNow"), Constants.TITLE_MISMATCHED);
            assertion.assertTrue(this.getUsernameLabel().getText().equals("User name"), Constants.TEXT_MISMATCHED);
            Logs.logReporter("----------- Entering valid username for login ------------");
            getEmail().sendKeys(email);
            assertion.assertTrue(this.getPasswordLabel().getText().equals("Password"), Constants.TEXT_MISMATCHED);
            getPassword().sendKeys(BasePage.decodeStr("UGFzc3dvcmRAMDc4Ng=="));
            assertion.assertTrue(this.getLogin().isDisplayed(), Constants.ELEMENT_NOT_DISPLAYED);
            getLogin().click();
            assertion.assertTrue(driver.getTitle().equals("ServiceNow"), Constants.TITLE_MISMATCHED);
            Logs.logReporter("----------- Successfully verified valid login ------------");
        }
    }

}
