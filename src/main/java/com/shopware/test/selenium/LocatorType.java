package com.shopware.test.selenium;

public enum LocatorType {
	XPATH("XPATH"), 
	ID("ID"),
	CSS_SELECTOR("CSS_SELECTOR"), 
	NAME("NAME"), 
	LINK_TEXT("LINK_TEXT"), 
	TAG_NAME("TAG_NAME"), 
	CLASS("CLASS");

	String name;

	private LocatorType(String name) {
		this.name = name;
	}
}
