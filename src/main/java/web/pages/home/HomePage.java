package web.pages.home;

import io.qameta.allure.Step;
import web.pages.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web.utils.DynamicXpathUtils;


public class HomePage extends BasePage {

	WebDriver driver;
	Actions actions;

	By pageTitle = By.xpath("//h1[contains(text(),'Welcome People')]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getLeftNavOption(String xpathName, String value) {
		String xpath = DynamicXpathUtils.getXpath(xpathName, value);
		return driver.findElement(By.xpath(xpath));
	}

	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}

	public boolean isLeftNavOptionsHighlighted(String option){
		String leftNavOption = "//a[contains(@class,'nav-link')]//span[text()='%s']";
		return !getLeftNavOption(leftNavOption, option).getAttribute("class").contains("collapse");
	}


	@Step("Fill the form")
	public void fillForm() {
		//waitForPresenceOfElement(getDailyDashboard());
//		WebElement template = driver.findElement(
//				By.xpath("//a[normalize-space()='Daily Dashboard']//following::a[text()='" + templateName + "']"));
//		wait.until(ExpectedConditions.visibilityOf(template));
//		template.click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(dashboardTitle));
//		actions = new Actions(driver);
//		actions.moveToElement(driver.findElement(dailyDashboard)).build().perform();

	}

}
