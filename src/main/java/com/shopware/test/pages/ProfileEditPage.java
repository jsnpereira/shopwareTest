package com.shopware.test.pages;

import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.ActionValidate;
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
	private String CURRENT_PASSWORD_INPUT_XPATH = ".//form[@name='passwordForm']//div[@class='profile--current-password']/input";
	private String NEW_PASSWORD_INPUT_XPATH = ".//form[@name='passwordForm']//div[@class='profile--password']/input";
	private String CONFIRM_PASSWORD_INPUT_XPATH = ".//form[@name='passwordForm']//div[@class='profile--password-confirmation']/input";
	private String PASSWORD_GREEN_ALERT_XPATH =".//form[@name='passwordForm']//div[@class='alert--content']";
	private String PASSWORD_SAVE_CHANGE_BUTTON_XPATH = ".//form[@name='passwordForm']//div[contains(@class,'panel--actions')]/button";

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

	public void enterChangePasswordFields(Customer_UI customerUI, String newPassword) {
		enterNewPasswordField(newPassword);
		enterConfirmPasswordField(newPassword);
		enterCurrentPasswordField(customerUI);
	}

	private void enterCurrentPasswordField(Customer_UI customerUI) {
		Type(customerUI.getPassword(), CURRENT_PASSWORD_INPUT_XPATH	, LocatorType.XPATH);
	}

	private void enterNewPasswordField(String newPassword) {
		Type(newPassword, NEW_PASSWORD_INPUT_XPATH, LocatorType.XPATH);
	}

	private void enterConfirmPasswordField(String confirmPassword) {
		Type(confirmPassword, CONFIRM_PASSWORD_INPUT_XPATH, LocatorType.XPATH);
	}

	private Boolean namesGreenAlert() {
		isPass = true;
		super.waitElementIsVisible(PROFILE_GREEN_ALERT_XPATH, LocatorType.XPATH);
		if (!super.verifyText(PROFILE_GREEN_ALERT_MESSAGE, PROFILE_GREEN_ALERT_XPATH, LocatorType.XPATH)) {
			isPass = false;
		}
		return isPass;
	}

	private Boolean namesRedAlert() {
		return true;
	}

	
	private Boolean passwordGreenAlert() {
		isPass = true;
		super.waitElementIsVisible(PASSWORD_GREEN_ALERT_XPATH, LocatorType.XPATH);
		if (!super.verifyText(PASSWORD_GREEN_ALERT_MESSAGE, PASSWORD_GREEN_ALERT_XPATH, LocatorType.XPATH)) {
			isPass = false;
		}
		return isPass;
	}

	private Boolean passwordRedAlert() {
		return true;
	}

	
	public Boolean checkAlert(ActionValidate actionValidate,Boolean isGreen) {
		switch (actionValidate) {
		case SAVE_NAMES_ALERT:
			return verifyNamesAlert(isGreen);
		case SAVE_PASSWORD_ALERT:
			return verifyPasswordAlert(isGreen);
		default:
			break;
		}
		return false;
	}
	
	private Boolean verifyNamesAlert(Boolean isGreen) {
		if (isGreen) {
			return namesGreenAlert();
		} else {
			return namesRedAlert();
		}
	}

	private Boolean verifyPasswordAlert(Boolean isGreen) {
		if (isGreen) {
			return passwordGreenAlert();
		} else {
			return passwordRedAlert();
		}
	}
	

	public void action(ActionCustomer actionCustomer) {
		switch (actionCustomer) {
		case SAVE_CHANGES_PROFILE:
			super.click(PROFILE_SAVE_CHANGE_BUTTON_XPATH, LocatorType.XPATH);
			break;
		case CUSTOMER_ACCOUNT_LINK:
			super.click(CUSTOMER_ACCOUNT_LINK_XPATH, LocatorType.XPATH);
			break;
		case SAVE_CHANGES_PASSWORD:
			super.click(PASSWORD_SAVE_CHANGE_BUTTON_XPATH, LocatorType.XPATH);
			break;
		default:
			break;
		}
	}
}
