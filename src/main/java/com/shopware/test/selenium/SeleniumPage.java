package com.shopware.test.selenium;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shopware.test.utils.QALogger;

public class SeleniumPage {
	private static WebDriver driver;
	private WebElement element;

	public static void browser(String browser) throws Exception {
		QALogger.info("Browser: " + browser);
		driver = DriverFactory.setUpBrowser(browser);
	}

	public static void navigationTo(String URL) {
		QALogger.info("URL: " + URL);
		driver.get(URL);
		waitPageIsLoaded();
	}
	
	
	public void waitPageIsRedirecting(final String actualURL) {
		QALogger.info("Redirect to: "+actualURL);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(500));
			    
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver t) {
				String url = t.getCurrentUrl();
				if (url.contains(actualURL)) {
					return false;
				}
				return true;
			}
		});
		waitPageIsLoaded();
	}
	
	public void redirectTo(String URL) {
		QALogger.info("Redirect to: "+URL);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.urlContains(URL));
		waitPageIsLoaded();
	}
	
	public static void close() {
		QALogger.info("Browser closed");
		driver.quit();
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

	public void click(String locator, LocatorType locatorType) {
		QALogger.info("click locator: " + locator);
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

	public static void waitPageIsLoaded() {
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

	public Boolean isWindows() {
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("win") >= 0);
	}


}
