package com.shopware.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shopware.test.api.ApiManagement;
import com.shopware.test.base.TestBase;
import com.shopware.test.pages.CustomerAccountPage;
import com.shopware.test.pages.CustomerRegisterPage;
import com.shopware.test.pages.HomePage;
import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.CustomerAccountPageConstants;
import com.shopware.test.pages.base.CustomerPageConstansts;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.pages.base.HomePageConstansts;
import com.shopware.test.utils.QALogger;

public class Test_02_Create_Customer  extends TestBase {
	HomePage homePage;
	CustomerRegisterPage customerRegisterPage;
	CustomerAccountPage customerAccountPage;
	ApiManagement api;
	Customer_UI customerUI;

	@Test(priority=1, description = "Create customer")
	public void test01() throws Exception {
		assertThat(homePage.getTitlePage()).isEqualTo(HomePageConstansts.TITLE_NAME_PAGE);
		
		homePage.action(ActionCustomer.REGISTER);
		homePage.redirectTo("#show-registration");
		assertThat(customerRegisterPage.getBrowserTitle()).isEqualTo(CustomerPageConstansts.TITLE_NAME_PAGE);
		
		customerRegisterPage.enterTextBoxCustomerRegister(customerUI);
		customerRegisterPage.action(ActionCustomer.REGISTER);
		customerRegisterPage.redirectTo("/account");
		assertThat(customerAccountPage.getBrowserTitle()).isEqualTo(CustomerAccountPageConstants.TITLE_NAME_PAGE);
	}
	
	@BeforeClass
	public void beforeTest() {
		QALogger.setLog(this);
		QALogger.info("============================ Start: '"+this.getClass().getName()+"' ============================");
		homePage = new HomePage();
		customerRegisterPage = new CustomerRegisterPage();
		customerAccountPage = new CustomerAccountPage();
		customerUI = new Customer_UI();
	}

	@AfterClass(alwaysRun=true)
	public void afterTest() {
		try {
			api = new ApiManagement();
			JSONObject json = api.getCustomerList();
			JSONArray customers = json.getJSONArray("data");
			int customerId = 0;
			Boolean canDelete = false;
			for (int i = 0; i < customers.length(); i++) {
				JSONObject customer = (JSONObject) customers.get(i);
				QALogger.info("Email: "+customer.getString("email"));
				if (customer.getString("email").equals(customerUI.getEmail())) {
					QALogger.info("Email: "+customer.getString("email"));
					customerId = customer.getInt("id");
					QALogger.info("Customer id was found: "+customerId);
					canDelete =true;
				}
			}
			
			if (canDelete) {
				api.deleteCustomer(String.valueOf(customerId));
				QALogger.info("Status code: "+api.getStatusCode());
				assertThat(api.getStatusCode()).isEqualTo("200");
			}
			
		} catch (Exception e) {
			QALogger.error("FAIELD: ", e);
		}
		
		QALogger.info("============================ End: '"+this.getClass().getName()+"' ============================");
	}

}
