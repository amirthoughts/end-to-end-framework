package pages.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import logger.Logs;
import pages.base.BasePage;
import pages.home.HomePage;

public class LoginPage{
    WebDriver driver;

    By email = By.id("username");
    By password = By.id("password");
    By login = By.xpath("//span[text()='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmail() {
        return driver.findElement(email);
    }

    public WebElement getPassword() {
        return driver.findElement(password);
    }

    public WebElement getLogin() {
        return driver.findElement(login);
    }

    @Step("Login to Dashboard")
    public HomePage login(String email){
        Logs.logReporter("----------- Logging into dashboard ------------");
        getEmail().sendKeys(email);
        getPassword().sendKeys(BasePage.decodeStr("RFMxQENhZDNudCE="));
        getLogin().click();
        Logs.logReporter("----------- Successfully logged into dashboard ------------");
        return new HomePage(driver);
    }

}
