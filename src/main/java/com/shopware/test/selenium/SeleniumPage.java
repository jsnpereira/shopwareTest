package com.shopware.test.selenium;

import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shopware.test.utils.QALogger;

public class SeleniumPage {
	private static WebDriver driver;
	private WebElement element;
//	private static String DRIVERS_FOLDER = "/src/main/resources/DRIVERS";

	public void browser(String browser) throws Exception {
		QALogger.info("Browser: " + browser);
		driver = setUpBrowser(browser);
	}

	public void navigationTo(String URL) {
		QALogger.info("URL: " + URL);
		driver.get(URL);
	}
	
	public void redirectTo(String URL) {
		QALogger.info("Redirect to: "+URL);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.urlContains(URL));
		waitPageLoaded();
	}
	
	public static void close() {
		QALogger.info("Browser closed");
		driver.close();
	}
	
	public WebElement getElement(String locator, LocatorType locatorType) {
		waitElementIslocated(locator, locatorType);
		return driver.findElement(Locator.get(locator, locatorType));
	}
	
	public void Type(String value, String locator, LocatorType locatorType) {
		Type(value, locator, locatorType, true);
	}

	public void Type(String value, String locator, LocatorType locatorType, Boolean clear) {
		QALogger.info("Type: " + value);
		QALogger.info("Locator: " + locator);
		element = getElement(locator, locatorType);
		
		if (clear) {
			element.clear();
		}
		
		element.sendKeys(value);
	}
	
	public boolean verifyTextOnValue(String value, String locator, LocatorType locatorType) {
		QALogger.info("Contains: " + value);
		QALogger.info("Locator: " + locator);
		element = getElement(locator, locatorType);
		QALogger.info("Verify text: "+value+" => "+element.getText());
		if (element.getAttribute("value").equals(value)) {
			return true;
		}
		return false;
	}

	public boolean verifyText(String value, String locator, LocatorType locatorType) {
		QALogger.info("Contains: " + value);
		QALogger.info("Locator: " + locator);
		element = getElement(locator, locatorType);
		QALogger.info("Verify text: "+value+" => "+element.getText());
		if (element.getText().equals(value)) {
			return true;
		}
		return false;
	}

	public boolean findText(String value) {
		QALogger.info("Find text value: " + value);
		return driver.getPageSource().contains(value);
	}

	public void Click(String locator, LocatorType locatorType) {
		QALogger.info("Click locator: " + locator);
		element = getElement(locator, locatorType);
		element.click();
	}

	public void selectValueDropDown(String value, String locator, LocatorType locatorType) {
		Select dropDown = new Select(element = getElement(locator, locatorType));
		dropDown.selectByVisibleText(value);
	}
	
	public String getBrowserTitle() {
		return driver.getTitle();
	}

	public Boolean isBrowserOpen() {
		try {
			driver.getCurrentUrl();// or driver.getTitle();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void waitPageLoaded() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
	
	public void waitElementIslocated(String locator, LocatorType locatorType) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(Locator.get(locator, locatorType)));
	}
	
	public void waitElementIsVisible(String locator, LocatorType locatorType) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator.get(locator, locatorType)));
	}
 
//	public WebDriver setUpBrowser(Browser browser) {
//		String address;
//		
//		if (isWindows()) {
//			address = System.getProperty("user.dir") + DRIVERS_FOLDER;
//		} else {
//			address = "/usr/bin";
//		}
//		
//		QALogger.info("Address: " + address);
//		switch (browser) {
//		case Firefox:
//			System.setProperty("webdriver.gecko.driver", address + "/geckodriver.exe");
//			return new FirefoxDriver();
//		case Chrome:
//			System.setProperty("webdriver.chrome.driver", address + "/chromedriver");
//			return new ChromeDriver();
//		default:
//			return null;
//		}
//	}
	
	public WebDriver setUpBrowser(String browser) throws Exception {
		DesiredCapabilities dc = null;
		
//		String address;
//		
//		if (isWindows()) {
//			address = System.getProperty("user.dir") + DRIVERS_FOLDER;
//		} else {
//			address = "/usr/bin";
//		}
		
		if (browser.equals("firefox")) {
			dc = DesiredCapabilities.firefox();
			return new RemoteWebDriver(new URL("http://172.17.0.4:5555/wd/hub"), dc);
		} else if (browser.equals("chrome")) {
			dc = DesiredCapabilities.chrome();
			return new RemoteWebDriver(new URL("http://172.17.0.3:5555/wd/hub"), dc);
		} else if (browser.equals("firefoxLoc")) {
			System.setProperty("webdriver.gecko.driver", "geckodriver");
			return new FirefoxDriver();
		} else if (browser.equals("chromeLoc")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			return new ChromeDriver(options);
		}
		
		return null;
	}

	public Boolean isWindows() {
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("win") >= 0);
	}


}
