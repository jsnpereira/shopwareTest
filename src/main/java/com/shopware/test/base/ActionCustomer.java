package com.shopware.test.base;

public enum ActionCustomer {
	REGISTER("REGISTER"),
	SIGN_IN("SIGN_IN"),
	WISH_LIST("WISH_LIST"),
	SHOP_CART("SHOP_CART"),
	FORGOT("FORGOT");
	
	private String name;
	
	private ActionCustomer(String name) {
		this.name = name;
	}
	
	public String getActionName() {
		return this.name;
	}

}
