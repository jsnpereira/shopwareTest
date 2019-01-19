package com.shopware.test.pages.base;

public enum ActionCustomer {
	REGISTER("REGISTER"),
	SIGN_IN("SIGN_IN"),
	WISH_LIST("WISH_LIST"),
	SHOP_CART("SHOP_CART"),
	FORGOT("FORGOT"),
	CHANGE_PROFILE("CHANGE_PROFILE"),
	SAVE_CHANGES_PROFILE("SAVE_CHANGES_PROFILE"),
	SAVE_CHANGES_EMAIL_ADDRESS("SAVE_CHANGES_EMAIL_ADDRESS"),
	SAVE_CHANGES_PASSWORD("SAVE_CHANGES_PASSWORD"),
	CUSTOMER_ACCOUNT_LINK("CUSTOMER_ACCOUNT_LINK")
	
	;
	
	private String name;
	
	private ActionCustomer(String name) {
		this.name = name;
	}
	
	public String getActionName() {
		return this.name;
	}

}
