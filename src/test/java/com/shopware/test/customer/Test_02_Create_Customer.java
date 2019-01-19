package com.shopware.test.customer;

import org.json.JSONArray;
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
import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.pages.base.HomePageConstansts;
import com.shopware.test.selenium.Browser;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class Test_02_Create_Customer  extends TestBase {
	HomePage homePage;
	CustomerRegisterPage customerRegisterPage;
	CustomerAccountPage customerAccountPage;
	ApiManagement api;
	Customer_UI customerUI;

	@Test(priority=1, description = "Launch home page")
	public void test01() throws Exception {
		AssertJUnit.assertTrue(homePage.checkPageIsDisplay());
	}
	
	@Test(priority=2, description = "Go to customer page and register new customer")
	public void test02()  {
		homePage.actionUser(ActionCustomer.REGISTER);
		homePage.redirectTo("#show-registration");
		AssertJUnit.assertTrue(customerRegisterPage.checkPageIsDisplay());
	}
	
	@Test(priority=3, description = "Create new customer on page")
	public void test03() {
		try {
			customerUI = new Customer_UI();
			customerRegisterPage.enterTextBoxCustomerRegister(customerUI);
			customerRegisterPage.action(ActionCustomer.REGISTER);
			customerRegisterPage.redirectTo("/account");
			AssertJUnit.assertTrue(customerAccountPage.checkCustomerAccount());
		} catch (InterruptedException e) {
			QALogger.error("Failed ", e);
		}
	}

	@BeforeClass
	public void beforeTest() {
		QALogger.setLog(this);
		QALogger.info("============================ Start: '"+this.getClass().getName()+"' ============================");
		homePage = new HomePage();
		customerRegisterPage = new CustomerRegisterPage();
		customerAccountPage = new CustomerAccountPage();
	}

	@AfterClass
	public void afterTest() {
		try {
			api = new ApiManagement();
			JSONObject json = api.getCustomerList();
			JSONArray customers = json.getJSONArray("data");
			int customerId = 0;
			for (int i = 0; i < customers.length(); i++) {
				JSONObject customer = (JSONObject) customers.get(i);
				if (customer.getString("email").equals(customerUI.getEmail())) {
					QALogger.info("Email: "+customer.getString("email"));
					customerId = customer.getInt("id");
					QALogger.info("Customer id was found: "+customerId);
				}
			}
			
			api.deleteCustomer(String.valueOf(customerId));
			QALogger.info("Status code: "+api.getStatusCode());
			AssertJUnit.assertTrue(api.getStatusCode().equals("200"));
		} catch (Exception e) {
			QALogger.error("FAIELD: ", e);
		}
		
		QALogger.info("============================ End: '"+this.getClass().getName()+"' ============================");
	}

}
