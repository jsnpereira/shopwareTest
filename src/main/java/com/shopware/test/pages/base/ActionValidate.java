package com.shopware.test.pages.base;

public enum ActionValidate {
	SIGN_IN_INVALID("SIGN_IN_INVALID"),
	SAVE_NAMES_ALERT("SAVE_NAMES_ALERT"),
	SAVE_PASSWORD_ALERT("SAVE_PASSWORD_ALERT"),
	NAMES_PROFILE_DASHBOARD("NAMES_PROFILE_DASHBOARD"),
	EMAIL_PROFILE_DASHBOARD("EMAIL_PROFILE_DASHBOARD")
	
	;
	
	private String name;
	
	private ActionValidate(String name) {
		this.name = name;
	}
	
	public String getActionName() {
		return this.name;
	}

}
