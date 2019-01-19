package com.shopware.test.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.CommonUtils;
import com.shopware.test.utils.QALogger;

public class TestBase {

	@BeforeClass
	@Parameters("browser")
	public void preCondition(@Optional("chrome") String browser) throws Exception {
		QALogger.setLog(this);
		QALogger.info("Setup the browser");
		SeleniumPage.browser(browser);
		SeleniumPage.navigationTo(CommonUtils.getValueProperties("url.base"));
	}

	@AfterClass
	public void postCondition() {
		SeleniumPage.close();
	}
}
