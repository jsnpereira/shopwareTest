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
import com.shopware.test.pages.base.ActionValidate;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.utils.DataUtils;
import com.shopware.test.utils.QALogger;

public class Test_05_Change_Password extends TestBase {
	HomePage homePage;
	CustomerRegisterPage customerRegisterPage;
	CustomerAccountPage customerAccountPage;
	ProfileEditPage profileEditPage;
	ApiManagement api;
	Customer_UI customerUI;
	int customerId;
	String oldPassword, newPassword;

	@Test(priority = 1, description = "Change password and try sign in with new password")
	public void test01() {
		homePage.checkPageIsDisplay();
		homePage.action(ActionCustomer.SIGN_IN);
		homePage.redirectTo("#hide-registration");
		assertTrue(customerRegisterPage.checkPageIsDisplay());
		
		customerRegisterPage.signIn(customerUI);
		customerRegisterPage.redirectTo("/account");
		assertTrue(customerAccountPage.checkCustomerAccount());
		
		customerAccountPage.action(ActionCustomer.CHANGE_PROFILE);
		customerAccountPage.redirectTo("/account/profile");
		assertTrue(profileEditPage.checkPageIsDisplay());

		newPassword = "@bc" + DataUtils.randomString(5);
		profileEditPage.enterChangePasswordFields(customerUI, newPassword);
		profileEditPage.action(ActionCustomer.SAVE_CHANGES_PASSWORD);
		assertTrue(profileEditPage.checkAlert(ActionValidate.SAVE_PASSWORD_ALERT, true));
		
		profileEditPage.action(ActionCustomer.CUSTOMER_ACCOUNT_LINK);
		profileEditPage.waitPageIsRedirecting("/account/profile");
		customerAccountPage.action(ActionCustomer.LOGOUT_USER_BY_NAVIGATION);
		customerAccountPage.redirectTo("/account/logout");
		homePage.action(ActionCustomer.BACK_BUTTON_TO_HOME);
		homePage.waitPageIsRedirecting("/account/logout");
		
		homePage.checkPageIsDisplay();
		homePage.action(ActionCustomer.SIGN_IN);
		homePage.redirectTo("#hide-registration");
		customerRegisterPage.signIn(customerUI);
		homePage.verifyValidate(ActionValidate.SIGN_IN_INVALID);
		
		customerUI.setPassword(newPassword);
		customerRegisterPage.signIn(customerUI);
		customerRegisterPage.redirectTo("/account");
		assertTrue(customerAccountPage.checkCustomerAccount());
	}

	@BeforeClass
	public void beforeClass() {
		QALogger.setLog(this);
		QALogger.info(
				"============================ Start: '" + this.getClass().getName() + "' ============================");
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
			QALogger.info("Status code: " + api.getStatusCode());
			AssertJUnit.assertTrue(api.getStatusCode().equals("200"));
		} catch (Exception e) {
			QALogger.error("Failed", e);
		}
		QALogger.info(
				"============================ End: '" + this.getClass().getName() + "' ============================");
	}

}
