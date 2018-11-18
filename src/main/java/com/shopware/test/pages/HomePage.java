package com.shopware.test.pages;

import com.shopware.test.base.ActionCustomer;
import com.shopware.test.base.HomePageConstansts;
import com.shopware.test.selenium.Browser;
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
	private String ACCOUNT_REGISTER_CLASS = "navigation--register";

	public void nativationTo(String url, Browser browser) throws Exception {
		QALogger.info("Start :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		QALogger.info("Navigation to '" + url + ", browser: " + browser.getBrowserName());
		super.browser(browser);
		super.navigationTo(url);
		super.waitPageLoaded();
		super.windowMaximize();
		QALogger.info("End :" + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public Boolean checkPageIsDisplay() {
		QALogger.info("Start :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		isPass = true;
		String titleName = getBrowserTitle();
		if (!titleName.equals(HomePageConstansts.TITLE_NAME_PAGE)) {
			isPass = false;
		}
		QALogger.info("Tile: '"+titleName+"' => '"+HomePageConstansts.TITLE_NAME_PAGE+"', isPass: "+isPass);
		QALogger.info("END :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return isPass;
	}

	public void actionUser(ActionCustomer actionUser) {
		QALogger.info("Start :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		switch (actionUser) {
		case REGISTER:
			Click(ACCOUNT_BUTTON_XPATH, LocatorType.XPATH);
			waitElementIsVisible(ACCOUNT_MENU_XPATH, LocatorType.XPATH);
			Click(ACCOUNT_REGISTER_CLASS, LocatorType.CLASS);
			break;
		case SIGN_IN:
			break;
		default:
			break;
		}
		QALogger.info("END :" + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void typeSearch(String search) {
		QALogger.info("Start :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		Type(search, SEARCH_FIELD_NAME, LocatorType.NAME);
		Click(SUBMIT_SEARCH_ICON_CLASS, LocatorType.CLASS);
		QALogger.info("END :" + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

}
