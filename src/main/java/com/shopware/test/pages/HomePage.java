package com.shopware.test.pages;

import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.ActionValidate;
import com.shopware.test.pages.base.HomePageConstansts;
import com.shopware.test.selenium.LocatorType;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class HomePage extends SeleniumPage {
	private Boolean isPass;

	// Element
	private String SEARCH_FIELD_NAME = "sSearch";
	private String SUBMIT_SEARCH_ICON_CLASS = "main-search--button";
	private String ACCOUNT_BUTTON_XPATH = "//li[contains(@class, 'entry--account')]";
	private String ACCOUNT_MENU_XPATH = "//div[contains(@class, 'account--menu')]";
	private String ACCOUNT_REGISTER_XPATH = ".//span[@class='navigation--register']/a";
	private String ACCOUNT_SIGNIN_XPATH = ".//a[contains(@class,'navigation--signin-btn')]";
	private String BACK_BUTTON_XPATH = "//div[contains(@class,'panel--actions')]/a[1]";
	private String SIGN_IN_INVALID_ALERT_CLASS ="alert--content";

	public void navigationTo() {
		QALogger.info("==============Start: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"====================");
		super.navigationTo("");
		super.waitPageIsLoaded();
		QALogger.info("==============End: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"======================");
	}

	public Boolean checkPageIsDisplay() {
		QALogger.info("==============Start: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"====================");
		isPass = true;
		String titleName = getBrowserTitle();
		if (!titleName.equals(HomePageConstansts.TITLE_NAME_PAGE)) {
			isPass = false;
		}
		QALogger.info("Tile: '"+titleName+"' => '"+HomePageConstansts.TITLE_NAME_PAGE+"', isPass: "+isPass);
		QALogger.info("==============End: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"======================");
		return isPass;
	}

	public void action(ActionCustomer actionUser) {
		QALogger.info("========Start: " + Thread.currentThread().getStackTrace()[1].getMethodName()+", action:"+actionUser.getActionName()+"====================");
		switch (actionUser) {
		case REGISTER:
			clickRegisterAction();
			break;
		case SIGN_IN:
			clickLoginInAction();
			break;
		case BACK_BUTTON_TO_HOME:
			clickBackToHomeAction();
			break;
		default:
			break;
		}
		QALogger.info("==============End: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"======================");
	}
	
	private void clickRegisterAction() {
		click(ACCOUNT_BUTTON_XPATH, LocatorType.XPATH);
		waitElementIsVisible(ACCOUNT_MENU_XPATH, LocatorType.XPATH);
		click(ACCOUNT_REGISTER_XPATH, LocatorType.XPATH);
	}
	
	private void clickLoginInAction() {
		click(ACCOUNT_BUTTON_XPATH, LocatorType.XPATH);
		waitElementIsVisible(ACCOUNT_MENU_XPATH, LocatorType.XPATH);
		click(ACCOUNT_SIGNIN_XPATH, LocatorType.XPATH);
	}
	
	private void clickBackToHomeAction() {
		click(BACK_BUTTON_XPATH, LocatorType.XPATH);
	}
	
	public Boolean verifyValidate(ActionValidate actionValidade) {
		switch (actionValidade) {
		case SIGN_IN_INVALID:
			return verifyRedNotifcation();
		default:
			System.out.println("Option ActionValidate invalid");
			return false;
		}
	}
	
	private Boolean verifyRedNotifcation() {
		isPass = true;
		waitElementIsVisible(SIGN_IN_INVALID_ALERT_CLASS, LocatorType.CLASS);
		if (!verifyText(HomePageConstansts.SIGN_IN_INVALID_ALERT, SIGN_IN_INVALID_ALERT_CLASS,LocatorType.CLASS)) { isPass = false;}
		return isPass;
	}

	public void typeSearch(String search) {
		QALogger.info("==============Start: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"====================");
		Type(search, SEARCH_FIELD_NAME, LocatorType.NAME);
		click(SUBMIT_SEARCH_ICON_CLASS, LocatorType.CLASS);
		QALogger.info("==============End: " + Thread.currentThread().getStackTrace()[1].getMethodName()+"======================");
	}

}
