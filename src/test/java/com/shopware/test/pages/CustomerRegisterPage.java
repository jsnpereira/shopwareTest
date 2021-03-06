package com.shopware.test.pages;

import com.shopware.test.pages.base.ActionCustomer;
import com.shopware.test.pages.base.Address_UI;
import com.shopware.test.pages.base.CustomerPageConstansts;
import com.shopware.test.pages.base.Customer_UI;
import com.shopware.test.selenium.LocatorType;
import com.shopware.test.selenium.SeleniumPage;
import com.shopware.test.utils.QALogger;

public class CustomerRegisterPage extends SeleniumPage implements CustomerPageConstansts {
	private static String FIRSTNAME_INPUT_ID = "firstname";
	private static String LASTNAME_INPUT_ID = "lastname";	
	private static String EMAIL_INPUT_ID = "register_personal_email";
	private static String PASSWORD_INPUT_ID = "register_personal_password";
	private static String SALUTATION_DROPDOWN_FIELD_ID = "salutation";
	private static String CUSTOMER_TYPE_DROPDOWN_FIELD_ID = "register_personal_customer_type";
	private static String STREET_INPUT_ID = "street";
	private static String ZIP_CODE_INPUT_ID = "zipcode";
	private static String CITY_INPUT_ID = "city";
	private static String COUNTRY_DROPDOWN_XPATH = ".//select[@id='country']";
	private static String CONTINUE_BUTTON_XPATH = ".//button[contains(@class, 'register--submit')]";
//	private static String SHIPING_ADDRESS_CHECK_ID = "register_billing_shippingAddress";
	private static String SELECT_UK_OPTION_DROPDOWN_XPATH = ".//select[@id='country']/option[@value='11']";
	private static String USER_EMAIL_INPUT_ID = "email";
	private static String USER_PWD_INPUT_ID = "passwort";
	private static String SIGNIN_LOGIN_BUTTON_XPATH =".//button[contains(@class,'register--login-btn')]";

	
	public String getTitlePage() {
		return getBrowserTitle();
	}

	public void enterTextBoxCustomerRegister(Customer_UI customer) throws InterruptedException {
		super.selectValueDropDown(customer.getCustomerType(), CUSTOMER_TYPE_DROPDOWN_FIELD_ID, LocatorType.ID);
		super.selectValueDropDown(customer.getSalutation(), SALUTATION_DROPDOWN_FIELD_ID, LocatorType.ID);
		super.Type(customer.getFirstName(), FIRSTNAME_INPUT_ID, LocatorType.ID);
		super.Type(customer.getLastName(), LASTNAME_INPUT_ID, LocatorType.ID);
		super.Type(customer.getEmail(), EMAIL_INPUT_ID, LocatorType.ID);
		super.Type(customer.getPassword(), PASSWORD_INPUT_ID, LocatorType.ID);

		Address_UI address = customer.getAddresses().get(0);
		super.Type(address.getAddressLine(), STREET_INPUT_ID, LocatorType.ID);
		super.Type(address.getZipCode(), ZIP_CODE_INPUT_ID, LocatorType.ID);
		super.Type(address.getCity(), CITY_INPUT_ID, LocatorType.ID);
		
		super.click(COUNTRY_DROPDOWN_XPATH, LocatorType.XPATH);
		super.waitElementIsVisible(SELECT_UK_OPTION_DROPDOWN_XPATH, LocatorType.XPATH);
		super.click(SELECT_UK_OPTION_DROPDOWN_XPATH, LocatorType.XPATH);
	}
	
	public void signIn(Customer_UI customer) {
		super.waitElementIsVisible(USER_EMAIL_INPUT_ID, LocatorType.ID);
		super.Type(customer.getEmail(), USER_EMAIL_INPUT_ID, LocatorType.ID);
		super.Type(customer.getPassword(), USER_PWD_INPUT_ID, LocatorType.ID);
		super.click(SIGNIN_LOGIN_BUTTON_XPATH, LocatorType.XPATH);
	}

	public void action(ActionCustomer action) {
		QALogger.info("Action customer: "+action.getActionName());
		switch (action) {
		case REGISTER:
			super.click(CONTINUE_BUTTON_XPATH, LocatorType.XPATH);
			break;
		default:
			break;
		}
	}

}
