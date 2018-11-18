package com.shopware.test.customer;

import static org.testng.Assert.assertTrue;

import org.json.JSONArray;
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

public class Test_02_Create_Customer {
	HomePage homePage;
	CustomerPage customerPage;
	CustomerAccountPage customerAccountPage;
	ApiManagement api;
	Customer_UI customerUI;

	@Test(priority=1, description = "Launch home page")
	public void launch() throws Exception {
		homePage.nativationTo(HomePageConstansts.URL,Browser.Chrome);
		AssertJUnit.assertTrue(homePage.checkPageIsDisplay());
	}
	
	@Test(priority=2, description = "Go to customer page and register new customer")
	public void goCustomerPage()  {
		homePage.actionUser(ActionCustomer.REGISTER);
		homePage.redirectTo("/account");
		AssertJUnit.assertTrue(customerPage.checkPageIsDisplay());
	}
	
	@Test(priority=3, description = "Create new customer on page")
	public void createNewCustomer() {
		try {
			customerUI = new Customer_UI();
			customerPage.enterTextBoxCustomerRegister(customerUI);
			customerPage.action(ActionCustomer.REGISTER);
			customerPage.redirectTo("/account");
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
		customerPage= new CustomerPage();
		customerAccountPage = new CustomerAccountPage();
	}

	@AfterClass
	public void afterTest() {
		try {
			SeleniumPage.close();
			api = new ApiManagement();
			JSONObject json = api.getCustomerList(HomePageConstansts.URL);
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
			
			api.deleteCustomer(HomePageConstansts.URL,String.valueOf(customerId));
			QALogger.info("Status code: "+api.getStatusCode());
			assertTrue(api.getStatusCode().equals("200"));
		} catch (Exception e) {
			QALogger.error("FAIELD: ", e);
		}
		
		QALogger.info("============================ End: '"+this.getClass().getName()+"' ============================");
	}

}
