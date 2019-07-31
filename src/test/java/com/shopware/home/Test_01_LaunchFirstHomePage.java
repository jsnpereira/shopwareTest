package com.shopware.home;


import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopware.test.base.TestBase;
import com.shopware.test.pages.HomePage;
import com.shopware.test.pages.base.HomePageConstansts;
import com.shopware.test.utils.QALogger;

public class Test_01_LaunchFirstHomePage extends TestBase {
	HomePage homePage;

	@Test(priority = 1, description = "Launch the first home page")
	public void test01() {
		QALogger.info("Check page is displays");
		assertThat(homePage.getTitlePage()).isEqualTo(HomePageConstansts.TITLE_NAME_PAGE);
	}

	@BeforeClass
	public void beforeTest() {
		QALogger.info("============================ Start: '" + this.getClass().getName() + "' ============================");
		homePage = new HomePage();
	}

	@AfterClass
	public void afterTest() {
		QALogger.info(
				"============================ End: '" + this.getClass().getName() + "' ============================");
	}

}
