package com.shopware.test.selenium;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static final ThreadLocal<WebDriver> driverMananger = new ThreadLocal<WebDriver>();
	
	public static void setDriver(WebDriver driver) {
		driverMananger.set(driver);
	}
	
	public static WebDriver getDriver() {
		return driverMananger.get();
	}
	
	
	public static void quit() {
		driverMananger.get().quit();
	}
	

}
