package web.com.servicenow.tests.login;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import web.constants.Constants;
import web.pages.base.BasePage;
import web.pages.loginPage.LoginPage;

public class LoginTest extends BasePage {

    public WebDriver driver;

    @BeforeMethod
    public void startBrowser(){
        driver = setup();
    }

    @Test
    @DisplayName("Scenario - Valid Login")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Sk Amir Ullah")
    @Link(name = "Website", url = "https://dev112733.service-now.com/")
    @Description("Description: Verify Valid Login")
    @Epic("Web interface")
    @Feature("Login features")
    @Story("Story Name: [STA-001] - Verify Valid Login")
    @Issue("LOGIN-123")
    @TmsLink("TMS-456")
    public void verifyValidLogin(){
        SoftAssert assertion = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty(Constants.USERNAME), "valid", assertion);
        assertion.assertAll();
    }

    @Test
    @DisplayName("Scenario - Invalid Login")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Sk Amir Ullah")
    @Link(name = "Website", url = "https://dev112733.service-now.com/")
    @Description("Description: Verify Invalid Login")
    @Epic("Web interface")
    @Feature("Login features")
    @Story("Story Name: [STA-001] - Verify Invalid Login")
    @Issue("LOGIN-123")
    @TmsLink("TMS-456")
    public void verifyInvalidLogin(){
        SoftAssert assertion = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty(Constants.USERNAME), "blank_details", assertion);
        loginPage.login(prop.getProperty(Constants.USERNAME), "only_with_username", assertion);
        loginPage.login(prop.getProperty(Constants.USERNAME), "only_with_password", assertion);
        assertion.assertAll();
    }

    @AfterMethod
    public void closeBrowser(){
        tearDown();
    }
}
