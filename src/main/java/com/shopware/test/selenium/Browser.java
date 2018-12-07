package com.shopware.test.selenium;

public enum Browser {
	Firefox("firefox"), Chrome("chrome"), FirefoxLoc("firefoxLoc"), ChromeLoc("chromeLoc");

	String browserName;

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	private Browser(String browserName) {
		this.browserName = browserName;
	}

}
