package com.shopware.test.customer;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopware.test.api.ApiManagement;
import com.shopware.test.base.TestBase;
import com.shopware.test.pages.CustomerAccountPage;
import com.shopware.test.pages.CustomerRegisterPage;
import com.shopware.test.pages.HomePage;
import com.shopware.test.pages.ProfileEditPage;
import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.pages.base.HomePageConstansts;
import com.shopware.test.selenium.Browser;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class Test_04_Change_Names_Customer  extends TestBase {
	HomePage homePage;
	CustomerRegisterPage customerRegisterPage;
	CustomerAccountPage customerAccountPage;
	ProfileEditPage profileEditPage;
	ApiManagement api;
	Customer_UI customerUI;
	int customerId;
	
	@Test(priority = 1, description = "Launch home page and click sign in")
	public void test01() {
		try {
			homePage.checkPageIsDisplay();
			homePage.actionUser(ActionCustomer.SIGN_IN);
			homePage.redirectTo("#hide-registration");
			assertTrue(customerRegisterPage.checkPageIsDisplay());
		} catch (Exception e) {
			QALogger.error("Failed", e);
		}
	}
	
	@Test(priority = 2, description = "Sign in and displays customer account page")
	public void test02() {
		customerRegisterPage.signIn(customerUI);
		customerRegisterPage.redirectTo("/account");
		assertTrue(customerAccountPage.checkCustomerAccount());
	}
	
	@Test(priority = 3, description = "Change first and last name and verify green alert")
	public void test03() {
		customerAccountPage.action(ActionCustomer.CHANGE_PROFILE);
		customerAccountPage.redirectTo("/account/profile");
		assertTrue(profileEditPage.checkPageIsDisplay());
		
		customerUI.setFirstName("John");
		customerUI.setLastName("Lennon");
		
		profileEditPage.enterFieldsProfile(customerUI);
		profileEditPage.action(ActionCustomer.SAVE_CHANGES_PROFILE);
		assertTrue(profileEditPage.checkAlert(true));
	}
	
	@Test(priority = 4, description = "Verify the names changed on dashboard")
	public void test04() {
		profileEditPage.action(ActionCustomer.CUSTOMER_ACCOUNT_LINK);
		profileEditPage.waitPageIsRedirecting("/account/profile");
		assertTrue(customerAccountPage.checkProfileDashboard(customerUI));
	}
	

	@BeforeClass
	public void beforeClass() {
		QALogger.setLog(this);
		QALogger.info("============================ Start: '"+this.getClass().getName()+"' ============================");
		homePage = new HomePage();
		customerRegisterPage = new CustomerRegisterPage();
		customerAccountPage = new CustomerAccountPage();
		profileEditPage = new ProfileEditPage();
		customerUI = new Customer_UI();
		api = new ApiManagement();
		
		JSONObject jCustomer = customerUI.converToJSON();
		JSONObject jCustomerResult = api.postCustomer(jCustomer.toString());
		AssertJUnit.assertTrue(api.getStatusCode().equals("201"));
		customerId = jCustomerResult.getJSONObject("data").getInt("id");
	}

	@AfterClass
	public void afterClass() {
		try {
			api.deleteCustomer(String.valueOf(customerId));
			QALogger.info("Status code: "+api.getStatusCode());
			AssertJUnit.assertTrue(api.getStatusCode().equals("200"));
		} catch (Exception e) {
			QALogger.error("Failed", e);
		}
		QALogger.info("============================ End: '"+this.getClass().getName()+"' ============================");
	}

}
