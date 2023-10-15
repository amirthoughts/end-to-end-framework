package pages.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

//import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.utils.Base64;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.paulhammant.ngwebdriver.NgWebDriver;

import constants.Browser;
import constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	public Properties prop;
	public NgWebDriver ngWebDriver;
	public JavascriptExecutor javascriptExecutor;
	public Wait<WebDriver> wait;
	public FileInputStream fileInputStream;

	public static WebDriver getDriver() {
		return driver;
	}

	public WebDriver setup() {
		prop = new Properties();
		try {
			fileInputStream = new FileInputStream(System.getProperty(Constants.USER_DIR) + Constants.PROPERTIES_PATH);
		} catch (FileNotFoundException fileNotFoundException) {

			fileNotFoundException.printStackTrace();
		}
		try {
			prop.load(fileInputStream);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		String browserName = prop.getProperty(Constants.BROWSER);
		String isHeadless = prop.getProperty(Constants.HEADLESS);
		try {
			if (browserName.equals(Browser.chrome.name())) {
				WebDriverManager.chromedriver().setup();
				if (isHeadless.equalsIgnoreCase("true")) {
					ChromeOptions options = new ChromeOptions();
					options.addArguments(Constants.HEADLESS);
					driver = new ChromeDriver(options);
					System.out.println("Chrome is running in headless mode. Please wait until the exection is over.");
				} else if (isHeadless.equalsIgnoreCase("false")) {
					driver = new ChromeDriver();
				} else {
					System.out.println("Please provide a valid mode of chrome execution.");
				}
				javascriptExecutor = (JavascriptExecutor) driver;
				ngWebDriver = new NgWebDriver(javascriptExecutor);
			} else if (browserName.equals(Browser.firefox.name())) {
				WebDriverManager.firefoxdriver().setup();
				if (isHeadless.equalsIgnoreCase("true")) {
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments(Constants.HEADLESS);
					driver = new FirefoxDriver(options);
					System.out.println("Firefox is running in headless mode. Please wait until the exection is over.");
				} else if (isHeadless.equalsIgnoreCase("false")) {
					driver = new FirefoxDriver();
				} else {
					System.out.println("Please provide a valid mode of firefox execution.");
				}
				driver = new FirefoxDriver();
				javascriptExecutor = (JavascriptExecutor) driver;
				ngWebDriver = new NgWebDriver(javascriptExecutor);
			} else if (browserName.equals(Browser.safari.name())) {
				WebDriverManager.safaridriver().setup();
				if (isHeadless.equalsIgnoreCase("true")) {
					System.out.println(
							"Safari doesn't support headless execution mode. Please try headed or headless in other browsers.");
				} else if (isHeadless.equalsIgnoreCase("false")) {
					driver = new SafariDriver();
					System.out.println("Browser launched successfully....");
				} else {
					System.out.println("Please provide a valid mode of safari execution.");
				}
				javascriptExecutor = (JavascriptExecutor) driver;
				ngWebDriver = new NgWebDriver(javascriptExecutor);
			} else {
				WebDriverManager.iedriver().setup();
				if (isHeadless.equalsIgnoreCase("true")) {
					System.out.println(
							"Internet Explorer doesn't support headless execution mode. Please try headed or headless in other browsers.");
				} else if (isHeadless.equalsIgnoreCase("false")) {
					driver = new InternetExplorerDriver();
				} else {
					System.out.println("Please provide a valid mode of IE execution.");
				}
				javascriptExecutor = (JavascriptExecutor) driver;
				ngWebDriver = new NgWebDriver(javascriptExecutor);
			}
			driver.manage().window().maximize();
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty(Constants.IMPLICIT_WAIT))));
			driver.get(prop.getProperty(Constants.APPLICATION_URL));
			ngWebDriver.waitForAngularRequestsToFinish();
		} catch (Exception e) {
			System.out.print("Exception - Unable to launch browser successfully : ");
			e.printStackTrace();
		}
		return getDriver();
	}

	public boolean isElementDisplayed(List<WebElement> elementList, String type) {
		boolean result = false;
		try {
			if (type.equals("isDisplayed")) {
				if (elementList.size() > 0) {
					result = true;
				}
			} else if (type.equals("isNotDisplayed")) {
				if (elementList.size() > 0) {
					result = false;
				} else {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public void waitForPresenceOfElement(WebElement element) {
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10L))
				.pollingEvery(Duration.ofSeconds(2L)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static String decodeStr(String encodedStr) {
		byte[] decoded = Base64.decodeBase64(encodedStr);
		return new String(decoded);
	}

	public void tearDown() {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			System.out.print("Unable to Quit browser successfully : ");
			e.printStackTrace();
		}

	}

}
