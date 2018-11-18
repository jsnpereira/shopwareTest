package com.shopware.test.home;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.shopware.test.base.HomePageConstansts;
import com.shopware.test.pages.HomePage;
import com.shopware.test.selenium.Browser;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class Test_01_LaunchFirstHomePage {
	HomePage homePage;

 
  @Test
  public void launchHomePage() throws Exception {
	  System.out.println("Test log");
	  QALogger.info("Start to testing the launch first URL");
	  homePage.nativationTo(HomePageConstansts.URL, Browser.Chrome);
	  QALogger.info("End to testing the launch first URL");
	  AssertJUnit.assertTrue(homePage.checkPageIsDisplay());
  }
  @BeforeClass
  public void beforeTest() {
	  QALogger.setLog(this);
	  QALogger.info("============================ Start: '"+this.getClass().getName()+"' ============================");
	  homePage = new HomePage();
  }
  
  @AfterClass
  public void afterTest() {
	  SeleniumPage.close();
	  QALogger.info("============================ End: '"+this.getClass().getName()+"' ============================");
  }

}
