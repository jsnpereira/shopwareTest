package com.shopware.test.pages;

import org.openqa.selenium.WebElement;

import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.CustomerAccountPageConstants;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.selenium.LocatorType;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.DataUtils;
import com.shopware.test.utils.QALogger;

public class CustomerAccountPage extends SeleniumPage implements CustomerAccountPageConstants {
	private Boolean isPass;
	private static String PROFILE_DASHBOARD_XPATH = ".//div[contains(@class,'account--info')]/div[1]/p";
	private static String CHANGE_PROFILE_BUTTON_XPATH = ".//div[contains(@class,'account--info')]/div[2]/a";
	private static String MY_ACCOUNT_BUTTON_XPATH ="//span[contains(@class,'navigation--personalized')]";
	private static String MY_ACCOUNT_DROPDOWN_NAVIGATION_XPATH = "//div[contains(@class,'account--dropdown-navigation')]";
	private static String LOGOUT_LINK_ON_NAVIGATION_XPATH =".//div[@class='account--dropdown-navigation']//a[contains(@class,'link--logout')]";
	
	
	public Boolean checkCustomerAccount() {
		QALogger.info("==============Start: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"====================");
		isPass = true;
		String titleName = getBrowserTitle();
		if (!titleName.equals(TITLE_NAME_PAGE)) {
			isPass = false;
		}
		QALogger.info("Tile name: '" + titleName + "' => '" + TITLE_NAME_PAGE + "', isPass: " + isPass);
		QALogger.info("==============End: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"====================");
		return isPass;
	}
	
	public Boolean checkProfileDashboard(Customer_UI customer) {
		QALogger.info("==============Start: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"====================");
		isPass = true;
		
		String names = DataUtils.capitalize(customer.getSalutation())+" "+customer.getFirstName()+" "+customer.getLastName();
		super.waitElementIslocated(PROFILE_DASHBOARD_XPATH, LocatorType.XPATH);
		WebElement eProfileDashboard = super.getElement(PROFILE_DASHBOARD_XPATH, LocatorType.XPATH);
		String[] profileDashboard = eProfileDashboard.getText().split("\n");
		
		if (!profileDashboard[0].equals(names)) { isPass = false;}
		QALogger.info("Names: "+profileDashboard[0]+" => "+names+", isPass: "+isPass);
		
		if (!profileDashboard[1].equals(customer.getEmail())) { isPass = false;}
		QALogger.info("Email: "+profileDashboard[1]+" => "+customer.getEmail()+", isPass: "+isPass);
		QALogger.info("==============End: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"====================");
		return isPass;
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
