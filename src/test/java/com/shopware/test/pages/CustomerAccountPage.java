package com.shopware.test.pages;

import org.openqa.selenium.WebElement;

import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.ActionValidate;
import com.shopware.test.pages.base.CustomerAccountPageConstants;
import com.shopware.test.selenium.LocatorType;
import com.shopware.test.selenium.SeleniumPage;

public class CustomerAccountPage extends SeleniumPage implements CustomerAccountPageConstants {
	private static String PROFILE_DASHBOARD_XPATH = ".//div[contains(@class,'account--info')]/div[1]/p";
	private static String CHANGE_PROFILE_BUTTON_XPATH = ".//div[contains(@class,'account--info')]/div[2]/a";
	private static String MY_ACCOUNT_BUTTON_XPATH ="//span[contains(@class,'navigation--personalized')]";
	private static String MY_ACCOUNT_DROPDOWN_NAVIGATION_XPATH = "//div[contains(@class,'account--dropdown-navigation')]";
	private static String LOGOUT_LINK_ON_NAVIGATION_XPATH =".//div[@class='account--dropdown-navigation']//a[contains(@class,'link--logout')]";
	
	public String getTitlePage() {
		return getBrowserTitle();
	}
	
	public String checkProfileDashboard(ActionValidate actionValidate) {
		super.waitElementIslocated(PROFILE_DASHBOARD_XPATH, LocatorType.XPATH);
		WebElement eProfileDashboard = super.getElement(PROFILE_DASHBOARD_XPATH, LocatorType.XPATH);
		String[] profileDashboard = eProfileDashboard.getText().split("\n");
		switch (actionValidate) {
		case NAMES_PROFILE_DASHBOARD:
			return profileDashboard[0];
		case EMAIL_PROFILE_DASHBOARD:
			return profileDashboard[1];
		default:
			return "";
		}
	}
	
	public void action(ActionCustomer actionCustomer) {
		switch (actionCustomer) {
		case CHANGE_PROFILE:
			clickChangeProfile();
			break;
		case LOGOUT_USER_BY_NAVIGATION:
			clickLogoutTheUserNavigation();
			break;
		default:
			break;
		}
		
	}
	
	private void clickChangeProfile() {
		super.click(CHANGE_PROFILE_BUTTON_XPATH, LocatorType.XPATH);
	}
	
	private void clickLogoutTheUserNavigation() {
		super.click(MY_ACCOUNT_BUTTON_XPATH, LocatorType.XPATH);
		super.waitElementIsVisible(MY_ACCOUNT_DROPDOWN_NAVIGATION_XPATH, LocatorType.XPATH);
		super.click(LOGOUT_LINK_ON_NAVIGATION_XPATH, LocatorType.XPATH);
	}

}
