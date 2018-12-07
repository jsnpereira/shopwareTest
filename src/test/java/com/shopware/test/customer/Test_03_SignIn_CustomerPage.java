package com.shopware.test.customer;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopware.test.api.ApiManagement;
import com.shopware.test.base.ActionCustomer;
import com.shopware.test.base.Customer_UI;
import com.shopware.test.base.HomePageConstansts;
import com.shopware.test.pages.CustomerAccountPage;
import com.shopware.test.pages.CustomerPage;
import com.shopware.test.pages.HomePage;
import com.shopware.test.selenium.Browser;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class Test_03_SignIn_CustomerPage {
	HomePage homePage;
	CustomerPage customerPage;
	CustomerAccountPage customerAccountPage;
	ApiManagement api;
	Customer_UI customerUI;
	int customerId;
	
	@Test(priority = 1, description = "Launch home page and click sign in")
	public void test01() {
		try {
			homePage.navigationTo(HomePageConstansts.URL, Browser.FirefoxLoc.getBrowserName());
			homePage.checkPageIsDisplay();
			homePage.actionUser(ActionCustomer.SIGN_IN);
			homePage.redirectTo("#hide-registration");
			assertTrue(customerPage.checkPageIsDisplay());
		} catch (Exception e) {
			QALogger.error("Failed", e);
		}
	}
	
	@Test(priority = 2, description = "Sign in and displays customer account page")
	public void test02() {
		customerPage.signIn(customerUI);
		customerPage.redirectTo("/account");
		assertTrue(customerAccountPage.checkCustomerAccount());
		assertTrue(customerAccountPage.checkProfileDashboard(customerUI));
	}

	@BeforeClass
	public void beforeClass() {
		QALogger.setLog(this);
		QALogger.info("============================ Start: '"+this.getClass().getName()+"' ============================");
		homePage = new HomePage();
		customerPage= new CustomerPage();
		customerAccountPage = new CustomerAccountPage();
		customerUI = new Customer_UI();
		api = new ApiManagement();
		
		JSONObject jCustomer = customerUI.converToJSON();
		JSONObject jCustomerResult = api.postCustomer(HomePageConstansts.URL, jCustomer.toString());
		AssertJUnit.assertTrue(api.getStatusCode().equals("201"));
		customerId = jCustomerResult.getJSONObject("data").getInt("id");
	}

	@AfterClass
	public void afterClass() {
		try {
			SeleniumPage.close();
			api.deleteCustomer(HomePageConstansts.URL,String.valueOf(customerId));
			QALogger.info("Status code: "+api.getStatusCode());
			AssertJUnit.assertTrue(api.getStatusCode().equals("200"));
		} catch (Exception e) {
			QALogger.error("Failed", e);
		}
		QALogger.info("============================ End: '"+this.getClass().getName()+"' ============================");
	}

}
