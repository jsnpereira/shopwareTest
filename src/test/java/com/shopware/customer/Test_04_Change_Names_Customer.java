package com.shopware.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
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
import com.shopware.test.pages.base.CustomerAccountPageConstants;
import com.shopware.test.pages.base.CustomerPageConstansts;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.pages.base.HomePageConstansts;
import com.shopware.test.utils.DataUtils;
import com.shopware.test.utils.QALogger;

public class Test_04_Change_Names_Customer extends TestBase {
	HomePage homePage;
	CustomerRegisterPage customerRegisterPage;
	CustomerAccountPage customerAccountPage;
	ProfileEditPage profileEditPage;
	ApiManagement api;
	Customer_UI customerUI;
	int customerId;

	@Test(priority = 1, description = "Change customer names")
	public void test01() {
		assertThat(homePage.getTitlePage()).isEqualTo(HomePageConstansts.TITLE_NAME_PAGE);
		homePage.action(ActionCustomer.SIGN_IN);
		homePage.redirectTo("#hide-registration");
		assertThat(customerRegisterPage.getBrowserTitle()).isEqualTo(CustomerPageConstansts.TITLE_NAME_PAGE);

		customerRegisterPage.signIn(customerUI);
		customerRegisterPage.redirectTo("/account");
		assertThat(customerAccountPage.getBrowserTitle()).isEqualTo(CustomerAccountPageConstants.TITLE_NAME_PAGE);

		customerAccountPage.action(ActionCustomer.CHANGE_PROFILE);
		customerAccountPage.redirectTo("/account/profile");
		profileEditPage.checkPageIsDisplay();

		customerUI.setFirstName("John");
		customerUI.setLastName("Lennon");

		profileEditPage.enterFieldsProfile(customerUI);
		profileEditPage.action(ActionCustomer.SAVE_CHANGES_PROFILE);
		assertThat(ProfileEditPage.PROFILE_GREEN_ALERT_MESSAGE).isEqualTo(profileEditPage.checkAlert(ActionValidate.SAVE_NAMES_ALERT, true));
	
		profileEditPage.action(ActionCustomer.CUSTOMER_ACCOUNT_LINK);
		profileEditPage.waitPageIsRedirecting("/account/profile");
		
		String names = DataUtils.capitalize(customerUI.getSalutation())+" "+customerUI.getFirstName()+" "+customerUI.getLastName();
		String profile = customerAccountPage.checkProfileDashboard(ActionValidate.NAMES_PROFILE_DASHBOARD);
		assertThat(profile).isEqualTo(names);
		profile = customerAccountPage.checkProfileDashboard(ActionValidate.EMAIL_PROFILE_DASHBOARD);
		assertThat(profile).isEqualTo(customerUI.getEmail());
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
		assertThat(api.getStatusCode()).isEqualTo("201");
		customerId = jCustomerResult.getJSONObject("data").getInt("id");
	}

	@AfterClass
	public void afterClass() {
		try {
			api.deleteCustomer(String.valueOf(customerId));
			QALogger.info("Status code: " + api.getStatusCode());
			assertThat(api.getStatusCode()).isEqualTo("200");
		} catch (Exception e) {
			QALogger.error("Failed", e);
		}
		QALogger.info(
				"============================ End: '" + this.getClass().getName() + "' ============================");
	}

}
