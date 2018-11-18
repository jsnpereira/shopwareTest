package com.shopware.test.base;

import com.shopware.test.utils.DataUtils;

public class Address_UI {
	private String saluation;
	private String firstName;
	private String lastName;
	private String addressLine;
	private String code;
	private String city;
	private String country;
	private String zipCode;
	
	public Address_UI(Customer_UI customer) {
		this.saluation = customer.getSalutation();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.addressLine = DataUtils.randomString(10)+" avenue, 910";
		this.code = "53060";
		this.city = "Springfield";
		this.country = "Great Britain";
		this.zipCode = "9010-090";
	}
	
	public Address_UI() {
		this.saluation = "mr";
		this.firstName = DataUtils.randomString(10);
		this.lastName = DataUtils.randomString(10);
		this.addressLine = DataUtils.randomString(10)+" avenue, 910";
		this.code = "53060";
		this.city = "Springfield";
		this.country = "Great Britain";
		this.zipCode = "9010-090";
	}
	
	

	public Address_UI(String saluation, String firtsName, String lastName, String addressLine, String code, String city,
			String country, String zipCode) {
		super();
		this.saluation = saluation;
		this.firstName = firtsName;
		this.lastName = lastName;
		this.addressLine = addressLine;
		this.code = code;
		this.city = city;
		this.country = country;
	}

	public String getSaluation() {
		return saluation;
	}

	public void setSaluation(String saluation) {
		this.saluation = saluation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
