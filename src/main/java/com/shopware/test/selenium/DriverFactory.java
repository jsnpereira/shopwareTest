package com.shopware.test.selenium;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.shopware.test.utils.CommonUtils;
import com.shopware.test.utils.QALogger;

public class DriverFactory {
	private static String PATH_HUB_URL = "/wd/hub";
	private static String HTTP = "http://";
	
	private static DesiredCapabilities dc = null;
	
	
	public static WebDriver setUpBrowser(String browser) throws Exception {
		Boolean remote = Boolean.valueOf(CommonUtils.getValueProperties("selenium.remote"));
		System.out.println("setUpBrowser");
		if (browser.equals("firefox")) {
			System.out.println("Browser firefox => remote: "+remote);
			if (remote) {
				return firefoxSetup();
			} else {
				return firefoLocSetup();
			}
		} else if (browser.equals("chrome")) {
			System.out.println("Browser Chrome => remote: "+remote);
			if (remote) {
				System.out.println("ChromeSetup ======");
				return chromeSetup();
			} else {
				System.out.println("ChromeLocSetup ======");
				return chromeLocSetup();
			}
		} 
		return null;
	}
	
	private static WebDriver firefoxSetup() throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("-fullscreen");
		dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
		return new RemoteWebDriver(new URL(setUpURL()), dc);
	}
	
	private static WebDriver chromeSetup() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		dc = DesiredCapabilities.chrome();
		dc.setCapability(ChromeOptions.CAPABILITY, options);
		return new RemoteWebDriver(new URL(setUpURL()), dc);
	}
	
	private static WebDriver firefoLocSetup() throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		return new FirefoxDriver();
	}
	
	private static WebDriver chromeLocSetup() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		return new ChromeDriver(options);
	}
	
	private static String setUpURL() {
		String grid_url = CommonUtils.getValueProperties("grid.url");
		String grid_port = CommonUtils.getValueProperties("grid.port");
		return HTTP + grid_url +":"+grid_port+ PATH_HUB_URL;
	}

}
