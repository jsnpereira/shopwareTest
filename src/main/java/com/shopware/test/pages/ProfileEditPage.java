package com.shopware.test.pages;

import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.pages.base.ProfileEditConstants;
import com.shopware.test.selenium.LocatorType;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class ProfileEditPage extends SeleniumPage implements ProfileEditConstants {
	private Boolean isPass;
	private String FIRST_NAME_FIELD_XPATH = ".//div[@class='profile--firstname']/input";
	private String LAST_NAME_FIELD_XPATH = ".//div[@class='profile--lastname']/input";
	private String PROFILE_SAVE_CHANGE_BUTTON_XPATH = ".//div[@class='profile--firstname']/../following-sibling::div/button";
	private String PROFILE_GREEN_ALERT_XPATH = ".//div[contains(@class,'is--success')]/div[2]";
	private String CUSTOMER_ACCOUNT_LINK_XPATH = "(.//a[@class='breadcrumb--link'])[1]";

	public Boolean checkPageIsDisplay() {
		QALogger.info("==============Start: " + Thread.currentThread().getStackTrace()[1].getMethodName()
				+ "====================");
		isPass = true;
		String titleName = getBrowserTitle();
		if (!titleName.equals(TITLE_NAME_PAGE)) {
			isPass = false;
		}
		QALogger.info("Tile name: '" + titleName + "' => '" + TITLE_NAME_PAGE + "', isPass: " + isPass);
		QALogger.info("==============END: " + Thread.currentThread().getStackTrace()[1].getMethodName()
				+ "======================");
		return isPass;
	}

	public void enterFieldsProfile(Customer_UI customerUI) {
		firstNameField(customerUI);
		lastNameField(customerUI);
	}

	private void firstNameField(Customer_UI customerUI) {
		super.Type(customerUI.getFirstName(), FIRST_NAME_FIELD_XPATH, LocatorType.XPATH);
	}

	private void lastNameField(Customer_UI customerUI) {	
		super.Type(customerUI.getLastName(), LAST_NAME_FIELD_XPATH, LocatorType.XPATH);
	}
	
	private Boolean greenAlert() {
		isPass = true;
		super.waitElementIsVisible(PROFILE_GREEN_ALERT_XPATH,LocatorType.XPATH);
		if (!super.verifyText(PROFILE_GREEN_ALERT_MESSAGE, PROFILE_GREEN_ALERT_XPATH, LocatorType.XPATH)) {
			isPass = false;
		}
		return isPass;
	}
	
	private Boolean redAlert() {
		return true;
	}
	
	public Boolean checkAlert(Boolean isGreen) {
		isPass = true;
		if (isGreen) {
			return greenAlert();
		} else {
			return redAlert();
		}
	}

	public void action(ActionCustomer actionCustomer) {
		switch (actionCustomer) {
		case SAVE_CHANGES_PROFILE:
			super.Click(PROFILE_SAVE_CHANGE_BUTTON_XPATH, LocatorType.XPATH);
			break;
		case CUSTOMER_ACCOUNT_LINK:
			super.Click(CUSTOMER_ACCOUNT_LINK_XPATH, LocatorType.XPATH);
			break;
		default:
			break;
		}
	}
}
