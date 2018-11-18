package com.shopware.test.pages;

import com.shopware.test.base.CustomerAccountPageConstants;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class CustomerAccountPage extends SeleniumPage implements CustomerAccountPageConstants {
	private Boolean isPass;
	
	public Boolean checkCustomerAccount() {
		QALogger.info("Start :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		isPass = true;
		String titleName = getBrowserTitle();
		if (!titleName.equals(TITLE_NAME_PAGE)) {
			isPass = false;
		}
		QALogger.info("Tile name: '" + titleName + "' => '" + TITLE_NAME_PAGE + "', isPass: " + isPass);
		QALogger.info("END :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return isPass;
	}

}
