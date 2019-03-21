package com.shopware.test.selenium;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static void setDriver(WebDriver driver) {
		DriverManager.driver.set(driver);
	}
	
	public static WebDriver getDriver() {
		return DriverManager.getDriver();
	}
	
	
	public static void quit() {
		DriverManager.getDriver().quit();
	}
	

}
