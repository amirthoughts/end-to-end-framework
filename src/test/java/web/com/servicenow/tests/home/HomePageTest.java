package web.com.servicenow.tests.home;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import web.constants.Constants;
import web.pages.base.BasePage;
import web.pages.home.HomePage;

public class HomePageTest extends BasePage {

    public WebDriver driver;

    @BeforeMethod
    public void startBrowser(){
        driver = setup();
    }

    @Test
    @DisplayName("Test HomePage")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Sk Amir Ullah")
    @Link(name = "Website", url = "https://skamirullah.com/")
    @Description("Description: Verify Homepage Landing")
    @Epic("Web interface")
    @Feature("Homepage features")
    @Story("Story Name: [STA-001] - Verify Homepage Navigation")
    @Issue("HOME-123")
    @TmsLink("TMS-456")
    public void verifyHomePage(){
        SoftAssert assertion = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        assertion.assertTrue(driver.getCurrentUrl().contains("index"), Constants.URL_MISMATCHED);
        assertion.assertTrue(homePage.isLeftNavOptionsHighlighted("Home"), Constants.ELEMENT_NOT_HIGHLIGHTED);
        assertion.assertTrue(homePage.getPageTitle().getText().contains("Welcome People"), Constants.PAGE_TITLE_MISMATCHED);
        assertion.assertAll();
    }

    @Test
    @DisplayName("Test Authentication")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Sk Amir Ullah")
    @Link(name = "Website", url = "https://skamirullah.com/")
    @Description("Description: Verify Homepage Landing")
    @Epic("Web interface")
    @Feature("Homepage features")
    @Story("Story Name: [STA-001] - Verify Homepage Navigation")
    @Issue("HOME-123")
    @TmsLink("TMS-456")
    public void verifyFormSubmit(){
        SoftAssert assertion = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        assertion.assertTrue(driver.getCurrentUrl().contains("index"), Constants.URL_MISMATCHED);
        assertion.assertTrue(homePage.isLeftNavOptionsHighlighted("Home"), Constants.ELEMENT_NOT_HIGHLIGHTED);
        assertion.assertTrue(homePage.getPageTitle().getText().contains("Welcome People"), Constants.PAGE_TITLE_MISMATCHED);
        assertion.assertAll();
    }

    @AfterMethod
    public void closeBrowser(){
        tearDown();
    }
}
